package com.akashsoam.instagramcloneapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        findViewById<View>(R.id.signin_link_btn).setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}