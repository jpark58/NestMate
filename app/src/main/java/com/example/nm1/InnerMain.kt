package com.example.nm1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nm1.alarmFrag.AlarmFragment
import com.example.nm1.homeFrag.HomeFragment
import com.example.nm1.myFrag.MyFragment
import kotlinx.android.synthetic.main.outer_main.*
import kotlinx.android.synthetic.main.toolbar_back.*

class InnerMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inner_main)

        // ViewPagerFragment를 fragmentFrame에 띄우기
        supportFragmentManager.beginTransaction().add(R.id.innerFrame, ViewPagerFragment()).commit()
    }
}