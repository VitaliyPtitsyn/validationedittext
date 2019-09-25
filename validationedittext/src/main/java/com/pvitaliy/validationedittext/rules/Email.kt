package com.pvitaliy.validationtext.rules

import android.content.res.Resources
import android.text.TextUtils
import android.util.Patterns
import com.pvitaliy.validationedittext.R
import com.pvitaliy.validationtext.ValidationException

class ValidationEmailRule : ValidationRule {

    override fun validate(text: String?, res: Resources) {
        if (!(!TextUtils.isEmpty(text) && Patterns.EMAIL_ADDRESS.matcher(text).matches()))
            throw EmailException()
    }

    override fun equals(other: Any?): Boolean = other is ValidationEmailRule
    override fun hashCode(): Int = javaClass.hashCode()
}

class EmailException : ValidationException(reasonId = R.string.VET_wrong_email_format)