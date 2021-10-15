package ru.shalkoff.rxjavaexample.observable.examples

import androidx.lifecycle.Lifecycle
import io.reactivex.rxjava3.core.Observable
import ru.shalkoff.rxjavaexample.base.BaseLifecycleObserver

class ObservableExample7(lifecycle: Lifecycle) : BaseLifecycleObserver(lifecycle) {

    /**
     * Пример работы оператора distinct
     *
     * Опытные программисты знают, что этот оператор сохраняет каждый уникальный элемент во
     * внутреннем списке, который прошел через Observable, и проверяет наличие новых
     * элементов в этом списке. Rx аккуратно скрывает такие вещи, вы должны быть в курсе,
     * что это может привести к значительным вычислительным затратам.
     */
    override fun run() {
        val observable = Observable.fromIterable(listOf("111", "221", "331", "444"))
            .distinct {
                // тут можно задать условие, по которому мы считаем, что элементы одинаковые
                //
                // Если последний элемент в строке где-то уже встречался,
                // этот элемент больше не будет эмитится
                it[it.lastIndex]
            }

        observable.subscribe(getObserver())
    }
}