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

class PlanAdapter(private val listPlan: ArrayList<PlanModel>) : RecyclerView.Adapter<PlanAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    @SuppressLint("NotifyDataSetChanged")
    fun addData(items: List<PlanModel>){
        listPlan.clear()
        listPlan.addAll(items)
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
        val plan = listPlan[position]
        holder.title.text = plan.sportType
        holder.desc.text = "${plan.recommendedDuration} minutes"
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listPlan[holder.absoluteAdapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listPlan.size
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title : TextView = itemView.findViewById(R.id.tvTitle)
        var desc : TextView = itemView.findViewById(R.id.tvDesc)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: PlanModel)
    }
}