package com.example.practica01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

lateinit var loginButton: Button;
lateinit var registerButton: Button;
private lateinit var auth: FirebaseAuth;
private lateinit var authStateListener: FirebaseAuth.AuthStateListener

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        auth= FirebaseAuth.getInstance()
        loginButton=findViewById(R.id.loginButtonHome)
        registerButton=findViewById(R.id.registerButtonHome)

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, NewAccount::class.java)
            startActivity(intent)
        }
        this.checkUser()
    }

    override fun onResume() {
        super.onResume()
        if (::authStateListener.isInitialized) {
            auth.addAuthStateListener(authStateListener)
        }
    }

    override fun onPause() {
        super.onPause()
        if (::authStateListener.isInitialized) {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    private fun checkUser() {
        val user = auth.currentUser
        if (user != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
            finish()
        }
    }
}