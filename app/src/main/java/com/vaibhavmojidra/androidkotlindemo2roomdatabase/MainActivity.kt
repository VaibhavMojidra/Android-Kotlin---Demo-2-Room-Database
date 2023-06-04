package com.vaibhavmojidra.androidkotlindemo2roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vaibhavmojidra.androidkotlindemo2roomdatabase.databinding.ActivityMainBinding
import com.vaibhavmojidra.androidkotlindemo2roomdatabase.db.Student
import com.vaibhavmojidra.androidkotlindemo2roomdatabase.db.StudentDAO
import com.vaibhavmojidra.androidkotlindemo2roomdatabase.db.StudentDatabase
import com.vaibhavmojidra.androidkotlindemo2roomdatabase.db.StudentRepository

class MainActivity : AppCompatActivity() {

    //Declaring data binding Obj
    private lateinit var binding: ActivityMainBinding

    //Declaring DAO via StudentDatabase, Repository, ViewModelFactory, ViewModel,
    private lateinit var dao:StudentDAO
    private lateinit var repository: StudentRepository
    private lateinit var viewModelFactory: StudentViewModelFactory
    private lateinit var viewModel: StudentViewModel
    private lateinit var adapter: MyRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initializing the data binding Obj
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Wiring Starts from here

        //Firstly initializing DAO via StudentDatabase
        dao = StudentDatabase.getInstance(application).studentDAO

        //Secondly initializing Repository
        repository= StudentRepository(dao)

        //Thirdly initializing ViewModelFactory
        viewModelFactory= StudentViewModelFactory(repository)

        //Fourthly initializing ViewModel
        viewModel=ViewModelProvider(this,viewModelFactory)[StudentViewModel::class.java]

        //Lastly Initializing binding.viewmodel with viewmodel obj
        binding.myViewModel=viewModel
        binding.lifecycleOwner=this

        binding.studentsRecyclerView.layoutManager=LinearLayoutManager(this)

        adapter=MyRecyclerViewAdapter({selectedItem:Student->listItemClicked(selectedItem)})
        binding.studentsRecyclerView.adapter=adapter


        displayStudentList()

    }

    private fun displayStudentList(){
        viewModel.students.observe(this) {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun listItemClicked(student: Student){
        viewModel.initUpdateAndDelete(student)
    }
}