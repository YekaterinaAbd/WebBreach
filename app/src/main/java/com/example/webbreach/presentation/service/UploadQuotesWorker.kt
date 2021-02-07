package com.example.webbreach.presentation.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.webbreach.domain.use_case.GetProcessQuoteUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UploadQuotesWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams), KoinComponent {

    companion object {
        const val WORK_NAME = "upload_quotes_work"
    }

    private val getProcessQuoteUseCase: GetProcessQuoteUseCase by inject()

    override suspend fun doWork(): Result =
        when (getProcessQuoteUseCase.invoke()) {
            is com.example.webbreach.domain.Result.Success -> Result.success()
            is com.example.webbreach.domain.Result.Error -> Result.retry()
        }
}
