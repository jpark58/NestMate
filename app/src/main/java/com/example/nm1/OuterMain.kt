package com.example.nm1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nm1.alarmFrag.AlarmFragment
import com.example.nm1.homeFrag.HomeFragment
import com.example.nm1.myFrag.MyFragment
import kotlinx.android.synthetic.main.outer_main.*

class OuterMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.outer_main)
        initNavigationBar()
    }

    private fun initNavigationBar() {
        tabBottom.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.home -> { changeFragment(HomeFragment()) }
                    R.id.alarm -> { changeFragment(AlarmFragment()) }
                    R.id.my -> { changeFragment(MyFragment()) }
                }
                true
            }
            selectedItemId = R.id.home
        }
    }
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager .beginTransaction().replace(R.id.outer_screen, fragment) .commit()
    }
}