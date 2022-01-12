package com.febrian.core.core.data

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : com.febrian.core.core.data.Resource<T>(data)
    class Loading<T>(data: T? = null) : com.febrian.core.core.data.Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : com.febrian.core.core.data.Resource<T>(data, message)
}
