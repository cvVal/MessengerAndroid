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
import com.valpu.seve.messenger.signup.SignUpPresenter
import com.valpu.seve.messenger.signup.SignUpPresenterImpl
import com.valpu.seve.messenger.signup.SignUpView
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), SignUpView, View.OnClickListener {

    private lateinit var etUsername: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: SignUpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        presenter = SignUpPresenterImpl(this)
        presenter.preferences = AppPreferences.create(this)
        bindViews()
    }

    override fun bindViews() {
        etUsername      = et_username
        etPhoneNumber   = et_phone
        etPassword      = et_password
        btnSignUp       = btn_sign_up
        progressBar     = progress_bar
        btnSignUp.setOnClickListener(this)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun navigateToHome() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun setUsernameError() {
        etUsername.error = "Username field cannot be empty"
    }

    override fun setPhoneNumberError() {
        etPhoneNumber.error = "Phone number field cannot be empty"
    }

    override fun setPasswordError() {
        etPassword.error = "Password field cannot be empty"
    }

    override fun getContext(): Context {
        return this
    }

    override fun showSignUpError() {

        Toast.makeText(this, "An unexpected error ocurred. Please try again later.",
                Toast.LENGTH_LONG). show()
    }

    override fun showAuthError() {

        Toast.makeText(this, "An authorization error ocurred. Please try again later.",
                Toast.LENGTH_LONG). show()
    }

    override fun onClick(p0: View) {

        if (p0.id == R.id.btn_sign_up) {

            presenter.executeSignUp(
                    etUsername.text.toString(),
                    etPhoneNumber.text.toString(),
                    etPassword.text.toString())
        }
    }
}
