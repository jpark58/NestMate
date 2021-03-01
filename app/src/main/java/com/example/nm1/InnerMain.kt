package com.example.nm1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InnerMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inner_main)

        // ViewPagerFragment를 fragmentFrame에 띄우기
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.innerFrame, ViewPagerFragment()).commit()
    }
}