package com.example.todolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.todolistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = this.findNavController(R.id.my_nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this,navController)




    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.my_nav_host_fragment)
        return navController.navigateUp()

    }


}