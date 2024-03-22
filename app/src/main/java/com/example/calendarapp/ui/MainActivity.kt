package com.example.calendarapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.calendarapp.R

class MainActivity : AppCompatActivity() {

    lateinit var allTaskButton: Button
    lateinit var addTaskButton:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        allTaskButton = findViewById(R.id.checkAllTaskButton)
        addTaskButton = findViewById(R.id.addTaskButton)
        allTaskButton.setOnClickListener {
            val intent = Intent(this, AllTasksActivity::class.java)
            startActivity(intent)
        }
        addTaskButton.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }




    }



}