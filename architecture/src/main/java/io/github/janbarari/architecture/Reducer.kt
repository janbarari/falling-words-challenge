package io.github.janbarari.architecture

fun interface Reducer<S> {
    fun reduce(state: S): S
}
