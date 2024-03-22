package com.example.calendarapp.network

import com.example.calendarapp.datamodels.CreateDeleteRequest
import com.example.calendarapp.datamodels.CreateTaskRequest
import com.example.calendarapp.datamodels.CreateTaskResponse
import com.example.calendarapp.datamodels.DeleteTask
import com.example.calendarapp.datamodels.GetAllTaskResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("api/storeCalendarTask")
    suspend fun createTask(@Body requestBody: CreateTaskRequest): Response<CreateTaskResponse>

    @FormUrlEncoded
    @POST("api/getCalendarTaskList")
    suspend fun getAllTasks(@Field("user_id") value:Int):Response<GetAllTaskResponse>


    @POST("/api/deleteCalendarTask")
    suspend fun deleteTask(@Body request: CreateDeleteRequest):Response<DeleteTask>
}