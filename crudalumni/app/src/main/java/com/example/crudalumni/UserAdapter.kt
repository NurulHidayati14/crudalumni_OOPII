package com.example.crudalumni

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crudalumni.Database.User
import kotlinx.android.synthetic.main.adapter_user.view.*


class UserAdapter (private val AllUser: ArrayList<User>, private val listener: OnAdapterListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_user, parent, false)
        )
    }

    override fun getItemCount() = AllUser.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = AllUser[position]
        holder.view.text_username.text = user.username
        holder.view.text_username.setOnClickListener {
            listener.onClick(user)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(user)
        }
        holder.view.icon_editUser.setOnClickListener {
            listener.onUpdate(user)
        }
    }

    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<User>) {
        AllUser.clear()
        AllUser.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(user: User)
        fun onDelete(user: User)
        fun onUpdate(user: User)
    }

}