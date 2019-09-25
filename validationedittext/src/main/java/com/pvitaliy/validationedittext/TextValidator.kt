package com.pvitaliy.validationtext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.pvitaliy.validationedittext.ErrorMode
import com.pvitaliy.validationedittext.R
import com.pvitaliy.validationtext.rules.ValidationRule
import java.lang.ref.WeakReference
import java.util.concurrent.CopyOnWriteArraySet

class TextValidator : TextWatcher {


    constructor(editText: EditText, mode: ErrorMode = ErrorMode.None) {
        editTextWeek = WeakReference(editText)
        editText.addTextChangedListener(this)
        this.errorMode = mode
        validateInput(editText, mode == ErrorMode.Always)
    }

    private val editTextWeek: WeakReference<EditText>
    private val rules = CopyOnWriteArraySet<ValidationRule>()

    lateinit var validationResult: ValidateResult

    var callback: OnValidation? = null
    var errorMode: ErrorMode = ErrorMode.None


    fun setRules(validationRules: List<ValidationRule>?) {
        if (validationRules != null) {
            rules.clear()
            rules.addAll(validationRules)
        }
    }

    fun validateInput() {
        editTextWeek.get()?.let { edit ->
            validateInput(
                edit, when (errorMode) {
                    ErrorMode.None -> false
                    ErrorMode.OnUserInput -> edit.hasFocus()
                    ErrorMode.Always -> true
                    is ErrorMode.Once -> true
                }
            )
        }
    }

    fun validateInput(editText: EditText, showError: Boolean = false) {
        editText.error = null
        val text = editText.text?.toString() ?: ""
        var isValidText = true
        rules.forEach { rule ->
            try {
                rule.validate(text, editText.resources)
            } catch (e: ValidationException) {
                isValidText = false
                if (!showError) return@forEach
                editText.error = if (e.reason != null) e.reason
                else editText.context.getString(e.reasonId ?: R.string.VET_invalid_input)

            }
        }

        if (errorMode is ErrorMode.Once) {
            errorMode = (errorMode as ErrorMode.Once).nextMode
        }

        validationResult = ValidateResult(text, if (isValidText) text else "", isValidText)
        callback?.onValidation(validationResult)
    }

    override fun afterTextChanged(s: Editable?) {
        validateInput()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    fun putRule(rule: ValidationRule?) {
        if (rule != null) rules.add(rule)
    }
}

interface OnValidation {
    fun onValidation(validateResult: ValidateResult)
}