package com.example.findfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.findfood.ui.home.HomeFragment
import com.example.findfood.ui.notifications.NotificationsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
    }

}
