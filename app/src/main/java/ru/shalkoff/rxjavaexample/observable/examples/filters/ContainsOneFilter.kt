package ru.shalkoff.rxjavaexample.observable.examples.filters

import io.reactivex.rxjava3.functions.Predicate

/**
 * Фильтр, который пропускает текст, который соджерит 1
 */
object ContainsOneFilter : Predicate<String> {

    override fun test(text: String): Boolean {
        return text.contains("1")
    }
}