package com.vaibhavmojidra.androidkotlindemo2roomdatabase


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaibhavmojidra.androidkotlindemo2roomdatabase.db.StudentRepository
import java.lang.IllegalArgumentException

class StudentViewModelFactory(private val repository: StudentRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StudentViewModel::class.java)){
            return StudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}