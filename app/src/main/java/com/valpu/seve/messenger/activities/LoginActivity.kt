package com.valpu.seve.messenger.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.valpu.seve.messenger.R
import com.valpu.seve.messenger.data.local.AppPreferences
import com.valpu.seve.messenger.login.LoginPresenter
import com.valpu.seve.messenger.login.LoginPresenterImpl
import com.valpu.seve.messenger.login.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView, View.OnClickListener {

    private lateinit var presenter: LoginPresenter
    private lateinit var preferences: AppPreferences

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenterImpl(this)
        preferences = AppPreferences.create(this)

        bindViews()
    }

    override fun onClick(view: View) {

        if (view.id == R.id.btn_login) {
            presenter.executeLogin(etUsername.text.toString(), etPassword.text.toString())
        } else if (view.id == R.id.btn_sign_up) {
            navigateToSignUp()
        }
    }

    override fun showProgress() {

        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {

        progressBar.visibility = View.GONE
    }

    override fun setUsernameError() {

        etUsername.error = "Username field cannot be empty"
    }

    override fun setPasswordError() {

        etPassword.error = "Password field cannot be empty"
    }

    override fun navigateToSignUp() {

        startActivity(Intent(this, SignUpActivity::class.java))
    }

    override fun navigateToHome() {
        finish()
        startActivity((Intent(this, MainActivity::class.java)))
    }

    override fun bindViews() {
        etUsername  = et_username
        etPassword  = et_password
        btnLogin    = btn_login
        btnSignUp   = btn_sign_up
        progressBar = progress_bar
        btnLogin.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)
    }

    override fun getContext(): Context {
        return this
    }

    override fun showAuthError() {

        Toast.makeText(this, "Invalid username or password. Try again.", Toast.LENGTH_LONG).show()
    }
}
