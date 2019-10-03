package com.pvitaliy.validationtext.binding

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.pvitaliy.validationedittext.ErrorMode
import com.pvitaliy.validationedittext.ErrorModeConstant
import com.pvitaliy.validationedittext.R
import com.pvitaliy.validationedittext.mapper.ValidationConvector
import com.pvitaliy.validationedittext.mapper.ValidationConvectorDefault
import com.pvitaliy.validationtext.OnValidation
import com.pvitaliy.validationtext.ValidateResult
import com.pvitaliy.validationtext.rules.*

@BindingAdapter(
    value = ["VET_validation_result", "VET_validation_converter", "VET_validationRules"],
    requireAll = false
)
fun EditText.setValidation(
    validateResult: ValidateResult?,
    convector: ValidationConvector?,
    rules: List<ValidationRule>?
) {
        setValidator(convector ?: ValidationConvectorDefault(), rules, validateResult)

}

@BindingAdapter(
    value = ["VET_validation_text", "VET_validation_converter", "VET_validationRules"],
    requireAll = false
)
fun EditText.setValidation(
    text: String?,
    convector: ValidationConvector?,
    rules: List<ValidationRule>?
) {
        setValidator(
            convector ?: ValidationConvectorDefault(),
            rules,
            ValidateResult(text ?: "")
        )

}


@InverseBindingAdapter(attribute = "VET_validation_result", event = "VET_texAttributeChange")
fun EditText.getResult(): ValidateResult {
    val validator = getOrCreateValidator()
    return validator.validationResult
}

@InverseBindingAdapter(attribute = "VET_validation_text", event = "VET_texAttributeChange")
fun EditText.getTextResult(): String {
    val validator = getOrCreateValidator()
    return validator.validationResult.originalText;
}


@BindingAdapter("VET_texAttributeChange")
fun EditText.setInverseBindingListener(listener: InverseBindingListener?) {
    val validator = getOrCreateValidator()
    if (listener == null) {
        validator.callback = null
    } else {
        validator.callback = object : OnValidation {
            override fun onValidation(validateResult: ValidateResult) {
                if (getTag(R.id.validation_tag_result) != validateResult) {
                    setTag(R.id.validation_tag_result, validateResult)
                    listener.onChange()
                }
            }
        }
    }
}

@BindingAdapter("VET_show_error_mode")
fun EditText.setMode(mode: ErrorMode) {
    val validator = getOrCreateValidator()
    validator.errorMode = mode
    validator.validateInput()
}

@BindingAdapter("VET_show_error_mode")
fun EditText.setMode(mode: ErrorModeConstant) {
    val validator = getOrCreateValidator()
    validator.errorMode = mode.mode
    validator.validateInput()
}

@BindingAdapter(value = ["VET_validation_min", "VET_validation_max"], requireAll = false)
fun EditText.setValidtionLength(min: Int?, max: Int?) {
    val validator = getOrCreateValidator()
    validator.putRule(ValidationLengthRule(min, max))
}

@BindingAdapter("VET_validation_content")
fun EditText.setValidationContent(contentValidation: ContentValidation) {
    val validator = getOrCreateValidator()
    validator.putRule(
        when (contentValidation) {
            ContentValidation.EMAIL -> ValidationEmailRule()
            ContentValidation.NOT_EMPTY -> ValidationEmptyRule()
        }
    )
}

@BindingAdapter("VET_validation_rule")
fun EditText.setCustomRule(rule: ValidationRule) {
    val validator = getOrCreateValidator()
    validator.putRule(rule)
    validator.validateInput()
}

@BindingAdapter("VET_validate_equal")
fun EditText.setValidateEqual(view: TextView) {
    val validator = getOrCreateValidator()
    validator.putRule(ValidationEqualRule(view))
    if (view is EditText) {
        view.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validator.validateInput()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}

