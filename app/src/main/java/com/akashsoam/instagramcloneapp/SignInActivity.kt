package com.akashsoam.instagramcloneapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        findViewById<View>(R.id.signup_link_btn).setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}