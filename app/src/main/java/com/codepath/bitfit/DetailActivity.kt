package com.codepath.bitfit

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val TAG = "DetailActivity"
class DetailActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var startEditText: EditText
    private lateinit var durationEditText: EditText
    private lateinit var calorieEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val logExerciseBtn = findViewById<Button>(R.id.save_button)
        logExerciseBtn.setOnClickListener {
            nameEditText = findViewById(R.id.edit_activity_name)
            dateEditText = findViewById(R.id.edit_date)
            startEditText = findViewById(R.id.edit_start_time)
            durationEditText = findViewById(R.id.edit_duration)
            calorieEditText = findViewById(R.id.edit_calories_burned)

            lifecycleScope.launch(IO){
                (application as ExerciseApplication).db.exerciseDao().insert(
                    ExerciseEntity(
                        name = nameEditText.text.toString(), date = dateEditText.text.toString(), time = startEditText.text.toString(),
                        duration = durationEditText.text.toString().toInt(), calories =  calorieEditText.text.toString().toInt())
                )
            }
            super.finish()
        }
    }
}