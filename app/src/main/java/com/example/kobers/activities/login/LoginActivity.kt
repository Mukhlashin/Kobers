package com.example.kobers.activities.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.kobers.R
import com.example.kobers.activities.HomeActivity
import com.example.kobers.activities.register.RegisterActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_login)


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