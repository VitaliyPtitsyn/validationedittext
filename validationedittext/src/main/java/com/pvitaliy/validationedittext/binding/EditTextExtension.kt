package com.pvitaliy.validationtext.binding

import android.text.TextUtils
import android.widget.EditText
import com.pvitaliy.validationedittext.R
import com.pvitaliy.validationedittext.mapper.ValidationConvector
import com.pvitaliy.validationedittext.mapper.ValidationConvectorDefault
import com.pvitaliy.validationtext.TextValidator
import com.pvitaliy.validationtext.ValidateResult
import com.pvitaliy.validationtext.rules.ValidationRule


fun EditText.setValidator(
    convector: ValidationConvector,
    rules: List<ValidationRule>?,
    validateResult: ValidateResult?
) {
    val validator = getOrCreateValidator()
    validator.setRules(rules)
    validator.validationConvector = convector

    if (!TextUtils.equals(text, validateResult?.originalText))
        setText(validateResult?.originalText)
}


fun EditText.validate(showResult: Boolean = true): ValidateResult {
    val validator = getOrCreateValidator()
    validator.validateInput(this, showResult)
    return validator.validationResult
}

@Synchronized
fun EditText.getOrCreateValidator(): TextValidator {
    var validator = getTag(R.id.validation_tag_validator) as? TextValidator
    if (validator == null) {
        validator = TextValidator(this, ValidationConvectorDefault())
        setTag(R.id.validation_tag_validator, validator)
    }
    return validator
}