package com.pvitaliy.validationtext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.pvitaliy.validationedittext.ErrorMode
import com.pvitaliy.validationedittext.mapper.ValidationConverter
import com.pvitaliy.validationtext.rules.ValidationRule
import java.lang.ref.WeakReference
import java.util.concurrent.CopyOnWriteArraySet

class TextValidator(
    editText: EditText,
    var validationConverter: ValidationConverter,
    mode: ErrorMode = ErrorMode.None
) : TextWatcher {

    private val editTextWeek: WeakReference<EditText> = WeakReference(editText)
    private val rules = CopyOnWriteArraySet<ValidationRule>()

    lateinit var validationResult: ValidateResult

    var callback: OnValidation? = null
    var errorMode: ErrorMode = mode


    init {
        editText.addTextChangedListener(this)
        validateInput(editText, mode == ErrorMode.Always)
    }

    fun setRules(validationRules: List<ValidationRule>) {
        rules.clear()
        rules.addAll(validationRules)
    }

    fun validateInput(ignoreFocus: Boolean = false) {
        editTextWeek.get()?.let { edit ->
            validateInput(
                edit, when (errorMode) {
                    ErrorMode.None -> false
                    ErrorMode.OnUserInput -> edit.hasFocus() || ignoreFocus
                    ErrorMode.Always -> true
                    is ErrorMode.Once -> true
                }
            )
        }
    }

    fun validateInput(editText: EditText, shouldShowError: Boolean = false) {
        hideError()
        var errorText: String? = null
        val resources = editText.context.resources
        val text = editText.text.toString()
        var isValidText = true

        rules.forEach { rule ->
            try {
                rule.validate(text, editText.resources)
            } catch (e: ValidationException) {
                isValidText = false
                errorText = validationConverter.convertError(resources, e)
                return@forEach
            }
        }

        showErrorOnView(errorText, shouldShowError)


        if (errorMode is ErrorMode.Once) {
            errorMode = (errorMode as ErrorMode.Once).nextMode
        }

        validationResult = ValidateResult(text, errorText, isValidText)
        callback?.onValidation(validationResult)
    }

    private fun showErrorOnView(errorText: String?, shouldShowError: Boolean) =
        editTextWeek.get()?.let { editText ->
            if (!shouldShowError) return@let

            if (editText.parent?.parent is TextInputLayout && (editText.parent?.parent as TextInputLayout).isErrorEnabled) {
                (editText.parent?.parent as TextInputLayout).error = errorText
            } else {
                editText.error = errorText
            }
        }


    private fun hideError() = editTextWeek.get()?.let { editText ->
        if (editText.parent?.parent is TextInputLayout && (editText.parent?.parent as TextInputLayout).isErrorEnabled) {
            (editText.parent?.parent as TextInputLayout).error = null
        } else {
            editText.error = null
        }
    }

    override fun afterTextChanged(s: Editable) {
        validateInput()
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    }

    fun putRule(rule: ValidationRule) {
        rules.add(rule)
    }
}

interface OnValidation {
    fun onValidation(validateResult: ValidateResult)
}