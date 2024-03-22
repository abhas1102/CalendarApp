package com.example.calendarapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.calendarapp.viewmodel.MainViewModel
import com.example.calendarapp.R
import com.example.calendarapp.datamodels.TaskModel

class AddTaskActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)


        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val titleEditText:EditText = findViewById(R.id.titleEditText)
            val title = titleEditText.text.toString()
            val descriptionEditText:EditText = findViewById(R.id.descriptionEditText)
            val description = descriptionEditText.text.toString()
//            val dueDate = intent.getStringExtra("selectedDate")

            val task = TaskModel(null,title, description)
            viewModel.createTask(task)
        }

        viewModel.taskCreated.observe(this, Observer { response ->
            if (response.isSuccessful) {
                val responseBody = response.body()
                Toast.makeText(this, "Task created successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AllTasksActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val errorMessage = response.message()
                Toast.makeText(this, "Failed to create task: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        })

    }
}