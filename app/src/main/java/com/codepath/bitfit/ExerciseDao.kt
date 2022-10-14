package com.codepath.bitfit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise_table")
    fun getAll(): Flow<List<ExerciseEntity>>

    @Insert
    fun insertAll(articles: List<ExerciseEntity>)

    @Insert
    fun insert(exerciseItem: ExerciseEntity)

    @Query("DELETE FROM exercise_table")
    fun deleteAll()
}