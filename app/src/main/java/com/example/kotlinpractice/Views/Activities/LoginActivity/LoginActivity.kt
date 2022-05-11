package com.example.kotlinpractice.Views.Activities.LoginActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlinpractice.R
import com.example.kotlinpractice.Views.Activities.ProfileActivity.ProfileActivity
import com.example.kotlinpractice.Views.Fragments.LoginFragment.LoginFragment
import com.example.kotlinpractice.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var loginFragment: LoginFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginFragment = LoginFragment()
        replaceFragment(loginFragment)

    }

    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().currentUser != null ){
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else{
            super.onBackPressed()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.mainFrame,fragment).commit()
    }
}