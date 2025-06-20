package com.example.workmanagertest

import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.UUID

class FinishWorkActivity : AppCompatActivity() {

    private val workerNotificacao = "notificacaoPreçoFIIs"
    private lateinit var periodicWorkRequest: PeriodicWorkRequest
    private lateinit var oneTimeWorkRequest: OneTimeWorkRequest
    private lateinit var firstWorkRequest: OneTimeWorkRequest
    private lateinit var secondWorkRequest: OneTimeWorkRequest
    private lateinit var thirdWorkRequest: OneTimeWorkRequest


    fun encerraWork() {
        WorkManager.getInstance(this)
            .cancelAllWork()

        WorkManager.getInstance(this)
            .cancelUniqueWork(workerNotificacao)

        WorkManager.getInstance(this)
            .cancelWorkById(UUID.randomUUID())
    }
}