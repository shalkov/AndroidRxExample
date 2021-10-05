package ru.shalkoff.rxjavaexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.shalkoff.rxjavaexample.observable.ObservableExample
import ru.shalkoff.rxjavaexample.observable.examples.ObservableExample5

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val observableExample: ObservableExample = ObservableExample5(lifecycle)
        runExample(observableExample)
    }

    private fun runExample(example: ObservableExample) {
        example.run()
    }
}