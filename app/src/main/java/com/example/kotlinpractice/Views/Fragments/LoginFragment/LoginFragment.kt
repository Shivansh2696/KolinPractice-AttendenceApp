package com.example.kotlinpractice.Views.Fragments.LoginFragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinpractice.Model.AuthenticationModels.LoginUser
import com.example.kotlinpractice.R
import com.example.kotlinpractice.Views.Activities.ProfileActivity.ProfileActivity
import com.example.kotlinpractice.Views.Fragments.RegisterFragment.RegisterFragment
import com.example.kotlinpractice.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    private lateinit var registerFragment : RegisterFragment
    private lateinit var loginUser : LoginUser
    private lateinit var mGoogleSignInClient : GoogleSignInClient
    private lateinit var viewModel : LoginFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.your_web_client_id))
            .requestEmail()
            .build()

         mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        registerFragment = RegisterFragment()
        viewModel = ViewModelProvider(this)[LoginFragmentViewModel :: class.java]

        viewModel.getCompleteLiveData().observe(viewLifecycleOwner, Observer {
                if(it == true){
                    binding.Progressbar.visibility = View.INVISIBLE
                    Toast.makeText(context,"Login Successful",Toast.LENGTH_SHORT).show()
                    startActivity( Intent(activity, ProfileActivity::class.java))
                    activity?.finish()
                }else{
                    binding.Progressbar.visibility = View.INVISIBLE
                    Toast.makeText(context,"Please Enter Correct UserName and Password",Toast.LENGTH_SHORT).show()
                }
        })

        binding.tvRegister.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFrame,registerFragment)
                ?.addToBackStack(registerFragment.javaClass.name)?.commit()
        }

        binding.btLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            if(TextUtils.isEmpty(email)){
                binding.etEmail.error = "Email is Required"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }
            if(password.length < 6){
                binding.etPassword.error = "Password Length Minimum 6 digits"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }
            loginUser = LoginUser(email,password)
            binding.Progressbar.visibility = View.VISIBLE
            viewModel.setLoginUser(loginUser)

        }

        binding.googleButton.setOnClickListener{
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, 100)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            try {
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken)
            }catch (e:ApiException){
                println(e.localizedMessage)
            }

        }
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        val mAuth = FirebaseAuth.getInstance()
        val credential = GoogleAuthProvider.getCredential(idToken,null)
        mAuth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                startActivity(Intent(requireContext(), ProfileActivity::class.java))
                activity?.finish()
            }
        }
    }
}

