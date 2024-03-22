package com.example.calendarapp.datamodels

data class CreateTaskRequest(val user_id:Int, val task: TaskModel)
data class CreateDeleteRequest(val task_id:Int, val user_id: Int)
