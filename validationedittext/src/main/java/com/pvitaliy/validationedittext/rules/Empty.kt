package com.pvitaliy.validationtext.rules

import android.content.res.Resources
import android.text.TextUtils
import com.pvitaliy.validationedittext.R
import com.pvitaliy.validationtext.ValidationException

class ValidationEmptyRule : ValidationRule {

    override fun validate(text: String?, res: Resources) {
        if (TextUtils.isEmpty(text)) throw EmptyException()
    }

    override fun equals(other: Any?): Boolean = other is ValidationEmailRule
    override fun hashCode(): Int = javaClass.hashCode()

}

class EmptyException : ValidationException(reasonId = R.string.VET_cant_be_empty)