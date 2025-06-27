package com.example.workmanagertest

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkRequest(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {
        val ativo = inputData.getString("ativo") ?: ""

        val httpsStatus = request(ativo)

        val data = Data.Builder().putInt("statusCode", httpsStatus).build()

        return if (httpsStatus in 200..<300) Result.success(data) else Result.failure(data)
    }

    private fun request(ativo: String): Int {
        if (ativo.isEmpty()) throw IllegalArgumentException("Ativo não informado")

        val endpoint = String.format("https://.../%s?token=%s", ativo, "akj4k3JSAk3")
        ///...
        return 200
    }
}