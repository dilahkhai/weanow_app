package com.applen.myapplication.ui

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.AppLen.myapplication.R
import com.AppLen.myapplication.databinding.ActivityProfileBinding
import com.applen.myapplication.fragment.ApplenFragment
import com.applen.myapplication.fragment.UsFragment
import com.applen.myapplication.fragment.WeanowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton




class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profileGroup  = R.id.fab_profile_group

        val weanowFragment= WeanowFragment()
        val applenFragment = ApplenFragment()
        val usFragment = UsFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        replaceFragment(weanowFragment)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_info_weanow -> replaceFragment(weanowFragment)
                R.id.ic_info_applen -> replaceFragment(applenFragment)
                R.id.ic_info_us -> replaceFragment(usFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if (fragment != null){
             val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, fragment)
            transaction.commit()
        }
    }
}