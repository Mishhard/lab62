package com.example.myapplicationlab6

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplicationlab6.databinding.FragmentAuthBinding
import com.google.android.material.tabs.TabLayoutMediator

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPrefs = requireContext().getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("jwt_token", null)

        if (token != null) {
            findNavController().navigate(R.id.action_authFragment_to_chatsFragment)
            requireActivity().findViewById<View>(R.id.bottomNavigationView)?.visibility = View.VISIBLE
            return
        }

        binding.viewPager.adapter = AuthPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Вход"
                1 -> "Регистрация"
                else -> null
            }
        }.attach()
    }

    fun onLoginSuccess() {
        requireActivity().findViewById<View>(R.id.bottomNavigationView)?.visibility = View.VISIBLE
        findNavController().navigate(R.id.action_authFragment_to_chatsFragment)
    }
}
