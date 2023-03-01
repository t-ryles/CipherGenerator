package com.example.ciphergenerator.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ciphergenerator.R
import com.example.ciphergenerator.data.Cypher

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var cypherList  = emptyList<Cypher>()

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cypher_row, parent, false))
    }

    override fun getItemCount(): Int {
        return cypherList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentCypher = cypherList[position]
        holder.itemView.findViewById<TextView>(R.id.cypherTitle_TV).text = currentCypher.name.toString()
        holder.itemView.findViewById<TextView>(R.id.cypher_TV).text = currentCypher.cipher.toString()
    }

    fun setData (cypher: List<Cypher>) {
        this.cypherList = cypher
        notifyDataSetChanged()
    }
}