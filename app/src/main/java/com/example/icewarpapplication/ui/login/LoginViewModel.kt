package com.example.icewarpapplication.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icewarpapplication.data.repository.AuthRepository
import com.example.icewarpapplication.utils.ValidationUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()
    val loginState = MutableLiveData<LoginState>()

    fun login(username: String, password: String) {

        // Validation
        when {
            username.isEmpty() || password.isEmpty() ->
                loginState.value = LoginState.Error("Fields cannot be empty")

            !ValidationUtils.isValidEmail(username) ->
                loginState.value = LoginState.Error("Invalid email")

            !ValidationUtils.isValidPassword(password) ->
                loginState.value = LoginState.Error("Password must be 6+ characters")

            else -> {
                loginState.value = LoginState.Loading

                repository.login(username, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        viewModelScope.launch {
                            repository.saveToken(response.token)
                            loginState.value = LoginState.Success(response.token)
                        }
                    }, { error ->
                        loginState.value =
                            LoginState.Error(error.message ?: "Login failed")
                    }).also {
                        disposables.add(it)
                    }
            }
        }
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}
