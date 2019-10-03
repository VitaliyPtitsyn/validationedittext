package com.pvitaliy.validationedittext.mapper

import android.content.res.Resources
import com.pvitaliy.validationedittext.R
import com.pvitaliy.validationedittext.rules.ErrorCodes.ERROR_CODE_EMAIL_FORMAT
import com.pvitaliy.validationedittext.rules.ErrorCodes.ERROR_CODE_EMPTY
import com.pvitaliy.validationedittext.rules.ErrorCodes.ERROR_CODE_SGW
import com.pvitaliy.validationedittext.rules.ErrorCodes.ERROR_CODE_TEXT_EQUYLITY
import com.pvitaliy.validationedittext.rules.ErrorCodes.ERROR_CODE_TEXT_MAX
import com.pvitaliy.validationedittext.rules.ErrorCodes.ERROR_CODE_TEXT_MIN
import com.pvitaliy.validationtext.ErrorCodeException
import com.pvitaliy.validationtext.ResException
import com.pvitaliy.validationtext.StringException
import com.pvitaliy.validationtext.ValidationException

/**
 * Convert erorr
 */
open class ValidationConvectorDefault : ValidationConvector {


    /**
     * Error code map
     * Key = error code
     * Value - String res value
     */
    val codeMap = HashMap<Int, Int>()

    init {
        codeMap[ERROR_CODE_SGW] = R.string.VET_invalid_input
        codeMap[ERROR_CODE_EMPTY] = R.string.VET_cant_be_empty
        codeMap[ERROR_CODE_EMAIL_FORMAT] = R.string.VET_wrong_email_format
        codeMap[ERROR_CODE_TEXT_EQUYLITY] = R.string.VET_not_equal_text
        codeMap[ERROR_CODE_TEXT_MIN] = R.string.VET_wrong_min_format
        codeMap[ERROR_CODE_TEXT_MAX] = R.string.VET_wrong_max_format
    }

    override fun convertError(resources: Resources, exc: ValidationException): String =
        when (exc) {
            is ErrorCodeException -> {
                val args = exc.args
                getFromMap(resources, exc.errorCode, *args)
            }
            is ResException -> resources.getString(exc.resString, exc.args)
            is StringException -> exc.reason
            else -> getFromMap(resources, ERROR_CODE_SGW)
        }


    protected fun getFromMap(resources: Resources, errorCode: Int, vararg args: Any?): String {
        if (!codeMap.containsKey(errorCode))
            throw RuntimeException("Error code $errorCode has been forgotten to the codeMap")
        return resources.getString(codeMap[errorCode]!!, *args)
    }
}