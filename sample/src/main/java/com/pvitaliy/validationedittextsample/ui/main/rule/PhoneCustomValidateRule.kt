package com.pvitaliy.validationedittextsample.ui.main.rule

import android.content.res.Resources
import android.util.Patterns
import com.pvitaliy.validationedittextsample.R
import com.pvitaliy.validationtext.ValidationException
import com.pvitaliy.validationtext.rules.ValidationRule

class PhoneCustomValidateRule : ValidationRule {
    override fun validate(text: String?, res: Resources) {
        if (!Patterns.PHONE.matcher(text).matches()) throw CustomReason()
    }

    override fun equals(other: Any?): Boolean = other is PhoneCustomValidateRule
    override fun hashCode(): Int = javaClass.hashCode()
}

class CustomReason : ValidationException(reasonId = R.string.custom_phone_error)