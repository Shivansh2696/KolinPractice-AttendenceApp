package com.example.kotlinpractice.Views.Fragments.RegisterFragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinpractice.Model.AuthenticationModels.RegisterUser
import com.example.kotlinpractice.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding : FragmentRegisterBinding
    private lateinit var viewModel : RegisterFragmentViewModel
    private lateinit var user : RegisterUser
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding =  FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[RegisterFragmentViewModel::class.java]
        viewModel.getCompleteLiveData().observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.Progressbar.visibility = View.INVISIBLE
                Toast.makeText(context,"Registration Successfully Done",Toast.LENGTH_SHORT).show()
                activity?.supportFragmentManager?.popBackStack()
            }
        })
        binding.btRegister.setOnClickListener(this::Onclick)
    }

    private fun Onclick(view: View)  {
        val id = view.id
        if (id == binding.btRegister.id) {
            registerUser()
        }
    }
    private fun registerUser(){
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()

        if (TextUtils.isEmpty(username)) {
            binding.etUsername.error = "Username is Required"
            binding.etUsername.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.error = "Email is Required"
            binding.etEmail.requestFocus()
            return
        }
        if (password.length < 6) {
            binding.etPassword.error = "Password is Required"
            binding.etPassword.requestFocus()
            return
        }
        user = RegisterUser(username,email,password)
        binding.Progressbar.visibility = View.VISIBLE
        viewModel.setUser(user)
    }
}
