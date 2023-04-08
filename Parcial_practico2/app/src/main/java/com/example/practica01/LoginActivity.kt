package com.example.practica01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

lateinit var registerRedirect: TextView
lateinit var emailInput: EditText
lateinit var passwordInput: EditText
lateinit var buttonLogin: Button
private lateinit var auth: FirebaseAuth;
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth= FirebaseAuth.getInstance()
        emailInput=findViewById(R.id.loginEmail)
        passwordInput=findViewById(R.id.loginPassword)
        buttonLogin=findViewById(R.id.loginButton)
        registerRedirect=findViewById(R.id.registerRedirect)
        registerRedirect.setOnClickListener {
            val intent = Intent(this, NewAccount::class.java)
            startActivity(intent)
        }
        buttonLogin.setOnClickListener {
            var email=emailInput.text.toString()
            var password=passwordInput.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("tag", "signInWithEmail:success")
                        val user = auth.currentUser
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("user", user)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("tag", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}