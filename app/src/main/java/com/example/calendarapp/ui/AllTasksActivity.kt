package com.example.calendarapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.ClickListener
import com.example.calendarapp.viewmodel.MainViewModel
import com.example.calendarapp.R
import com.example.calendarapp.adapter.TaskAdapter
import com.example.calendarapp.datamodels.Tasks

class AllTasksActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val listOfTasks = arrayListOf<Tasks>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_tasks)

       /* val dummyTasks = listOf(
            TaskModel("Task 1","Task 1 is good"),
            TaskModel("Task 2","Task 2 is good"),
            TaskModel("Task 3", "Task 3 is good")
        )*/
        val allTaskRecylerView:RecyclerView = findViewById(R.id.allTaskRecyclerView)
        viewModel.getTask(1)
        viewModel.taskLists.observe(this, Observer{ response ->
            if (response.isSuccessful) {
                val responseBody = response.body()
                Toast.makeText(this,"List Fetched", Toast.LENGTH_SHORT).show()
                Log.d("taskList",responseBody.toString())
                listOfTasks.addAll(responseBody!!.tasks)
                allTaskRecylerView.adapter = TaskAdapter(listOfTasks,object : ClickListener {
                    override fun onClick(task: Tasks) {
                        val intent = Intent(this@AllTasksActivity, TaskDetailActivity::class.java)
                        intent.putExtra("taskTitle",task.task_detail.title)
                        intent.putExtra("taskDescription",task.task_detail.description)
                        intent.putExtra("taskId",task.task_id)
//                        Log.d("taskId",taskId.toString())
                        startActivity(intent)
                    }

                })

            } else {

                val errorMessage = response.message()
                Toast.makeText(this, "Failed to fetch list: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        })


    }

}