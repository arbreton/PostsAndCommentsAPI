package com.example.demo

import android.app.Application
import com.example.demo.adapters.PostCommentsAdapter
import com.example.demo.adapters.PostsAdapter
import com.example.demo.network.ApiModule
import com.example.demo.network.NetworkModule
import com.example.demo.repo.PostRepo
import com.example.demo.viewmodels.PostsViewModel
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(module {
            single{ NetworkModule().getBaseClient() }
            single("PostsAPI") { ApiModule(get()).postsApi() }
            single { PostRepo(get()) }
            factory { (listener: PostsAdapter.PostActionInterface) -> PostsAdapter(listener) }
            factory { PostCommentsAdapter() }
        }))
        StandAloneContext.loadKoinModules(listOf(module {
            viewModel { PostsViewModel(get()) }
        }))
    }
}