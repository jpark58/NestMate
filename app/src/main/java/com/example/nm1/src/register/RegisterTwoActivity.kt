package com.example.nm1.src.register

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nm1.R
import com.example.nm1.config.BaseActivity
import com.example.nm1.databinding.ActivityRegisterTwoBinding
import com.example.nm1.src.main.home.MainActivity
import com.example.nm1.util.firebaseKey
import com.example.nm1.util.onMyTextChanged
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.text.SimpleDateFormat
import java.util.*

class RegisterTwoActivity : BaseActivity<ActivityRegisterTwoBinding>(ActivityRegisterTwoBinding::inflate) {
    private var storage = Firebase.storage(firebaseKey())
    private lateinit var uriPhoto: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)

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

        binding.registerImg.setOnClickListener {
            var photoIntent = Intent(Intent.ACTION_PICK)
            photoIntent.type = "image/*"
            startActivityForResult(photoIntent, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0){
            if(resultCode == Activity.RESULT_OK){
                uriPhoto = data?.data!!
                binding.registerImg.setImageURI(uriPhoto)

                if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED){
                    funImageUpload()
                }
            }
        }
    }

    private fun funImageUpload(){
        var timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var imgFileName = "IMAGE_" + timestamp + "_.jpg"
        var storageRef = storage?.reference.child("images").child(imgFileName)
        storageRef.putFile(uriPhoto).addOnSuccessListener {
            showCustomToast("Image upload successful")
        }
    }
}