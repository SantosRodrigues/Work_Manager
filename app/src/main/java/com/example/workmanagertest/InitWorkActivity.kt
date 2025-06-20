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
        // Inicia execução única do work    ! Risco de acumulo de works
        WorkManager.getInstance(this).enqueue(periodicWorkRequest)

        // Inicia execução única do work
        WorkManager.getInstance(this).enqueueUniqueWork(
            workerNotificacao,                                // Nome do work
//            ExistingWorkPolicy.KEEP,                        // Caso exista: faz nada
//            ExistingWorkPolicy.APPEND,                      // Caso exista: linka um novo work que será executado após o work em execução
//            ExistingWorkPolicy.REPLACE,                     // Caso exista: cancela e inicia novo work
            ExistingWorkPolicy.APPEND_OR_REPLACE,             // Caso exista (não executado): linka um novo work
            // Caso exista (já executado): inicia um novo work
            oneTimeWorkRequest                                // Work
        )
    }

    fun iniciaWorkEmLoop() {
        // Verifica se já existe work com mesmo nome em execução
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            workerNotificacao,                                // Nome do work
            ExistingPeriodicWorkPolicy.KEEP,                  // Caso exista: faz nada
//          ExistingPeriodicWorkPolicy.UPDATE,                // Caso exista: atualizar parametros de entrada (mantém tempo e políticas)
//          ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,  // Caso exista: cancela e inicia novo work
            periodicWorkRequest                               // Work
        )
    }

    fun iniciarWorkEncadeado() {
        WorkManager.getInstance(this)
            .beginWith(firstWorkRequest)                      // Inicio da cadeia de works
            .then(secondWorkRequest)                          // Segundo que será executado
            .then(thirdWorkRequest)                           // Terceiro que será executado
            .enqueue()

        val priorityWorks = listOf(firstWorkRequest, secondWorkRequest)
        WorkManager.getInstance(this)
            .beginWith(priorityWorks)                         // lista de works serão executado em paralelo
            .then(thirdWorkRequest)                           // work que depende da lista
            .enqueue()
    }
}