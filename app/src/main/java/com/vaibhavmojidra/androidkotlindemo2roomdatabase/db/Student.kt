package com.vaibhavmojidra.androidkotlindemo2roomdatabase.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Student_Data_Table")
data class Student(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sid")
    val id:Int,

    @ColumnInfo(name = "First_Name")
    var firstName:String,

    @ColumnInfo(name = "Last_Name")
    var lastName:String,

    @ColumnInfo(name = "Class")
    var classNumber:Int
)
