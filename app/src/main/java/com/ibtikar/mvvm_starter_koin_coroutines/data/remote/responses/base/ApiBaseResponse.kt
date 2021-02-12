package com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses.base

data class ApiBaseResponse<T>(
    val message: String? = null,
    val data: T? = null,
    val errors: HashMap<String, Any>? = null
)
