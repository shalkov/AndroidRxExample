package ru.shalkoff.rxjavaexample.observable.examples

import androidx.lifecycle.Lifecycle
import io.reactivex.rxjava3.core.Observable
import ru.shalkoff.rxjavaexample.base.BaseLifecycleObserver
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit

class ObservableExample2(lifecycle: Lifecycle) : BaseLifecycleObserver(lifecycle) {

    /**
     * События отправляются каждую секунду,
     * при этом если будет ошибка, поток событий будет повторяться 3 раза
     */
    override fun run() {
        val observable = Observable.fromIterable(listOf("one", "two", "three"))
            .concatMap {
                if (it == "three") {
                    throw NullPointerException()
                }
                Observable.just(it).delay(1L, TimeUnit.SECONDS)
            }
            .retry(3)
        observable.subscribe(getObserver())
    }

}