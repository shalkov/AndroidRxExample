package ru.shalkoff.rxjavaexample.subjects.examples

import androidx.lifecycle.Lifecycle
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.shalkoff.rxjavaexample.base.BaseLifecycleObserver

class SubjectExample3(lifecycle: Lifecycle) : BaseLifecycleObserver(lifecycle) {

    private val behaviorSubject1: BehaviorSubject<String> = BehaviorSubject.create()
    private val behaviorSubject2: BehaviorSubject<String> = BehaviorSubject.create()
    private val behaviorSubjectDefault: BehaviorSubject<String> =
        BehaviorSubject.createDefault("behaviorSubjectDefault T0")

    /**
     * Пример работы BehaviorSubject отправляется одно последние событие до подписки
     *
     * Так как роль BehaviorSubject – всегда иметь доступные данные,
     * считается неправильным создавать его без начального значения, также как и завершать его.
     */
    override fun run() {
        behaviorSubjectDefault.subscribe(getObserver())

        onNextAll("T1")
        onNextAll("T2")

        behaviorSubject1.subscribe(getObserver())

        onNextAll("T3")

        behaviorSubject2.subscribe(getObserver())
    }

    private fun onNextAll(message: String) {
        behaviorSubjectDefault.onNext("behaviorSubjectDefault $message")
        behaviorSubject1.onNext("behaviorSubject1 $message")
        behaviorSubject2.onNext("behaviorSubject2 $message")
    }
}