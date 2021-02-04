package com.example.crudalumni

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.crudalumni.Database.AppRoomDB
import com.example.crudalumni.Database.Constant
import com.example.crudalumni.Database.User
import kotlinx.android.synthetic.main.activity_edit_user.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditUserActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
        setupListener()
        setupView()
    }

    fun setupListener(){
        btn_saveUser.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().addUser(
                        User(0, txt_nama.text.toString(), txt_username.text.toString())
                )
                finish()
            }
        }
        btn_updateUser.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().updateUser(
                    User(userId, txt_nama.text.toString(), txt_username.text.toString())
                )
                finish()
            }
        }
    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                btn_updateUser.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btn_saveUser.visibility = View.GONE
                btn_updateUser.visibility = View.GONE
                getUser()
            }
            Constant.TYPE_UPDATE -> {
                btn_saveUser.visibility = View.GONE
                getUser()
            }
        }
    }

    fun getUser() {
        userId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val users =  db.userDao().getUser( userId )[0]
            txt_nama.setText( users.nama )
            txt_username.setText( users.username )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}