package com.pvitaliy.validationedittextsample.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pvitaliy.validationedittext.ErrorMode
import com.pvitaliy.validationedittextsample.ui.main.rule.PhoneCustomValidateRule
import com.pvitaliy.validationtext.ValidateResult

class MainViewModel : ViewModel() {

    val livePhone = MutableLiveData<String>()
    val phoneValidationRules = listOf(PhoneCustomValidateRule())

    val liveName = MutableLiveData<ValidateResult>()
    val liveEmail = MutableLiveData<ValidateResult>()
    val livePassword = MutableLiveData<ValidateResult>()

    val liveShowOnEdit = MutableLiveData<ErrorMode>(ErrorMode.None)

    fun singIn() {
        if (isValid()) {

        } else {
            liveShowOnEdit.postValue(ErrorMode.Once(ErrorMode.OnUserInput))
        }
    }

    private fun isValid(): Boolean = (liveName.value?.isValid ?: false) and
            (liveEmail.value?.isValid ?: false) and
            (livePassword.value?.isValid ?: false)

}
