package com.example.nm1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register_one.*
import kotlinx.android.synthetic.main.register_one.register_one_btn
import kotlinx.android.synthetic.main.register_two.*
import kotlinx.android.synthetic.main.toolbar_back.*

class RegisterTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_two)
        toolbar_title.text="회원가입"

        register_two_btn.setOnClickListener {
            val intent = Intent(this, OuterMain::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }
}