package com.example.telekilogram.data.core

sealed class Either <out L, out R> {//Определеям sealed class Either, который может быть либо ошибкой, либо успешным результатом.
    data class Error<L>(val error: L) : Either<L, Nothing>()
    data class Success<R>(val data: R) : Either<Nothing, R>()
}