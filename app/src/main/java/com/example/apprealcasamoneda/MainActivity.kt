package com.example.apprealcasamoneda

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController

import com.example.apprealcasamoneda.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    var language = "en"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()

        setContentView(binding.root)
        updateLanguage()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottonNavigationView = findViewById<BottomNavigationView>(R.id.BottomNavigationBar)
        setupWithNavController(bottonNavigationView, navController)

        binding.swichtEn.setOnClickListener{
            savelanguage("en")

        }

        binding.swichtEs.setOnClickListener{
            savelanguage("es")

        }
    }

    private fun savelanguage(language: String) {
        val sharedPreferences = getSharedPreferences("preference",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("language", language)
        editor.apply()

        updateLanguage()

        recreate()
    }

    private fun updateLanguage() {
        val sharedPreferences = getSharedPreferences("preference",Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("language", "en")
        //val configuration = resources.configuration
        val conf = Configuration(baseContext.resources.configuration)
        conf.setLocale(if(language == "en") Locale.ENGLISH else Locale("es"))
        //resources.updateConfiguration(configuration,resources.displayMetrics)
        baseContext.resources.updateConfiguration(conf, baseContext.resources.displayMetrics)

        if (language == "en") {
            binding.swichtEn.setTextColor(resources.getColor(R.color.icon_arrow_svg))
            binding.swichtEs.setTextColor(resources.getColor(R.color.icons_svg))
            binding.swichtEn.setBackgroundColor(resources.getColor(R.color.white))
            binding.swichtEs.setBackgroundColor(resources.getColor(R.color.white))
        } else {
            binding.swichtEn.setTextColor(resources.getColor(R.color.icons_svg))
            binding.swichtEs.setTextColor(resources.getColor(R.color.icon_arrow_svg))
            binding.swichtEn.setBackgroundColor(resources.getColor(R.color.white))
            binding.swichtEs.setBackgroundColor(resources.getColor(R.color.white))
        }
    }


}