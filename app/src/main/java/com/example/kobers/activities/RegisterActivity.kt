package com.example.kobers.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.kobers.R
import com.example.kobers.utils.Constants.DATA_USERS
import com.example.kobers.utils.User
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    val firebaseDb = FirebaseFirestore.getInstance()
    val firebaseAuth = FirebaseAuth.getInstance()
    val firebaseAuthListener = FirebaseAuth.AuthStateListener {
        val user = firebaseAuth.currentUser?.uid
        if (user != null) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setTextChangedListener(nama_toko_regis, til_nama_toko_regis)
        setTextChangedListener(nomor_regis, til_nomor_regis)
        setTextChangedListener(password_regis, til_password_regis)
        progress_layout.setOnTouchListener { _, _ -> true }
        btn_register.setOnClickListener {
            onSignup()
        }
        txt_login.setOnClickListener {
            onLogin()
        }
    }

    private fun setTextChangedListener(edt: EditText, til: TextInputLayout) { edt.addTextChangedListener(object :
        TextWatcher {
        override fun afterTextChanged(s: Editable?) { }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { til.isErrorEnabled = false }
    })
    }

    private fun onLogin() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(firebaseAuthListener)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(firebaseAuthListener)
    }

    private fun onSignup() {
        var proceed = true
        if (nama_toko_regis.text.isNullOrEmpty()) {
            til_nama_toko_regis.error = "Required Name"
            til_nama_toko_regis.isErrorEnabled = true
            proceed = false
        }
        if (nomor_regis.text.isNullOrEmpty()) {
            til_nomor_regis.error = "Required Phone Number"
            til_nomor_regis.isErrorEnabled = true
            proceed = false
        }
        if (password_regis.text.isNullOrEmpty()) {
            til_password_regis.error = "Required Password"
            til_password_regis.isErrorEnabled = true
            proceed = false
        }

        if (proceed) {
            progress_layout.visibility = View.VISIBLE
            firebaseAuth.createUserWithEmailAndPassword(
                nomor_regis.text.toString(),
                password_regis.text.toString()
            )
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        progress_layout.visibility = View.GONE
                        Toast.makeText(
                            this@RegisterActivity,
                            "SignUp error: ${task.exception?.localizedMessage}",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (firebaseAuth.uid != null) {
                        val namaToko = nama_toko_regis.text.toString()
                        val phone = nomor_regis.text.toString()
                        val password = password_regis.text.toString()
                        val user = User(
                            namaToko,
                            phone,
                            password
                        )
                        firebaseDb.collection(DATA_USERS)
                            .document(firebaseAuth.uid!!).set(user)
                    }
                    progress_layout.visibility = View.GONE
                }

                .addOnFailureListener {
                    progress_layout.visibility = View.GONE
                    it.printStackTrace()
                }
        }
    }
}