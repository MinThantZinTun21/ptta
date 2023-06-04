package com.mtz.zinkoko

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtz.ptta.R
import com.mtz.zinkoko.data.NOTD

class RecAdapter(val data: ArrayList<NOTD>) : RecyclerView.Adapter<RecAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_no, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.textTotalAmout).text =
            data[position].amount.toString()
        holder.itemView.findViewById<TextView>(R.id.textNo2).text =
            data[position].no.toString()
    }


    fun setData(newdta: MutableList<NOTD>) {
        data.clear()
        data.addAll(newdta)
        notifyDataSetChanged()
    }

    fun addData(newdta: MutableList<NOTD>) {
        data.addAll(newdta)
        notifyDataSetChanged()
    }


}