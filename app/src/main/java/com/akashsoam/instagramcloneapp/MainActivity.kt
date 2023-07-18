package com.akashsoam.instagramcloneapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.akashsoam.instagramcloneapp.databinding.ActivityMainBinding
import com.akashsoam.instagramcloneapp.fragments.HomeFragment
import com.akashsoam.instagramcloneapp.fragments.NotificationsFragment
import com.akashsoam.instagramcloneapp.fragments.ProfileFragment
import com.akashsoam.instagramcloneapp.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    internal var selectedFragment: Fragment? = null

//    private lateinit var textView: TextView

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
//                    textView.text = "Home"
//                moveToFragment(HomeFragment())
//                    return@OnNavigationItemSelectedListener true
                    selectedFragment = HomeFragment()
                }

                R.id.nav_search -> {
//                    textView.setText("Search")
//                moveToFragment(SearchFragment())
//                    return@OnNavigationItemSelectedListener true
                    selectedFragment = SearchFragment()

                }

                R.id.nav_add_post -> {
//                    textView.setText("Add post")
                    item.isChecked = false
//                startActivity(Intent(this@MainActivity, AddPostActivity::class.java))
                    return@OnNavigationItemSelectedListener true
//                    selectedFragment = HomeFragment()

                }

                R.id.nav_notifications -> {
//                    textView.setText("Notifications")
//                moveToFragment(NotificationsFragment())
//                    return@OnNavigationItemSelectedListener true
                    selectedFragment = NotificationsFragment()

                }

                R.id.nav_profile -> {
//                    textView.setText("Profile")
//                moveToFragment(ProfileFragment())
//                    return@OnNavigationItemSelectedListener true
                    selectedFragment = ProfileFragment()
                }
            }

            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment!!)
                    .commit()
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        textView = findViewById(R.id.message)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment())
            .commit()
    }
}