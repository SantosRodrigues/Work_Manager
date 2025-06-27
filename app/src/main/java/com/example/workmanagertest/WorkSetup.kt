package com.example.workmanagertest

import androidx.work.BackoffPolicy
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import java.util.concurrent.TimeUnit

class WorkSetup {

    fun setupOneTimeWork(ativo: String) {
        val data = Data.Builder().putString("ativo", ativo).build()

        val work = OneTimeWorkRequestBuilder<WorkRequest>()
            .setInitialDelay(1L, TimeUnit.SECONDS)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 1L, TimeUnit.MINUTES)          //1,2,3,4,5
//                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(1))         //1,2,4,8,16
            .addTag("ping")
            .setInputData(data)
            .build()

        val uuid =
            work.id                                                                       //UUID do work
    }

    fun setupPeriodicWork(data: Data) =
        PeriodicWorkRequestBuilder<WorkRequest>(10L, TimeUnit.MINUTES)
            .setInitialDelay(1L, TimeUnit.SECONDS)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 1L, TimeUnit.MINUTES)
            .addTag("sync")
            .setInputData(data)
            .build()
}