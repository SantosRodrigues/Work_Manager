package com.example.workmanagertest

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.work.WorkManager
import java.util.UUID

class InfoWork() {

    fun observeInfoWork(context: Context, workUUID: UUID, owner: LifecycleOwner) {
        WorkManager.getInstance(context)
            .getWorkInfoByIdLiveData(workUUID)
            .observe(owner) { info ->
                if (info != null && info.state.isFinished) Log.i(
                    TAG,
                    "${info.outputData.getString("statusCode")}"
                )
            }
    }

    companion object {
        private val TAG = InfoWork::class.java.simpleName
    }
}