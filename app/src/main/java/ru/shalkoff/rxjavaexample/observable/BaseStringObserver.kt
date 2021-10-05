package ru.shalkoff.rxjavaexample.observable

import android.util.Log
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import ru.shalkoff.rxjavaexample.Constants

class BaseStringObserver(disposableList: CompositeDisposable){

    val observer: Observer<String> = object : Observer<String> {

        override fun onSubscribe(disposable: Disposable) {
            Log.d(Constants.LOGGER_TAG, Constants.ON_SUBSCRIBE)
            disposableList.add(disposable)
        }

        override fun onNext(element: String) {
            Log.d(Constants.LOGGER_TAG, element)
        }

        override fun onError(e: Throwable) {
            Log.d(Constants.LOGGER_TAG, Constants.ON_ERROR)
        }

        override fun onComplete() {
            Log.d(Constants.LOGGER_TAG, Constants.ON_COMPLETE)
        }
    }
}