package com.pvitaliy.validationtext.rules

import android.content.res.Resources
import com.pvitaliy.validationtext.ValidationException

interface ValidationRule {

    @Throws(ValidationException::class)
    fun validate(text: String, res: Resources)
}