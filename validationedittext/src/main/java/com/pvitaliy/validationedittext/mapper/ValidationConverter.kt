package com.pvitaliy.validationedittext.mapper

import android.content.res.Resources
import com.pvitaliy.validationtext.ValidationException

interface ValidationConverter {

    fun convertError(resources: Resources, exc: ValidationException): String
}