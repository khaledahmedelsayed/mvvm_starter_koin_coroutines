package com.khaledahmedelsayed.mvvmwithstatestarter.common.types

@Suppress("MagicNumber")
interface ErrorType {
    sealed class Network : ErrorType {
        enum class ClientSide(val code: Int? = null) : ErrorType {
            BadRequest(400),
            Unauthorized(401),
            Forbidden(403),
            ResourceNotFound(404),
            NotAllowedMethod(405),
            UnprocessableEntity(422),
            UpgradeRequired(426),
            BadBaseUrl,
            NoInternetConnection;

            companion object {
                private val map by lazy { values().associateBy(Network.ClientSide::code) }
                fun fromStatusCode(code: Int) = map[code]
                fun contains(code: Int) = map.containsKey(code)
            }
        }

        object ServerSideError : Network() {
            val codeRange = 500..Int.MAX_VALUE
        }
        object UnexpectedResponseCode : Network()
    }

    object UnexpectedErrorType : ErrorType
}
