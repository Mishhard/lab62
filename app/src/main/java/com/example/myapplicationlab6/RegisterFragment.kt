package com.example.myapplicationlab6

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplicationlab6.databinding.FragmentRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val login = binding.etLogin.text.toString()
            val password = binding.etPassword.text.toString()
            val email = binding.etName.text.toString()
            performRegistration(login, password, email)
        }
    }

    private fun performRegistration(login: String, password: String, name: String) {
        val request = RegisterRequest(login, name, password)  // ✅ Используем правильную модель

        RetrofitClient.api.register(request).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful && response.body()?.token != null) {
                    Toast.makeText(requireContext(), "Регистрация успешна!", Toast.LENGTH_SHORT).show()

                    // Сохраняем токен (если нужно)
                    val sharedPreferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                    sharedPreferences.edit().putString("TOKEN", response.body()?.token).apply()

                    // После успешной регистрации входим
                    (parentFragment as? AuthFragment)?.onLoginSuccess()
                } else {
                    Toast.makeText(requireContext(), "Ошибка регистрации: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Ошибка сети: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
