package ru.shalkoff.rxjavaexample.observable.examples

import androidx.lifecycle.Lifecycle
import io.reactivex.rxjava3.core.Observable
import ru.shalkoff.rxjavaexample.base.BaseLifecycleObserver
import ru.shalkoff.rxjavaexample.observable.examples.filters.ContainsOneFilter

class ObservableExample6(lifecycle: Lifecycle) : BaseLifecycleObserver(lifecycle) {

    /**
     * Пример оператора filter
     * (если условие, которое написано выполняется, то поток событий идёт дальше по цепочке)
     */
    override fun run() {
        val observable = Observable.just("1", "21", "33", "44")
            .filter(ContainsOneFilter)

        observable.subscribe(getObserver())
    }
}