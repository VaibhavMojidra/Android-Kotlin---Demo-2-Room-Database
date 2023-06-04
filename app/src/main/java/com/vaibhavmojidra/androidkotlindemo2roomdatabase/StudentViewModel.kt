package com.vaibhavmojidra.androidkotlindemo2roomdatabase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaibhavmojidra.androidkotlindemo2roomdatabase.db.Student
import com.vaibhavmojidra.androidkotlindemo2roomdatabase.db.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentViewModel(private val repository: StudentRepository) : ViewModel() {

    val students = repository.students

    val inputFirstName = MutableLiveData<String>()
    val inputLastName = MutableLiveData<String>()
    val inputClass = MutableLiveData<String>()

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearOrDeleteButtonText = MutableLiveData<String>()

    private var isUpdateAndDelete=false
    private lateinit var studentSelected: Student

    init {
        saveOrUpdateButtonText.value = "Save"
        clearOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if(isUpdateAndDelete){
            studentSelected.firstName= inputFirstName.value.toString()
            studentSelected.lastName= inputLastName.value.toString()
            studentSelected.classNumber= inputClass.value!!.toInt()
            update(studentSelected)
        }else{
            val firstName=inputFirstName.value!!
            val lastName=inputLastName.value!!
            val classNumber= inputClass.value!!.toInt()
            insert(Student(0,firstName,lastName,classNumber))
            inputFirstName.value=""
            inputLastName.value=""
            inputClass.value=""
        }

    }

    fun clearOrDelete() {
        if(isUpdateAndDelete){
            delete(studentSelected)
        }else{
            clearAll()
        }
    }

    fun insert(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(student)
    }

    fun delete(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(student)
        withContext(Dispatchers.Main){
            inputFirstName.value=""
            inputLastName.value=""
            inputClass.value=""
            isUpdateAndDelete=false
            saveOrUpdateButtonText.value="Save"
            clearOrDeleteButtonText.value="Clear All"
        }
    }

    fun update(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(student)
        withContext(Dispatchers.Main){
            inputFirstName.value=""
            inputLastName.value=""
            inputClass.value=""
            isUpdateAndDelete=false
            saveOrUpdateButtonText.value="Save"
            clearOrDeleteButtonText.value="Clear All"
        }
    }

    fun clearAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun initUpdateAndDelete(student: Student){
        inputFirstName.value=student.firstName
        inputLastName.value=student.lastName
        inputClass.value=student.classNumber.toString()
        isUpdateAndDelete=true
        studentSelected=student
        saveOrUpdateButtonText.value="Update"
        clearOrDeleteButtonText.value="Delete"
    }

}