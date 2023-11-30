package com.applen.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.AppLen.myapplication.R
import com.applen.myapplication.fragment.ApplenFragment
import com.applen.myapplication.fragment.UsFragment
import com.applen.myapplication.fragment.WeanowFragment

private val weanowFragment= WeanowFragment()
private val applenFragment = ApplenFragment()
private val usFragment = UsFragment()

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }
}