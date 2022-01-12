package com.febrian.core.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.febrian.core.core.data.Resource
import com.febrian.core.core.data.source.remote.network.ApiResponse
import com.febrian.core.core.data.source.remote.network.ApiService
import com.febrian.core.core.data.source.remote.response.GithubResponse
import com.febrian.core.core.data.source.remote.response.ListGithubResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource(private val apiService: ApiService) {
    @SuppressLint("CheckResult")
    fun getAllUsers(): Flowable<ApiResponse<List<ListGithubResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<ListGithubResponse>>>()

        val client = apiService.getUsers()
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                resultData.onNext(if (response.isNotEmpty()) ApiResponse.Success(response) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("Remote Data Source", error.message.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getDetail(username: String): Flowable<Resource<GithubResponse>>{
        val resultData = PublishSubject.create<Resource<GithubResponse>>()

        val client = apiService.getDetailUser(username)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                resultData.onNext(Resource.Success(response))
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
                Log.e("Remote Data Source", error.message.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}