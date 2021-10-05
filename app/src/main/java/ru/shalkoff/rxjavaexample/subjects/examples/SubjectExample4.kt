package ru.shalkoff.rxjavaexample.subjects.examples

import androidx.lifecycle.Lifecycle
import io.reactivex.rxjava3.subjects.AsyncSubject
import ru.shalkoff.rxjavaexample.base.BaseLifecycleObserver

class SubjectExample4(lifecycle: Lifecycle) : BaseLifecycleObserver(lifecycle) {

    private val asyncSubject: AsyncSubject<String> = AsyncSubject.create()

    override fun run() {
        asyncSubject.onNext("T1")
        asyncSubject.onNext("T2")
        asyncSubject.subscribe(getObserver())
        asyncSubject.onComplete() // Обязательно надо вызвать, иначе последнее событие не придёт
    }
}