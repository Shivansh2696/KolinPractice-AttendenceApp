package com.example.kotlinpractice.Views.Activities.ProfileActivity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.kotlinpractice.Model.AuthenticationModels.EmailDetails
import com.example.kotlinpractice.R
import com.example.kotlinpractice.databinding.ActivityProfileBinding
import com.google.android.material.navigation.NavigationView


class ProfileActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileActivityViewModel
    private lateinit var emailDetail: EmailDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ProfileActivityViewModel::class.java]
        emailDetail = viewModel.getEmailDetails()

        setSupportActionBar(binding.appBarProfile.toolbar)

        //Getting IDs From Navigation Header
        val view: NavigationView = findViewById(R.id.nav_view)
        val headerView: View = view.getHeaderView(0)
        val navUsername = headerView.findViewById<View>(R.id.UserName) as TextView
        val navEmail = headerView.findViewById<View>(R.id.Email) as TextView
        val navPhoto = headerView.findViewById<View>(R.id.ProfilePhoto) as ImageView

        // Setting Text To Navigation Header
        navUsername.text = emailDetail.getUsername()
        navEmail.text = emailDetail.getEmail()
        Glide.with(this).load(emailDetail.getImage()).into(navPhoto)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_profile)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_dashboard, R.id.nav_settings), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_profile)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
