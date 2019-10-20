package com.pvitaliy.validationtext

data class ValidateResult(
    val originalText: String,
    val errorText: String? = null,
    val isValid: Boolean = false
)