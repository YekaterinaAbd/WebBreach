package com.example.webbreach

import android.app.Application
import androidx.work.*
import com.example.webbreach.data.di.apiModule
import com.example.webbreach.data.di.dataMapperModule
import com.example.webbreach.data.di.databaseModule
import com.example.webbreach.data.di.repositoryModule
import com.example.webbreach.domain.di.useCaseModule
import com.example.webbreach.presentation.di.modelMapperModule
import com.example.webbreach.presentation.di.viewModelModule
import com.example.webbreach.presentation.service.UploadQuotesWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BreachApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        delayedInit()
        startKoin {
            androidContext(this@BreachApplication)
            modules(
                databaseModule,
                apiModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
                dataMapperModule,
                modelMapperModule
            )
        }
    }

    private fun delayedInit() {
        applicationScope.launch {
            Timber.plant(Timber.DebugTree())
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            //.setRequiresDeviceIdle(true)
            .build()

        val repeatingRequest =
            PeriodicWorkRequestBuilder<UploadQuotesWorker>(15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            UploadQuotesWorker.WORK_NAME, ExistingPeriodicWorkPolicy.REPLACE, repeatingRequest
        )
    }
}