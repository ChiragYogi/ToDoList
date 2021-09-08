package com.example.todolistapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.todolistapp.databinding.ActivityMainBinding
import com.example.todolistapp.reciver.AlarmReceiver
import com.example.todolistapp.utiles.utills

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    /*private lateinit var reciver:  AlarmReceiver*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        
       val navHostFragment =
           supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment)    as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)


       /* // dynamic reciver
        reciver = AlarmReceiver()

        IntentFilter(utills.ACTION_SET_EXACT).also {
            registerReceiver(reciver,it)
        }*/





    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()

    }

   /* override fun onStop() {
        super.onStop()
        unregisterReceiver(reciver)
    }*/


}