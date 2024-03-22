package com.example.calendarapp.ui
import kotlinx.coroutines.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import com.example.calendarapp.datamodels.CreateDeleteRequest
import com.example.calendarapp.viewmodel.MainViewModel
import com.example.calendarapp.R
import com.google.android.material.snackbar.Snackbar



class TaskDetailActivity : AppCompatActivity() {
    lateinit var removeButton: Button
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)
        val taskTitleText:TextView = findViewById(R.id.taskTitle)
        val taskTitleDescription:TextView = findViewById(R.id.taskDescription)

        val taskTitle = intent.getStringExtra("taskTitle")
        val taskDescription = intent.getStringExtra("taskDescription")
        val taskId = intent.getIntExtra("taskId",0)

        taskTitleText.text = taskTitle
        taskTitleDescription.text = taskDescription
        removeButton = findViewById(R.id.removeTaskButton)
        removeButton.setOnClickListener {
            Log.d("taskId", taskId.toString())
            viewModel.deleteTask(CreateDeleteRequest(taskId,1))
            val snackbar = Snackbar.make(findViewById(android.R.id.content), "Item deleted", Snackbar.LENGTH_SHORT)
            snackbar.show()

            val scope = CoroutineScope(Dispatchers.Main)
            scope.launch {
                delay(1000)
                finish()
            }

        }

    }
}