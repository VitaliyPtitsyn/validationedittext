package com.pvitaliy.validationtext.rules

import android.content.res.Resources
import android.text.TextUtils
import android.util.Patterns
import com.pvitaliy.validationedittext.rules.ErrorCodes.ERROR_CODE_EMAIL_PHORMAT
import com.pvitaliy.validationtext.ErrorCodeException

class ValidationEmailRule : ValidationRule {

    override fun validate(text: String?, res: Resources) {
        if (!(!TextUtils.isEmpty(text) && Patterns.EMAIL_ADDRESS.matcher(text).matches()))
            throw EmailException()
    }

    override fun equals(other: Any?): Boolean = other is ValidationEmailRule
    override fun hashCode(): Int = javaClass.hashCode()
}

class EmailException : ErrorCodeException(ERROR_CODE_EMAIL_PHORMAT)