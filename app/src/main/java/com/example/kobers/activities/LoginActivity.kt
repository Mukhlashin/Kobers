package com.example.kobers.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.kobers.R
import com.example.kobers.activities.HomeActivity
import com.example.kobers.activities.RegisterActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseAuthListener = FirebaseAuth.AuthStateListener{
        val user = firebaseAuth.currentUser?.uid
        if (user != null) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish() }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_login)

        setTextChangedListener(nama_toko_login, til_nama_toko_login)
        setTextChangedListener(password_login, til_password_login)
        progress_layout.setOnTouchListener { _ , _ -> true }

        login_button.setOnClickListener {
            onLogin()
        }

        txt_signup.setOnClickListener {
            onSignup()
        }
    }

    private fun setTextChangedListener(edt: EditText, til: TextInputLayout) {
        edt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }
            override fun beforeTextChanged(s: CharSequence? , start: Int, count: Int, after: Int) {

            }
            // ketika editText diubah memastikan TextInputLayout tidak menunjukkan pesan error
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til.isErrorEnabled = false
            }
        })
    }

    private fun onLogin() {
        var proceed = true
        if (nama_toko_login.text.isNullOrEmpty()) {
            til_nama_toko_login.error = "Required Email"
            til_nama_toko_login.isErrorEnabled = true
            proceed = false
        }
        if (password_login.text.isNullOrEmpty()) {
            til_password_login.error = "Required Password"
            til_password_login.isErrorEnabled = true
            proceed = false
        }

        if (proceed) {
            progress_layout.visibility = View.VISIBLE
            firebaseAuth.signInWithEmailAndPassword(
                nama_toko_login.text.toString(),
                password_login.text.toString()
            )
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        progress_layout.visibility = View.GONE
                        Toast.makeText(
                            this@LoginActivity,
                            "Login error: ${task.exception?.localizedMessage}",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    progress_layout.visibility = View.GONE
                    e.printStackTrace()
                }
        }
    }

    private fun onSignup() {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart() // method yang pertama kali dijalankan sebelum method lainnya
        firebaseAuth.addAuthStateListener(firebaseAuthListener)
    }

    override fun onStop() {
        super.onStop() // dijalankan jika proses dalam activity selesai atau dihentikan system
        firebaseAuth.removeAuthStateListener(firebaseAuthListener)
    }

//    private fun onLogin() {
//        var proceed = true
//        if (nama_toko_login.text.isNullOrEmpty()) {
//            til_email.error = "Required Email"
//            til_email.isErrorEnabled = true
//            proceed = false
//        }
//        if (password_login.text.isNullOrEmpty()) {
//            til_password.error = "Required Password"
//            til_password.isErrorEnabled = true
//            proceed = false
//        }
//
//        if (proceed) {
//            progress_layout.visibility = View.VISIBLE
//            firebaseAuth.signInWithEmailAndPassword(
//                nama_toko_login.text.toString(),
//                password_login.text.toString()
//            )
//                .addOnCompleteListener { task ->
//                    if (!task.isSuccessful) {
//                        progress_layout.visibility = View.GONE
//                        Toast.makeText(
//                            this@LoginActivity,
//                            "Login error: ${task.exception?.localizedMessage}",
//                            Toast.LENGTH_SHORT).show()
//                    }
//                }
//                .addOnFailureListener { e ->
//                    progress_layout.visibility = View.GONE
//                    e.printStackTrace()
//                }
//        }
//    }
//
//    private fun onSignup() {
//        startActivity(Intent(this, RegisterActivity::class.java))
//        finish()
//    }
//
//    override fun onStart() {
//        super.onStart()
//        firebaseAuth.addAuthStateListener(firebaseAuthListener)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        firebaseAuth.removeAuthStateListener(firebaseAuthListener)
//    }
}