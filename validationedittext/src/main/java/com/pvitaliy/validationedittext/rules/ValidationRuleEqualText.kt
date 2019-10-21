package com.pvitaliy.validationtext.rules

import android.content.res.Resources
import android.text.TextUtils
import android.widget.TextView
import com.pvitaliy.validationedittext.rules.ErrorCodes.ERROR_CODE_TEXT_EQUYLITY
import com.pvitaliy.validationtext.ErrorCodeException

class ValidationEqualRule(private val view: TextView) : ValidationRule {

    override fun validate(text: String, res: Resources) {
        if (!TextUtils.equals(text, view.text)) throw EqualException()
    }

    override fun equals(other: Any?): Boolean = other is ValidationEqualRule

    override fun hashCode(): Int = javaClass.hashCode()
}

class EqualException : ErrorCodeException(ERROR_CODE_TEXT_EQUYLITY)