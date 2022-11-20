package com.codingwithjadrey.loginapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.codingwithjadrey.loginapp.LoginApplication
import com.codingwithjadrey.loginapp.data.interf.AuthListener
import com.codingwithjadrey.loginapp.databinding.FragmentLoginBinding
import com.codingwithjadrey.loginapp.ui.MainActivity
import com.codingwithjadrey.loginapp.viewmodel.AuthViewModel

class LoginFragment : Fragment(), AuthListener {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by lazy {
        ViewModelProvider(this, LoginApplication.authViewModelFactory)[AuthViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = authViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            loginBtn.setOnClickListener { viewModel?.login() }
            noAccount.setOnClickListener { viewModel?.goToSignup(view) }
        }
        authViewModel.authListener = this
    }

    override fun onStarted() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        binding.progressBar.visibility = View.GONE
        startActivity(Intent(requireContext(), MainActivity::class.java))
        activity?.finish()
    }

    override fun onFailure(message: String) {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}