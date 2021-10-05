package ru.shalkoff.rxjavaexample.subjects.examples

import androidx.lifecycle.Lifecycle
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.ReplaySubject
import ru.shalkoff.rxjavaexample.base.BaseLifecycleObserver
import java.util.concurrent.TimeUnit

class SubjectExample2(lifecycle: Lifecycle) : BaseLifecycleObserver(lifecycle) {

    private val replaySubject: ReplaySubject<String> = ReplaySubject.create()
    private val replaySizeSubject: ReplaySubject<String> = getReplayWithSize()
    private val replayTimeSubject: ReplaySubject<String> = getReplayWithTime()
    private val replayTimeSizeSubject: ReplaySubject<String> = getReplayWithTimeSize()

    /**
     * Пример работы с ReplaySubject с его разными состояниями.
     * - Обычный режим с кешем
     * - Режим с ограничением кол-ва событий
     * - Режим с ограничением по времени событий
     * - Режим где есть ограничение, как по кол-ву событий, так и по времени
     */
    override fun run() {
        onNextAll("T1")
        Thread.sleep(1000)
        onNextAll("T2")
        Thread.sleep(2000)
        onNextAll("T3")

        replaySubject.subscribe(getObserver())
        replaySizeSubject.subscribe(getObserver())
        replayTimeSubject.subscribe(getObserver())
        replayTimeSizeSubject.subscribe(getObserver())
    }

    private fun onNextAll(message: String) {
        replaySubject.onNext(message)
        replaySizeSubject.onNext(message)
        replayTimeSubject.onNext(message)
        replayTimeSizeSubject.onNext(message)
    }

    private fun getReplayWithSize(): ReplaySubject<String> {
        return ReplaySubject.createWithSize(2)
    }

    private fun getReplayWithTime(): ReplaySubject<String> {
        return ReplaySubject.createWithTime(
            2,
            TimeUnit.SECONDS,
            Schedulers.computation()
        )
    }

    private fun getReplayWithTimeSize(): ReplaySubject<String> {
        return ReplaySubject.createWithTimeAndSize(
            3,
            TimeUnit.SECONDS,
            Schedulers.computation(),
            2
        )
    }
}