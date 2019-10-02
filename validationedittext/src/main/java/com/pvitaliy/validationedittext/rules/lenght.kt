package com.pvitaliy.validationtext.rules

import android.content.res.Resources
import com.pvitaliy.validationedittext.rules.ErrorCodes.ERROR_CODE_TEXT_MAX
import com.pvitaliy.validationedittext.rules.ErrorCodes.ERROR_CODE_TEXT_MIN
import com.pvitaliy.validationtext.ErrorCodeException

data class ValidationLengthRule(val min: Int?, val max: Int?) : ValidationRule {

    override fun validate(text: String?, res: Resources) {
        val finalText = text ?: ""
        min?.let {
            if (finalText.length < it) throw
            ErrorCodeException(ERROR_CODE_TEXT_MIN, it)
        }
        max?.let {
            if (finalText.length > it) throw
            ErrorCodeException(ERROR_CODE_TEXT_MAX, it)
        }
    }

    override fun equals(other: Any?): Boolean = other is ValidationLengthRule
    override fun hashCode(): Int = javaClass.hashCode()
}
