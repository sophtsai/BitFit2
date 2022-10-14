package com.codepath.bitfit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter(private val context: Context, private val exercises: MutableList<ExerciseEntity>) :
    RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    override fun getItemCount() = exercises.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val activityTextView = itemView.findViewById<TextView>(R.id.activity_title)
        private val dateTextView = itemView.findViewById<TextView>(R.id.day_name)
        private val timeTextView = itemView.findViewById<TextView>(R.id.time_entry)
        private val durationTextView = itemView.findViewById<TextView>(R.id.duration_entry)
        private val calorieTextView = itemView.findViewById<TextView>(R.id.calorie_entry)

        fun bind(exercise: ExerciseEntity) {
            activityTextView.text = exercise.name
            dateTextView.text = exercise.date
            timeTextView.text = exercise.time
            durationTextView.text = exercise.duration.toString()
            calorieTextView.text = exercise.calories.toString()
        }

    }
}