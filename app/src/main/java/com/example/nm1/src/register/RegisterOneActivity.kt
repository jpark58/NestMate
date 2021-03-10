package com.example.nm1.src.register

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.nm1.R
import com.example.nm1.config.BaseActivity
import com.example.nm1.databinding.ActivityRegisterOneBinding
import com.example.nm1.util.onMyTextChanged
import kotlin.math.roundToInt

class RegisterOneActivity : BaseActivity<ActivityRegisterOneBinding>(ActivityRegisterOneBinding::inflate) {
    private var isOK = false
    private var isResend = false
    private var checkList = booleanArrayOf(false,false,false,false,false,false)
    val timer: CountDownTimer = object : CountDownTimer(300000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            //update the UI with the new count
            var count = millisUntilFinished / 1000

            if(count - ((count/60)*60) >= 10){
                binding.registerPhoneAuthTimer.text = (count/60).toString() + ":" + (count - ((count/60)*60))
            }else{
                binding.registerPhoneAuthTimer.text = (count/60).toString() + ":0" + (count - ((count/60)*60))
            }
        }

        override fun onFinish() {
            binding.registerPhoneAuthTimer.text = "00:00"
            binding.registerPhoneAuthBtn.text = "재전송"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.registerOneToolbar.toolbarBack)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        binding.registerOneToolbar.toolbarTitle.text = getString(R.string.register_toolbar_title)

        binding.registerOneBtn.setOnClickListener {
            if(isOK){
                val intent = Intent(this, RegisterTwoActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

                intent.putExtra("name", (binding.registerNameEt.text).toString())
                intent.putExtra("email",(binding.registerEmailEt.text).toString())
                intent.putExtra("phone", (binding.registerPhoneEt.text).toString())
                intent.putExtra("password", (binding.registerPwEt.text).toString())

                startActivity(intent)
                finish()
            }
        }

        binding.registerPhoneAuthBtn.setOnClickListener {
            binding.registerPhoneAuthLayout.visibility = View.VISIBLE
            timer.start()
        }

        binding.registerPhoneAuthMsgBtn.setOnClickListener {
            // RETRFOIT!!!!
        }

        binding.registerEmailBtn.setOnClickListener {
            // RETROFIT!!!
            val dialog = RegisterAuthDialog("email")
            dialog.show(supportFragmentManager, dialog.tag)
        }

        binding.registerNameEt.onMyTextChanged {
            checkList[0] = (binding.registerNameEt.text).isNotEmpty()
        }

        binding.registerPhoneEt.onMyTextChanged {
            checkList[1] = (binding.registerPhoneEt.text).isNotEmpty()
        }

        binding.registerPhoneAuthMsgEt.onMyTextChanged {
            checkList[2] = (binding.registerPhoneEt.text).isNotEmpty()
        }

        binding.registerEmailEt.onMyTextChanged {
            checkList[3] = (binding.registerEmailEt.text).isNotEmpty()
        }

        binding.registerPwEt.onMyTextChanged {
            checkList[4] = (binding.registerPwEt.text).isNotEmpty()
        }

        binding.registerPwConfirmEt.onMyTextChanged {
            checkList[5] = (binding.registerPwConfirmEt.text).isNotEmpty()

            var flag = true
            for(temp in checkList){
                if(!temp){
                    flag = false
                }
            }

            if(flag){
                binding.registerOneBtn.setBackgroundResource(R.drawable.roundrec_design_active_bg)
                isOK = true
            }else{
                binding.registerOneBtn.setBackgroundResource(R.drawable.roundrec_design_inactive_bg)
            }
        }
    }
}