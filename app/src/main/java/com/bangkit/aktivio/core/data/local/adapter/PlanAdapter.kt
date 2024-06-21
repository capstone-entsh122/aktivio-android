package com.bangkit.aktivio.core.data.local.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.domain.model.PlanModel

class PlanAdapter(private val listArticle: ArrayList<PlanModel>) : RecyclerView.Adapter<PlanAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    @SuppressLint("NotifyDataSetChanged")
    fun addData(items: List<PlanModel>){
        listArticle.clear()
        listArticle.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemCLickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val article = listArticle[position]

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listArticle[holder.absoluteAdapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listArticle.size
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imageView : ImageView = itemView.findViewById(R.id.iVThumbnail)
        var title : TextView = itemView.findViewById(R.id.tvTitle)
        var desc : TextView = itemView.findViewById(R.id.tvDesc)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: PlanModel)
    }
}