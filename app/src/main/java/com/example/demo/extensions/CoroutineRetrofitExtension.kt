package com.example.demo.extensions

import com.google.gson.GsonBuilder
import ru.gildor.coroutines.retrofit.ErrorResult
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.getOrNull

inline fun <T : Any> Result<T>.ifFailed(handler: (error:ErrorResult) -> Unit): Result<T> {
    if (this is ErrorResult) {handler(this)}
    return this
}

inline fun <T : Any> Result<T>.ifSucceeded(handler: (data: T,response:okhttp3.Response) -> Unit): Result<T> {
    (this as? Result.Ok)?.getOrNull()?.let { handler(it,response) }
    return this
}

inline fun <T : Any> Result<T>.ifError(handler: (code: Result.Error,response:okhttp3.Response) -> Unit): Result<T> {
    (this as? Result.Error)?.let {}
    return this
}

inline fun <T : Any> Result<T>.ifException(handler: (exception: Throwable) -> Unit): Result<T> {
    (this as? Result.Exception)?.exception?.let { handler(it) }
    return this
}



