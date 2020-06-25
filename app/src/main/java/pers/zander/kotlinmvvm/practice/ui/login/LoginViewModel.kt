package pers.zander.kotlinmvvm.practice.ui.login

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pers.zander.kotlinmvvm.practice.CoroutinesDispatcherProvider
import pers.zander.kotlinmvvm.practice.checkResult
import pers.zander.kotlinmvvm.practice.model.bean.User
import pers.zander.kotlinmvvm.practice.model.repository.LoginRepository
import pers.zander.mvvmcore.base.BaseViewModel

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class LoginViewModel @ViewModelInject constructor(val repository: LoginRepository, val provider: CoroutinesDispatcherProvider) : BaseViewModel(){
    val userName = ObservableField<String>("")
    val passWord = ObservableField<String>("")

    private val _uiState = MutableLiveData<LoginUIState<User>>()
    val uiState : LiveData<LoginUIState<User>>
        get() = _uiState

    private fun isInputValid(username: String, password: String) = username.isNotBlank() && password.isNotBlank()

    fun loginDataChanged(){
        _uiState.value = LoginUIState(enableLoginButton = isInputValid(userName.get()?:"", passWord.get()?: ""))
    }

    fun login(){
        launchOnUI {
            if (userName.get().isNullOrBlank() || passWord.get().isNullOrBlank()) {
                _uiState.value = LoginUIState(enableLoginButton = false)
                return@launchOnUI
            }

            _uiState.value = LoginUIState(isLoading = true)

            val result = repository.login(userName.get()?:"", passWord.get()?:"")

            result.checkResult(
                onSuccess = {
                    _uiState.value = LoginUIState(isSuccess = it, enableLoginButton = true)
                },
                onError = {
                    _uiState.value = LoginUIState(isError = it, enableLoginButton = true)
                }
            )
        }
    }

    fun register() {
        viewModelScope.launch(provider.computation) {
            if (userName.get().isNullOrBlank() || passWord.get().isNullOrBlank()) return@launch

            withContext(provider.main) { _uiState.value = LoginUIState(isLoading = true) }

            val result = repository.register(userName.get() ?: "", passWord.get() ?: "")

            result.checkResult({
                _uiState.value = LoginUIState(isSuccess = it, enableLoginButton = true)
            }, {
                _uiState.value = LoginUIState(isError = it, enableLoginButton = true)
            })
        }
    }

    val verifyInput: (String) -> Unit = { loginDataChanged() }
}