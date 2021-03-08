package com.example.nm1.src.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.example.nm1.R
import com.example.nm1.config.BaseActivity
import com.example.nm1.databinding.ActivityRegisterTwoBinding
import com.example.nm1.src.main.home.MainActivity
import com.example.nm1.util.onMyTextChanged

class RegisterTwoActivity : BaseActivity<ActivityRegisterTwoBinding>(ActivityRegisterTwoBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.registerTwoToolbar.toolbarBack)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        binding.registerTwoToolbar.toolbarTitle.text = getString(R.string.register_toolbar_title)

        binding.registerTwoBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        binding.registerNickname.onMyTextChanged {
            if(binding.registerNickname.text.isNotEmpty()){
                binding.registerTwoBtn.setBackgroundResource(R.drawable.roundrec_design_active_bg)
            }else{
                binding.registerTwoBtn.setBackgroundResource(R.drawable.roundrec_design_inactive_bg)
            }
        }
    }
}