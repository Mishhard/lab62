package com.example.myapplicationlab6

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, MainFragment())
            .commit()

    }
}