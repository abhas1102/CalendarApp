package com.example.calendarapp.datamodels

data class CreateTaskResponse(val task_id:Int, val task: TaskModel)
data class GetAllTaskResponse(val tasks:List<Tasks>)
data class Tasks(val task_id: Int, val task_detail: TaskModel)