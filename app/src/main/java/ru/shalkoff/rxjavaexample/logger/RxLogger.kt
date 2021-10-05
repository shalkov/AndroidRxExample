package ru.shalkoff.rxjavaexample.logger

import android.util.Log

/**
 * Логгер событий
 */
object RxLogger {

    fun log(message: String) {
        Log.d(Constants.LOGGER_TAG, message)
    }

    fun onSubscribe() {
        log(Constants.ON_SUBSCRIBE)
    }

    fun onError() {
        log(Constants.ON_ERROR)
    }

    fun onComplete() {
        log(Constants.ON_COMPLETE)
    }
}