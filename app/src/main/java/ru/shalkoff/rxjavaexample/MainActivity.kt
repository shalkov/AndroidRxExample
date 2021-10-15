package ru.shalkoff.rxjavaexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.shalkoff.rxjavaexample.base.ObservableExample
import ru.shalkoff.rxjavaexample.observable.examples.*
import ru.shalkoff.rxjavaexample.subjects.examples.SubjectExample4

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val observableExample: ObservableExample = ObservableExample9(lifecycle)
        runExample(observableExample)
    }

    private fun runExample(example: ObservableExample) {
        example.run()
    }
}