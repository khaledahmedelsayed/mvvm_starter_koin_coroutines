package com.ibtikar.mvvm_starter_koin_coroutines

class ApplicationRunTimeException(
    val throwable: Throwable? = null,
    val errorType: ErrorType,
    val errorMessage: String? = null
) : RuntimeException()

sealed class ErrorType {
    sealed class Network : ErrorType() {
        object Unauthorized : Network()
        object ResourceNotFound : Network()
        object InternalServerError : Network()
        object Unexpected : Network()
        object NoInternetConnection : Network()
    }

    object Unexpected : ErrorType()
}
