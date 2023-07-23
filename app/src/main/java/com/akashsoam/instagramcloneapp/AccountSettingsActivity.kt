package com.akashsoam.instagramcloneapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.akashsoam.instagramcloneapp.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.util.Locale

class AccountSettingsActivity : AppCompatActivity() {
    private lateinit var logoutButton: Button
    private lateinit var firebaseUser: FirebaseUser
    private var checker = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_settings)


        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        logoutButton = findViewById(R.id.logout_btn_acc_sett)

        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this@AccountSettingsActivity, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
        findViewById<ImageView>(R.id.save_info_profile_imgview_btn).setOnClickListener {
            if (checker == "clicked") {
                TODO("complete it")
            } else {
                updateUserInfoOnly()
            }
        }

        getUserInfo()
    }

    private fun updateUserInfoOnly() {
        if (findViewById<TextView>(R.id.full_name_account_sett).text.toString() == "" || findViewById<TextView>(
                R.id.user_name_account_sett
            ).text.toString() == "" || findViewById<TextView>(R.id.bio_account_sett).text.toString() == ""
        ) {
            if (findViewById<TextView>(R.id.full_name_account_sett).text.toString() == "") {
                Toast.makeText(this, "Please enter full name", Toast.LENGTH_SHORT).show()
            }
            if (findViewById<TextView>(R.id.user_name_account_sett).text.toString() == "") {
                Toast.makeText(this, "Please enter user name", Toast.LENGTH_SHORT).show()
            }
            if (findViewById<TextView>(R.id.bio_account_sett).text.toString() == "") {
                Toast.makeText(this, "Please enter bio", Toast.LENGTH_SHORT).show()
            }
        } else {

            val usersRef =
                FirebaseDatabase.getInstance().reference.child("users")
            val userMap = HashMap<String, Any>()
            userMap["fullname"] =
                findViewById<TextView>(R.id.full_name_account_sett).text.toString()
                    .lowercase(Locale.getDefault())
            userMap["username"] =
                findViewById<TextView>(R.id.user_name_account_sett).text.toString()
                    .lowercase(Locale.getDefault())
            userMap["bio"] = findViewById<TextView>(R.id.bio_account_sett).text.toString()
                .lowercase(Locale.getDefault())

            usersRef.child(firebaseUser.uid).updateChildren(userMap)

            Toast.makeText(
                this@AccountSettingsActivity,
                "Account update successful",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(this@AccountSettingsActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getUserInfo() {
        val usersRef =
            FirebaseDatabase.getInstance().reference.child("users").child(firebaseUser.uid)
        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {
                    val user = dataSnapshot.getValue<User>(User::class.java)
                    Picasso.get().load(user!!.getImage()).placeholder(R.drawable.profile)
                        .into(findViewById<CircleImageView>(R.id.profile_image_view_acc_settings))
                    findViewById<TextView>(R.id.user_name_account_sett).text = user.getUsername()
                    findViewById<TextView>(R.id.full_name_account_sett).text = user.getFullname()
                    findViewById<TextView>(R.id.bio_account_sett).text = user.getBio()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

}