package com.khaledahmedelsayed.mvvmwithstatestarter.common

import com.khaledahmedelsayed.mvvmwithstatestarter.common.extensions.removeCharacterOccurrences
import com.khaledahmedelsayed.mvvmwithstatestarter.common.types.ErrorType

class ApplicationRunTimeException(
    val errorType: ErrorType,
    var errorMessage: String? = null,
    val errorsContent: HashMap<String, Any>? = null
) : RuntimeException() {

    fun getContentFormatted(): String {
        return this.errorsContent?.values
            .toString()
            .replace(",", "\n")
            .removeCharacterOccurrences('[')
            .removeCharacterOccurrences(']')
    }

    fun getDisplayError() =
        if (errorsContent.isNullOrEmpty()) errorMessage else getContentFormatted()
}
