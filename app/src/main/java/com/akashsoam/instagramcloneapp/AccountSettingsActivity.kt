package com.akashsoam.instagramcloneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class AccountSettingsActivity : AppCompatActivity() {
    lateinit var logoutButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_settings)

        logoutButton = findViewById(R.id.logout_btn_acc_sett)

        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this@AccountSettingsActivity, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}