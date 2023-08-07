package com.example.homework5android5.domain

sealed class Either<out L, out R> {

    data class Left<L>(val message: String) : Either<L, Nothing>()
    data class Right<R>(val data: R) : Either<Nothing, R>()
}