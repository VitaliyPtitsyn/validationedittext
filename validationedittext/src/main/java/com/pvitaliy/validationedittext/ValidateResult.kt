package com.pvitaliy.validationtext

data class ValidateResult(
    val originalText: String,
    val validatedText: String = "",
    val isValid: Boolean = false
)