package ru.shalkoff.rxjavaexample.observable

import androidx.lifecycle.Lifecycle
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.Disposable

class ObservableExample4(lifecycle: Lifecycle) : BaseLifecycleObserver(lifecycle) {

    /**
     * Создаем последовательность с помощью оператора create
     * Emmiter делаем глобальным, чтобы можно было генерировать события вне метода subscribe()
     */
    private var emmiter: ObservableEmitter<String>? = null
    private val disposable = Disposable.empty()

    override fun run() {
        val observableCreate = Observable.create(object : ObservableOnSubscribe<String> {

            override fun subscribe(emitter: ObservableEmitter<String>) {
                this@ObservableExample4.emmiter = emitter
                emmiter?.setDisposable(disposable)
                emitter.onNext("One")
                emitter.onNext("Two")
                emitter.onNext("Three")
            }

        })
        observableCreate.subscribe(getCommonObserver())
        emmiter?.let {
            it.onNext("Four")
            it.onComplete()

            // Это событие не отправиться, так как поток завершился (ранее был вызван onComplete())
            it.onNext("Five")
        }
    }
}