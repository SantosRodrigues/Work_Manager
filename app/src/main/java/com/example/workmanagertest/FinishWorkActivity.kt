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
    private val tagPing = "ping"

    fun encerraWork() {
        WorkManager.getInstance(this)
            .cancelAllWork()

        WorkManager.getInstance(this)
            .cancelAllWorkByTag(tagPing)

        WorkManager.getInstance(this)
            .cancelUniqueWork(workerNotificacao)
    }
}