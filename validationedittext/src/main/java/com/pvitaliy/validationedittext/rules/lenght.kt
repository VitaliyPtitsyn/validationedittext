package com.pvitaliy.validationtext.rules

import android.content.res.Resources
import com.pvitaliy.validationedittext.R
import com.pvitaliy.validationtext.ValidationException

data class ValidationLengthRule(val min: Int?, val max: Int?) : ValidationRule {

    override fun validate(text: String?, res: Resources) {
        val finalText = text ?: ""
        min?.let {
            if (finalText.length < it) throw
            LengthException(res.getString(R.string.VET_wrong_min_format, it))
        }
        max?.let {
            if (finalText.length > it) throw
            LengthException(res.getString(R.string.VET_wrong_max_format, it))
        }
    }

    override fun equals(other: Any?): Boolean = other is ValidationLengthRule
    override fun hashCode(): Int = javaClass.hashCode()
}

class LengthException(reason: String) : ValidationException(reason = reason)