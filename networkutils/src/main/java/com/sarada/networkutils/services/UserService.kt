package com.sarada.networkutils.services

import com.sarada.networkutils.data.UserData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface UserService {
    @GET("android_assignment.json")
    fun getUserDetails(): Deferred<UserData>
}