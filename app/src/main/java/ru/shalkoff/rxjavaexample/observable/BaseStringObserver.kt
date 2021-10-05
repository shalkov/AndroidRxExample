package ru.shalkoff.rxjavaexample.observable

import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import ru.shalkoff.rxjavaexample.Constants

class BaseStringObserver(disposableList: CompositeDisposable){

    val observer: Observer<String> = object : Observer<String> {

        override fun onSubscribe(disposable: Disposable) {
            RxLogger.onSubscribe()
            disposableList.add(disposable)
        }

        override fun onNext(element: String) {
            RxLogger.log(element)
        }

        override fun onError(e: Throwable) {
            RxLogger.onError()
        }

        override fun onComplete() {
            RxLogger.onComplete()
        }
    }
}