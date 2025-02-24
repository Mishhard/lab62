package com.example.myapplicationlab6

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplicationlab6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Объявляем поле binding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//Создаем объект класса ActivityMainBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
//Устанавливаем для отображения корневой элемент (root)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as
                NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        val sharedPrefs = getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("jwt_token", null)

        if (token == null) {
            // Если нет токена, отправляем на экран авторизации
            navController.setGraph(R.navigation.navigation_graph, Bundle().apply {
                putBoolean("isAuth", false)
            })
            binding.bottomNavigationView.visibility = View.GONE
        } else {
            // Если есть токен, загружаем обычную навигацию
            navController.setGraph(R.navigation.navigation_graph, Bundle().apply {
                putBoolean("isAuth", true)
            })
            binding.bottomNavigationView.visibility = View.VISIBLE
        }

        binding.bottomNavigationView.setupWithNavController(navController)
    }
}