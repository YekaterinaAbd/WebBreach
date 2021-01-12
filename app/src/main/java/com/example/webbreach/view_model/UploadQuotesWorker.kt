package com.example.webbreach.view_model

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.webbreach.repository.QuoteRepository
import com.example.webbreach.utils.Outcome
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UploadQuotesWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams), KoinComponent {

    companion object {
        const val WORK_NAME = "upload_quotes_work"
    }

    private val repository: QuoteRepository by inject()

    override suspend fun doWork(): Result =
        when (repository.processQuote()) {
            Outcome.SUCCESS -> Result.success()
            Outcome.FAILURE -> Result.retry()
        }
}
