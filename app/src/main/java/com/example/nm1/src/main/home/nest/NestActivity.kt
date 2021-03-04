package com.example.nm1.src.main.home.nest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nm1.R

class NestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inner_main)

        // ViewPagerFragment를 fragmentFrame에 띄우기
        supportFragmentManager.beginTransaction().add(R.id.innerFrame, ViewPagerFragment()).commit()
    }
}