package com.example.calendarapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calendarapp.datamodels.CreateDeleteRequest
import com.example.calendarapp.datamodels.CreateTaskResponse
import com.example.calendarapp.datamodels.GetAllTaskResponse
import com.example.calendarapp.datamodels.TaskModel
import com.example.calendarapp.network.TaskRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel():ViewModel() {
    private val repository = TaskRepository()

    private val _taskCreated = MutableLiveData<Response<CreateTaskResponse>>()
    val taskCreated: MutableLiveData<Response<CreateTaskResponse>> = _taskCreated

    private val _taskLists = MutableLiveData<Response<GetAllTaskResponse>>()
    val taskLists: MutableLiveData<Response<GetAllTaskResponse>> = _taskLists

    fun createTask(task: TaskModel) {
        viewModelScope.launch {
            _taskCreated.value = repository.createTask(1,task)
        }
    }
    fun getTask(key:Int) {
        viewModelScope.launch {
            _taskLists.value = repository.getAllTasks(key)
        }
    }
    fun deleteTask(task: CreateDeleteRequest) {
        viewModelScope.launch {
            try {
                repository.deleteTask(task)
            } catch (e:Exception) {

            }
        }
    }
}