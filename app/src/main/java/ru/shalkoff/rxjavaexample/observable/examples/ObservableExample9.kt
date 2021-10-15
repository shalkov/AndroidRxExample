package ru.shalkoff.rxjavaexample.observable.examples

import androidx.lifecycle.Lifecycle
import io.reactivex.rxjava3.core.Observable
import ru.shalkoff.rxjavaexample.base.BaseLifecycleObserver

class ObservableExample9(lifecycle: Lifecycle) : BaseLifecycleObserver(lifecycle) {

    /**
     * ignoreElements() игнорирует все события, в onNext().
     * Реагирует только на onError() и on Complete()
     */
    override fun run() {
        val observable = Observable.just("11", "22", "33")
        //TODO доделать
        //observable.ignoreElements()

        observable.subscribe(getObserver())
    }
}