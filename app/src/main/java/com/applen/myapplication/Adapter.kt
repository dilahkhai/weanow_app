package com.applen.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.applen.myapplication.databinding.ItemRowBinding

class Adapter(private val listUs: ArrayList<Item>): RecyclerView.Adapter<Adapter.ListViewHolder>() {
    class ListViewHolder(var binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        try {
            val nullString: String? = null
            nullString!!.length
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listUs.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, kelas, photo) = listUs[position]
        holder.binding.ivStudent.setImageResource(photo)
        holder.binding.tvName.text = name
        holder.binding.tvClass.text = kelas
        holder.binding.tvDescription.text = description
    }
}