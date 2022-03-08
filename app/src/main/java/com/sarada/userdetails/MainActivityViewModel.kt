package com.sarada.userdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sarada.networkutils.UserApi
import com.sarada.networkutils.data.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    private val _user = MutableLiveData<UserData>()
    val user : LiveData<UserData>
        get() = _user

    init {
        getUserData()
        _user.value = UserData("","", listOf())
    }

    private fun getUserData() {
        val viewModelJob = Job()
        val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

        coroutineScope.launch {
            val getUserDetailsDeferred = UserApi.retrofitService.getUserDetails()
            try{
                _user.value = getUserDetailsDeferred.await()
            }catch(t: Throwable){
                Log.d("Error fetching data", t.message!!)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}