package ru.shalkoff.rxjavaexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.shalkoff.rxjavaexample.observable.ObservableExample2
import ru.shalkoff.rxjavaexample.observable.ObservableExample3
import ru.shalkoff.rxjavaexample.observable.ObservableExample4
import ru.shalkoff.rxjavaexample.observable.ObservableExample

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val observableExample: ObservableExample = ObservableExample4(lifecycle)
        runExample(observableExample)
    }

    private fun runExample(observableExample: ObservableExample) {
        observableExample.run()
    }
}