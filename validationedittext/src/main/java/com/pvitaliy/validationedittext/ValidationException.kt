package com.pvitaliy.validationtext

import androidx.annotation.StringRes

/**
 * throw when validation failed. Than Text validation will catch and havlde the reason
 * String reason has a priority, but if both will be null. "Invalid" wil be shown
 */
open class ValidationException(
    var reason: String? = null,
    @StringRes var reasonId: Int? = null
) : Exception()