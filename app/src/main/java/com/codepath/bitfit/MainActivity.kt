package com.codepath.bitfit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val exercise = mutableListOf<ExerciseEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exerciseRecyclerView = findViewById<RecyclerView>(R.id.menu_recycler_view)
        val addExerciseBtn = findViewById<Button>(R.id.add_button)
        val removeAllBtn = findViewById<Button>(R.id.remove_button)
        exerciseRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            exerciseRecyclerView.addItemDecoration(dividerItemDecoration)
        }
        val exerciseAdapter = ExerciseAdapter(this, exercise)
        exerciseRecyclerView.adapter = exerciseAdapter

        addExerciseBtn.setOnClickListener {
            Intent(this, DetailActivity::class.java).also {
                startActivity(it)
            }
        }

        lifecycleScope.launch {
            (application as ExerciseApplication).db.exerciseDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    ExerciseEntity(
                        entity.name,
                        entity.date,
                        entity.time,
                        entity.duration,
                        entity.calories
                    )
                }.also { mappedList ->
                    exercise.clear()
                    exercise.addAll(mappedList)
                    exerciseAdapter.notifyDataSetChanged()
                }
            }
        }

        removeAllBtn.setOnClickListener {
            lifecycleScope.launch (IO) {
                (application as ExerciseApplication).db.exerciseDao().deleteAll()
            }
        }
    }
}