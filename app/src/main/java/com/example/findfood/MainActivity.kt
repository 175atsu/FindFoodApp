package com.example.findfood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.findfood.ui.dashboard.DashboardFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)

        fab.setOnClickListener {
            val fragment = DashboardFragment()
//            val bundle = Bundle().apply {
//                //putString("URL", urlData.url)
//            }
//            fragment.setArguments(bundle)

            //navigateでactionの指定ぽい
            //navController.navigate(R.id.nav_host_fragment)


            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
        }
    }
}
