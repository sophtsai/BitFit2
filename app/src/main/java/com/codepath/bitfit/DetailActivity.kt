package com.codepath.bitfit

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var startEditText: EditText
    private lateinit var durationEditText: EditText
    private lateinit var calorieEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Find the views for the screen
        nameEditText = findViewById(R.id.mediaImage)
        dateEditText = findViewById(R.id.mediaTitle)
        startEditText = findViewById(R.id.mediaByline)
        durationEditText = findViewById(R.id.mediaAbstract)

        // Get the extra from the Intent
        val article = intent.getSerializableExtra(ARTICLE_EXTRA) as DisplayArticle

        // Set the title, byline, and abstract information from the article
        dateEditText.text = article.headline
        startEditText.text = article.byline
        durationEditText.text = article.abstract

    }
}