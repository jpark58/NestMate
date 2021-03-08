package com.example.nm1.src.register

import com.example.nm1.config.BaseResponse

interface RegisterView {
    fun onPostUserSuccess(reponse: BaseResponse)
    fun onPostUserFailure(message: String)
}