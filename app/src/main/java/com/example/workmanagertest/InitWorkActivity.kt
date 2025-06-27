package com.example.workmanagertest

import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager

class InitWorkActivity : AppCompatActivity() {

    private val workerNotificacao = "notificacaoPreçoFIIs"
    private lateinit var periodicWorkRequest: PeriodicWorkRequest
    private lateinit var oneTimeWorkRequest: OneTimeWorkRequest
    private lateinit var firstWorkRequest: OneTimeWorkRequest
    private lateinit var secondWorkRequest: OneTimeWorkRequest
    private lateinit var thirdWorkRequest: OneTimeWorkRequest

    fun iniciaWork() {

        WorkManager.getInstance(this).enqueue(periodicWorkRequest)

        WorkManager.getInstance(this).enqueueUniqueWork(
            workerNotificacao,
            ExistingWorkPolicy.KEEP,
            oneTimeWorkRequest
        )
    }

    fun iniciaWorkEmLoop() {
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            workerNotificacao,
            ExistingPeriodicWorkPolicy.UPDATE,
            periodicWorkRequest
        )
    }

    fun iniciarWorkEncadeado() {
        WorkManager.getInstance(this)
            .beginWith(firstWorkRequest)
            .then(secondWorkRequest)
            .then(thirdWorkRequest)
            .enqueue()

        val priorityWorks = listOf(firstWorkRequest, secondWorkRequest)
        WorkManager.getInstance(this)
            .beginWith(priorityWorks)
            .then(thirdWorkRequest)
            .enqueue()
    }
}