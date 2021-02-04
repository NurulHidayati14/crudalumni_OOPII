package com.example.crudalumni

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.crudalumni.Database.AppRoomDB
import com.example.crudalumni.Database.Constant
import com.example.crudalumni.Database.Siswa
import kotlinx.android.synthetic.main.activity_edit_siswa.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditSiswaActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var siswaId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_siswa)
        setupListener()
        setupView()
    }

    fun setupListener(){
        btn_saveSiswa.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.siswaDao().addSiswa(
                        Siswa(0, txt_nama.text.toString(), Integer.parseInt(txt_tahunangkatan.text.toString()), Integer.parseInt(txt_jurusan.text.toString()) )
                )
                finish()
            }
        }
        btn_updateSiswa.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.siswaDao().updateSiswa(
                    Siswa(siswaId, txt_nama.text.toString(), Integer.parseInt(txt_tahunangkatan.text.toString()), Integer.parseInt(txt_jurusan.text.toString()) )
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
                btn_updateSiswa.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btn_saveSiswa.visibility = View.GONE
                btn_updateSiswa.visibility = View.GONE
                getSiswa()
            }
            Constant.TYPE_UPDATE -> {
                btn_saveSiswa.visibility = View.GONE
                getSiswa()
            }
        }
    }

    fun getSiswa() {
        siswaId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
           val siswas =  db.siswaDao().getSiswa( siswaId )[0]
            txt_nama.setText( siswas.nama )
            txt_tahunangkatan.setText( siswas.tahunangkatan.toString() )
            txt_jurusan.setText( siswas.jurusan.toString() )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}