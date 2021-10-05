package ru.shalkoff.rxjavaexample

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.shalkoff.rxjavaexample.Consts.LOGGER_TAG
import ru.shalkoff.rxjavaexample.Consts.ON_COMPLETE
import ru.shalkoff.rxjavaexample.Consts.ON_ERROR
import ru.shalkoff.rxjavaexample.Consts.ON_SUBSCRIBE
import java.lang.NullPointerException
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

/**
 * Пример Rx цепочек с использованием Observable
 */
class ObservableExample1 : LifecycleObserver {

    private var disposable: Disposable? = null
    private val observer: Observer<String> = object : Observer<String> {

        override fun onSubscribe(disposable: Disposable) {
            Log.d(LOGGER_TAG, ON_SUBSCRIBE)
            this@ObservableExample1.disposable = disposable
        }

        override fun onNext(element: String) {
            Log.d(LOGGER_TAG, element)
        }

        override fun onError(e: Throwable) {
            Log.d(LOGGER_TAG, ON_ERROR)
        }

        override fun onComplete() {
            Log.d(LOGGER_TAG, ON_COMPLETE)
        }
    }

    /**
     * События отправляются каждую секунду
     */
    fun runExample1() {
        val observable = Observable.fromIterable(listOf("one", "two", "three"))
            .concatMap {
                Observable.just(it).delay(1L, TimeUnit.SECONDS)
            }
        observable.subscribe(observer)
    }

    /**
     * События отправляются каждую секунду,
     * при этом если будет ошибка, поток событий будет повторяться 3 раза
     */
    fun runExample2() {
        val observable = Observable.fromIterable(listOf("one", "two", "three"))
            .concatMap {
                if (it == "three") {
                    throw NullPointerException()
                }
                Observable.just(it).delay(1L, TimeUnit.SECONDS)
            }
            .retry(3)
        observable.subscribe(observer)
    }

    /**
     * Выполняем код метода(который гипотетически тяжелый) в io потоке,
     * а результат получаем в main треде.
     * Перед этим обернули метод в класс, который реализует интерфейс Callable
     */
    fun runExample3() {
        val observable = Observable.fromCallable(CallableLongAction("123"))
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
        observable.subscribe(object : Observer<Int> {

            override fun onSubscribe(disposable: Disposable) {
                Log.d(LOGGER_TAG, ON_SUBSCRIBE)
                this@ObservableExample1.disposable = disposable
            }

            override fun onNext(t: Int) {
                Log.d(LOGGER_TAG, Thread.currentThread().name)
                Log.d(LOGGER_TAG, t.toString())
            }

            override fun onError(e: Throwable) {
                Log.d(LOGGER_TAG, ON_ERROR)
            }

            override fun onComplete() {
                Log.d(LOGGER_TAG, ON_COMPLETE)
            }

        })
    }

    /**
     * Создаем последовательность с помощью оператора create
     * Emmiter делаем глобальным, чтобы можно было генерировать события вне метода subscribe()
     */
    private var emmiter: ObservableEmitter<String>? = null
    fun runExample4() {
        val observableCreate = Observable.create(object : ObservableOnSubscribe<String> {

            override fun subscribe(emitter: ObservableEmitter<String>) {
                this@ObservableExample1.emmiter = emitter
                emmiter?.setDisposable(disposable)
                emitter.onNext("One")
                emitter.onNext("Two")
                emitter.onNext("Three")
            }

        })
        observableCreate.subscribe(observer)
        emmiter?.let {
            it.onNext("Four")
            it.onComplete()

            // Это событие не отправиться, так как поток завершился (ранее был вызван onComplete())
            it.onNext("Five")
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {

        disposable?.dispose()
    }
}

class CallableLongAction(private val data: String) : Callable<Int> {

    override fun call(): Int {
        return longAction(data)
    }
}

private fun longAction(text: String): Int {
    try {
        Log.d(LOGGER_TAG, Thread.currentThread().name)
        TimeUnit.SECONDS.sleep(3)
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
    return text.toInt()
}

