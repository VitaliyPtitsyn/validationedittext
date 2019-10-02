package com.pvitaliy.validationtext

import androidx.annotation.StringRes


abstract class ValidationException : Exception()


open class ErrorCodeException(
    open val errorCode: Int,
    open vararg val args: Any?
) : ValidationException()


open class StringException(val reason: String) : ValidationException()

open class ResException(
    @StringRes val resoruceString: Int,
    vararg val args: Any?
) : ValidationException()