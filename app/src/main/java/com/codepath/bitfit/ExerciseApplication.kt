package com.codepath.bitfit

import android.app.Application

class ExerciseApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}