package io.github.janbarari.architecture

abstract class UseCaseNoInput<O> {
    abstract suspend fun execute(): O
}
