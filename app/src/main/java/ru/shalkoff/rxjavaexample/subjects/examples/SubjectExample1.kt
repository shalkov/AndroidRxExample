package ru.shalkoff.rxjavaexample.subjects.examples

import androidx.lifecycle.Lifecycle
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.shalkoff.rxjavaexample.base.BaseLifecycleObserver

class SubjectExample1(lifecycle: Lifecycle):  BaseLifecycleObserver(lifecycle) {

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    /**
     * Пример работы publishSubject
     */
    override fun run() {
        publishSubject.onNext("Test1")
        publishSubject.subscribe(getObserver())
        publishSubject.onNext("Test2")

        publishSubject.onComplete()
        publishSubject.onNext("Test3")
    }

}