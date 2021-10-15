package ru.shalkoff.rxjavaexample.observable.examples

import androidx.lifecycle.Lifecycle
import io.reactivex.rxjava3.core.Observable
import ru.shalkoff.rxjavaexample.base.BaseLifecycleObserver

class ObservableExample8(lifecycle: Lifecycle) : BaseLifecycleObserver(lifecycle) {

    /**
     * distinctUntilChanged() скипает одинаковые последовательные элементы
     */
    override fun run() {
        val observable = Observable.fromIterable(listOf("1", "1", "2", "1", "1", "2"))
            .distinctUntilChanged()

        observable.subscribe(getObserver())
    }
}