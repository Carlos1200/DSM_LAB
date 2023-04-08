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

lateinit var emailRegisterInput: EditText
lateinit var passwordRegisterInput: EditText
lateinit var repeatPasswordInput: EditText
lateinit var buttonRegister: Button
lateinit var loginRedirect: TextView
private lateinit var auth: FirebaseAuth
class NewAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_account)
        auth= FirebaseAuth.getInstance()
        emailRegisterInput=findViewById(R.id.registerEmail)
        passwordRegisterInput=findViewById(R.id.registerPassword)
        repeatPasswordInput=findViewById(R.id.registerConfirmPassword)
        buttonRegister=findViewById(R.id.registerButton)
        loginRedirect=findViewById(R.id.loginRedirect)
        loginRedirect.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        buttonRegister.setOnClickListener {
            var email=emailRegisterInput.text.toString()
            var password=passwordRegisterInput.text.toString()
            var repeatPassword=repeatPasswordInput.text.toString()
            if (email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password != repeatPassword){
                Log.i("tag", password)
                Log.i("tag", repeatPassword)

                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("tag", "createUserWithEmail:success")
                        val user = auth.currentUser
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("user", user)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("tag", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}