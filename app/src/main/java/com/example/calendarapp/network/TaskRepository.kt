package com.example.calendarapp.network

import com.example.calendarapp.datamodels.CreateDeleteRequest
import com.example.calendarapp.datamodels.CreateTaskRequest
import com.example.calendarapp.datamodels.CreateTaskResponse
import com.example.calendarapp.datamodels.DeleteTask
import com.example.calendarapp.datamodels.GetAllTaskResponse
import com.example.calendarapp.datamodels.TaskModel
import okhttp3.ResponseBody
import retrofit2.Response

class TaskRepository {

    private val taskApiService: ApiService = RetrofitInstance.taskApiService
    suspend fun createTask(userId:Int, task: TaskModel): Response<CreateTaskResponse> {
        val requestBody = CreateTaskRequest(userId, task)
        return try {
            taskApiService.createTask(requestBody)
        } catch (e: Exception) {
            e.printStackTrace()
            val errorBody = ResponseBody.create(null, "Failed to create task: ${e.message}")
            Response.error(500, errorBody)
        }
    }
    suspend fun getAllTasks(key:Int):Response<GetAllTaskResponse> {
        return try {
            taskApiService.getAllTasks(key)
        } catch (e:Exception) {
            e.printStackTrace()
            val errorBody = ResponseBody.create(null,"Failed to get list: ${e.message}")
            Response.error(500,errorBody)
        }
    }

    suspend fun deleteTask(request: CreateDeleteRequest):Response<DeleteTask> {
        return try {
            taskApiService.deleteTask(request)
        } catch (e:Exception) {
            e.printStackTrace()
            val errorBody = ResponseBody.create(null,"Failed to delete: ${e.message}")
            Response.error(500,errorBody)
        }
    }
}