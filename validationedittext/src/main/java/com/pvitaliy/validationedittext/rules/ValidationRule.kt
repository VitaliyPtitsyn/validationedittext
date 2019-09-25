package com.pvitaliy.validationtext.rules

import android.content.res.Resources

interface ValidationRule {

    @Throws
    fun validate(text: String?, res: Resources)
}