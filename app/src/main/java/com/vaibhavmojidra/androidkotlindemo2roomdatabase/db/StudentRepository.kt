package com.vaibhavmojidra.androidkotlindemo2roomdatabase.db

class StudentRepository(private val dao: StudentDAO) {

    val students=dao.getAllStudents()

    suspend fun insert(student: Student){
        dao.insertStudent(student)
    }

    suspend fun update(student: Student){
        dao.updateStudent(student)
    }

    suspend fun delete(student: Student){
        dao.deleteStudent(student)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}