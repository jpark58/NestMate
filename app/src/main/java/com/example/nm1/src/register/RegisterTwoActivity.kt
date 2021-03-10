package com.example.nm1.src.register

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nm1.R
import com.example.nm1.config.BaseActivity
import com.example.nm1.config.BaseResponse
import com.example.nm1.databinding.ActivityRegisterTwoBinding
import com.example.nm1.src.main.home.MainActivity
import com.example.nm1.util.onMyTextChanged
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class RegisterTwoActivity : BaseActivity<ActivityRegisterTwoBinding>(ActivityRegisterTwoBinding::inflate), RegisterTwoView {
    private lateinit var uriPhoto: Uri
    private var map = HashMap<String, RequestBody>()
    private lateinit var uploadFile: MultipartBody.Part
    private var nicknameet = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        setSupportActionBar(binding.registerTwoToolbar.toolbarBack)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        binding.registerTwoToolbar.toolbarTitle.text = getString(R.string.register_toolbar_title)

        binding.registerTwoBtn.setOnClickListener {
            Log.d("로그", "${map["nickname"].toString()}")
            RegisterTwoService(this).tryPostUserSignUp(map = map, img = uploadFile)

        }

        binding.registerNickname.onMyTextChanged {
            if(binding.registerNickname.text.isNotEmpty()){
                binding.registerTwoBtn.setBackgroundResource(R.drawable.roundrec_design_active_bg)
                nicknameet = binding.registerNickname.text.toString()
                var nickname = RequestBody.create("text/plain".toMediaTypeOrNull(), this.nicknameet)
                map["nickname"] = nickname
            }else{
                binding.registerTwoBtn.setBackgroundResource(R.drawable.roundrec_design_inactive_bg)
            }
        }

        binding.registerImgDefault.setOnClickListener {
            var photoIntent = Intent(Intent.ACTION_PICK)
            photoIntent.type = "image/*"
            startActivityForResult(photoIntent, 0)
        }

        binding.registerImgSelected.setOnClickListener {
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

                if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED){
                    var email = RequestBody.create("text/plain".toMediaTypeOrNull(), intent.getStringExtra("email")!!)
                    var password = RequestBody.create("text/plain".toMediaTypeOrNull(), intent.getStringExtra("password")!!)
                    var phoneNumber = RequestBody.create("text/plain".toMediaTypeOrNull(), intent.getStringExtra("phone")!!)
                    var name = RequestBody.create("text/plain".toMediaTypeOrNull(), intent.getStringExtra("name")!!)


                    var file = File(getRealPathFromURI(uriPhoto))
                    var requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
                    uploadFile = MultipartBody.Part.createFormData("img", file.name, requestFile)

                    map["email"] = email
                    map["password"] = password
                    map["phoneNumber"] = phoneNumber
                    map["name"] = name


                    binding.registerImgDefault.visibility = View.GONE
                    binding.registerImgSelected.visibility = View.VISIBLE
                    binding.registerImgSelected.setImageURI(uriPhoto)
                }
            }
        }
    }



    override fun postUserSignUpSuccess(response: BaseResponse) {
        when(response.code){
            200 -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                finish()
            }
            else -> {
                val dialog = RegisterAuthDialog(response.message.toString(), false)
                dialog.show(supportFragmentManager, dialog.tag)
            }
        }
    }

    override fun postUserSignUpFailure(message: String) {
        showCustomToast(message)
    }

    fun getRealPathFromURI(uri: Uri): String{
        var path = ""
        var cursor = this.contentResolver.query(uri, null, null, null, null)
        if(cursor == null){
            path = uri.path!!
        }else{
            cursor.moveToFirst()
            var idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            path = cursor.getString(idx)
            cursor.close()
        }
        return path
    }
}