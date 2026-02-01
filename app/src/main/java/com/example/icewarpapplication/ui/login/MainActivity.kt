package com.example.icewarpapplication.ui.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.icewarpapplication.R
import com.example.icewarpapplication.data.api.RetrofitClient
import com.example.icewarpapplication.data.db.AppDatabase
import com.example.icewarpapplication.data.repository.AuthRepository

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val repository = AuthRepository(
            RetrofitClient.api,
            AppDatabase(this)
        )

        viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return LoginViewModel(repository) as T
                }
            }
        )[LoginViewModel::class.java]

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // Default credentials
        etEmail.setText("testuser@mofa.onice.io")
        etPassword.setText("Password123456")

        btnLogin.setOnClickListener {
            viewModel.login(
                etEmail.text.toString(),
                etPassword.text.toString()
            )
        }

        viewModel.loginState.observe(this) { state ->
            when (state) {
                is LoginState.Loading ->
                    Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()

                is LoginState.Success ->
                    Toast.makeText(this, "Token Saved!", Toast.LENGTH_SHORT).show()

                is LoginState.Error ->
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}