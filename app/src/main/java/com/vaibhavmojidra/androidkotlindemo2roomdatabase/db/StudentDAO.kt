package com.vaibhavmojidra.androidkotlindemo2roomdatabase.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDAO {

    @Insert
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM Student_Data_Table")
    suspend fun deleteAll()

    @Query("SELECT * FROM Student_Data_Table")
    fun getAllStudents():LiveData<List<Student>>
}