package ru.shalkoff.rxjavaexample.observable.examples

import androidx.lifecycle.Lifecycle
import io.reactivex.rxjava3.core.Observable
import ru.shalkoff.rxjavaexample.base.BaseLifecycleObserver
import ru.shalkoff.rxjavaexample.logger.RxLogger

class ObservableExample5(lifecycle: Lifecycle) : BaseLifecycleObserver(lifecycle) {

    /**
     * defer не создает новый Observable, пока мы на него не подпишемся.
     *
     * В примере видно, хоть мы и поменяли значение переменной до подписки(сделали: "000"),
     * для just всё-равно используется изначальное значение(оно равно: "1")
     */
    override fun run() {
        var one: String = "1"

        val observableJust = Observable.just(one, "2", "3")
        val observableDefer = Observable.defer {
            Observable.just(one, "2", "3")
        }

        one = "000" // Меняем значение переменной

        observableJust.subscribe {
            RxLogger.log(it)
        }
        observableDefer.subscribe {
            RxLogger.log(it)
        }
        one = "0001" // Меняем значение переменной ещё раз
        observableDefer.subscribe {
            RxLogger.log(it)
        }
    }
}