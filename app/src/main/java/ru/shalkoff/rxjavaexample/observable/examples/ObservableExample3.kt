package ru.shalkoff.rxjavaexample.observable.examples

import android.util.Log
import androidx.lifecycle.Lifecycle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.shalkoff.rxjavaexample.logger.Constants
import ru.shalkoff.rxjavaexample.base.BaseLifecycleObserver
import ru.shalkoff.rxjavaexample.logger.RxLogger
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

class ObservableExample3(lifecycle: Lifecycle) : BaseLifecycleObserver(lifecycle) {

    /**
     * Выполняем код метода(который гипотетически тяжелый) в io потоке,
     * а результат получаем в main треде.
     * Перед этим обернули метод в класс, который реализует интерфейс Callable
     */
    override fun run() {
        val observable = Observable.fromCallable(CallableLongAction("123"))
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
        observable.subscribe(object : Observer<Int> {

            override fun onSubscribe(disposable: Disposable) {
                RxLogger.onSubscribe()
                addDisposables(disposable)
            }

            override fun onNext(t: Int) {
                RxLogger.log(Thread.currentThread().name)
                RxLogger.log(t.toString())
            }

            override fun onError(e: Throwable) {
                RxLogger.onError()
            }

            override fun onComplete() {
                RxLogger.onComplete()
            }
        })
    }

    class CallableLongAction(private val data: String) : Callable<Int> {

        override fun call(): Int {
            return longAction(data)
        }

        private fun longAction(text: String): Int {
            try {
                Log.d(Constants.LOGGER_TAG, Thread.currentThread().name)
                TimeUnit.SECONDS.sleep(3)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return text.toInt()
        }
    }
}