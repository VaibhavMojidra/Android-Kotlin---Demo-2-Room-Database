package com.vaibhavmojidra.androidkotlindemo2roomdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vaibhavmojidra.androidkotlindemo2roomdatabase.databinding.StudentItemBinding
import com.vaibhavmojidra.androidkotlindemo2roomdatabase.db.Student

class MyRecyclerViewAdapter(private val clicklistener:(Student)->Unit): RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    private val studentList=ArrayList<Student>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding:StudentItemBinding=DataBindingUtil.inflate(layoutInflater,R.layout.student_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(studentList[position],clicklistener)
    }

    fun setList(studentList: List<Student>){
        this.studentList.clear()
        this.studentList.addAll(studentList)
    }


    class MyViewHolder(val binding:StudentItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(student: Student,listener:(Student)->Unit){
            binding.firstnameTextView.text=student.firstName
            binding.lastnameTextView.text=student.lastName
            binding.classTextView.text=student.classNumber.toString()
            binding.studentItemCard.setOnClickListener {
                listener(student)
            }
        }

    }

}