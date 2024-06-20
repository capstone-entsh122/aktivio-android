package com.bangkit.aktivio.modules.exercise

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.aktivio.R

class PlanAdapter(private var activities: List<String>) : RecyclerView.Adapter<PlanAdapter.ActivityViewHolder>() {

    private val TAG = "PlanAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plan, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.bind(activities[position])
        Log.d(TAG, "Binding activity at position $position: ${activities[position]}")
    }

    override fun getItemCount(): Int = activities.size

    fun updateActivities(newActivities: List<String>) {
        activities = newActivities
        notifyDataSetChanged()
        Log.d(TAG, "Updated activities: $activities")
    }

    class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val activityTextView: TextView = itemView.findViewById(R.id.activity_text_view)

        fun bind(activity: String) {
            activityTextView.text = activity
        }
    }
}
