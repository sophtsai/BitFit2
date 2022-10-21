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

    @Query("SELECT MAX(duration) FROM exercise_table")
    fun longDur():Float
    @Query("SELECT MIN(duration) FROM exercise_table")
    fun shortDur() :Float
    @Query("SELECT AVG(duration) FROM exercise_table")
    fun avgDur() :Float
    @Query("SELECT MAX(calories) FROM exercise_table")
    fun mostCal():Float
    @Query("SELECT MIN(calories) FROM exercise_table")
    fun leastCal() :Float
    @Query("SELECT AVG(calories) FROM exercise_table")
    fun avgCal() :Float

}