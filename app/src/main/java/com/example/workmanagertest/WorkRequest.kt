package com.example.workmanagertest

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkRequest(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {
        return Result.success()
//        return Result.failure()
//        return Result.retry()
//        return Result.failure(Data)
//        return Result.success(Data)
    }
}