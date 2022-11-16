package com.example.hiltdaggerretrocoroutine.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hiltdaggerretrocoroutine.R
import com.example.hiltdaggerretrocoroutine.data.model.User
import com.example.hiltdaggerretrocoroutine.databinding.ItemLayoutBinding


class MainAdapter(private val users:ArrayList<User>):RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

 inner class DataViewHolder(val bind:ItemLayoutBinding):RecyclerView.ViewHolder(bind.root)
//    fun bind(user: User){
//        itemView.textViewUserName.text=user.name
//        itemView.textViewUserEmail.text=user.email
//        Glide.with(itemView.imageViewAvatar.context)
//            .load(user.avatar)
//            .into(itemView.imageViewAvatar)
//    }






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        with(holder){
            with(users[position]){
                bind.textViewUserName.text = this.name
                bind.textViewUserEmail.text = this.email
                        Glide.with(bind.imageViewAvatar.context)
            .load(this.avatar)
            .into(bind.imageViewAvatar)
            }
        }
       // holder.bind
    }

    override fun getItemCount(): Int =users.size
 fun addData(list: List<User>){
     users.addAll(list)
 }

}