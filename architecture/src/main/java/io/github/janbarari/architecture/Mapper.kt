package io.github.janbarari.architecture

interface Mapper<L, R> {
    fun map(left: L): R
}
