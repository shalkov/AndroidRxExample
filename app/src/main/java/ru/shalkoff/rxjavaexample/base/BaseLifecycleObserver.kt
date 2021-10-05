package ru.shalkoff.rxjavaexample.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Базовый класс с жизненым циклом
 */
abstract class BaseLifecycleObserver(
    lifecycle : Lifecycle
): LifecycleObserver, ObservableExample {

    init {
        registerLifecycle(lifecycle)
    }

    private val disposableList = CompositeDisposable()
    private val stringObserver = BaseStringObserver(disposableList)

    private fun registerLifecycle(lifecycle : Lifecycle){
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        disposableList.dispose()
    }

    fun addDisposables(disposable: Disposable) {
        disposableList.addAll(disposable)
    }

    fun getObserver(): Observer<String> {
        return stringObserver.observer
    }
}