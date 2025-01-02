package br.com.hellodev.hellojob.application

import android.app.Application
import br.com.hellodev.di.appModules
import br.com.hellodev.hellojob.home.di.homeModules
import br.com.hellodev.hellojob.search.di.searchModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModules, homeModules, searchModules)
        }
    }
}