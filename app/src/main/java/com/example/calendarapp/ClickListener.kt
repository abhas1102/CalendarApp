package com.example.calendarapp

import com.example.calendarapp.datamodels.Tasks

interface ClickListener {
    fun onClick(task: Tasks)
}