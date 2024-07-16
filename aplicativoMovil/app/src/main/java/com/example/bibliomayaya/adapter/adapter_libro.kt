package com.example.bibliomayaya.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bibliomayaya.R

class AdapterLibro(
    var listLibro: List<Libro>,
    var context: Context
) : RecyclerView.Adapter<AdapterLibro.MyHolder>() {

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var lblNombreLibro: TextView
        lateinit var lblNombreAutor: TextView

        init {
            lblNombreLibro = itemView.findViewById(R.id.lblNombreLibro)
            lblNombreAutor = itemView.findViewById(R.id.lblNombreAutor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_libro, parent, false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val libro = listLibro[position]
        holder.lblNombreLibro.text = libro.name
        holder.lblNombreAutor.text = libro.author
    }

    override fun getItemCount(): Int {
        return listLibro.size
    }
}
