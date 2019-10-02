package com.pvitaliy.validationedittext.mapper

import android.content.res.Resources
import com.pvitaliy.validationtext.ValidationException

interface ValidationConvector {

    fun convertError(resources: Resources, exc: ValidationException): String
}