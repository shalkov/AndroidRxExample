package ru.shalkoff.rxjavaexample.observable.examples

import androidx.lifecycle.Lifecycle
import io.reactivex.rxjava3.core.Observable
import ru.shalkoff.rxjavaexample.observable.BaseLifecycleObserver
import java.util.concurrent.TimeUnit


class ObservableExample1(lifecycle: Lifecycle) : BaseLifecycleObserver(lifecycle) {

    /**
     * События отправляются каждую секунду
     */
    override fun run() {
        val observable = Observable.fromIterable(listOf("one", "two", "three"))
            .concatMap {
                Observable.just(it).delay(1L, TimeUnit.SECONDS)
            }
        observable.subscribe(getObserver())
    }
}

