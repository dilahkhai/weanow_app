package com.applen.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.AppLen.myapplication.R
import com.AppLen.myapplication.databinding.ActivityProfileBinding
import com.applen.myapplication.fragment.ApplenFragment
import com.applen.myapplication.fragment.UsFragment
import com.applen.myapplication.fragment.WeanowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class ProfileActivity : AppCompatActivity() {
    private var _binding: ActivityProfileBinding? = null
    private val binding get() = _binding as ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fab: View = binding.fabProfileGroup
        fab.setOnClickListener { view ->
            val intent = Intent(this@ProfileActivity, MainActivity::class.java)
            startActivity(intent)
        }

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