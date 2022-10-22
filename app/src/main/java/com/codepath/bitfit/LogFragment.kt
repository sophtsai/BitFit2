package com.codepath.bitfit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogFragment : Fragment() {
    private val exercises = mutableListOf<ExerciseEntity>()
    private lateinit var exerciseRecyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_log, container, false)
        val layoutManager = LinearLayoutManager(context)
        exerciseRecyclerView = view.findViewById(R.id.menu_recycler_view)
        exerciseRecyclerView.layoutManager = layoutManager
        exerciseRecyclerView.setHasFixedSize(true)
        exerciseAdapter = ExerciseAdapter(view.context, exercises)
        exerciseRecyclerView.adapter = exerciseAdapter

        val exerciseRecyclerView = view.findViewById<RecyclerView>(R.id.menu_recycler_view)
        val addExerciseBtn = view.findViewById<Button>(R.id.add_button)
        val removeAllBtn = view.findViewById<Button>(R.id.remove_button)
        exerciseRecyclerView.layoutManager = LinearLayoutManager(context).also {
            val dividerItemDecoration = DividerItemDecoration(context, it.orientation)
            exerciseRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        addExerciseBtn.setOnClickListener {
            Intent(context, DetailActivity::class.java).also {
                startActivity(it)
            }
        }

        removeAllBtn.setOnClickListener {
            lifecycleScope.launch (Dispatchers.IO) {
                (activity?.application as ExerciseApplication).db.exerciseDao().deleteAll()
            }
        }

        return view
    }

    private fun fetchFoodList() {
        lifecycleScope.launch {
            (activity?.application as ExerciseApplication).db.exerciseDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    ExerciseEntity(
                        entity.name,
                        entity.date,
                        entity.time,
                        entity.duration,
                        entity.calories
                    )
                }.also { mappedList ->
                    exercises.clear()
                    exercises.addAll(mappedList)
                    exerciseAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        fetchFoodList()
    }

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }
}