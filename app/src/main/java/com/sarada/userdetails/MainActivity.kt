package com.sarada.userdetails

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.opengl.Visibility
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sarada.networkutils.UserApi
import com.sarada.networkutils.data.UserData
import com.sarada.userdetails.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setHomeScreen()
    }

    private fun setHomeScreen() {
        if(isNetworkConnected()){
            viewModel.user.observe(this, Observer { newUser ->
                if(newUser.uiData.isNotEmpty()){
                    binding.apply {
                        letsGoButton.visibility = VISIBLE
                        infoTextView.text = resources.getString(R.string.info_text)
                    }
                    binding.letsGoButton.setOnClickListener {
                        val intent = Intent(this, UserDetailsFormActivity::class.java)
                        intent.putExtra("user", newUser)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        this.startActivity(intent)
                    }
                }
                else{
                    binding.apply {
                        letsGoButton.visibility = GONE
                        infoTextView.text = resources.getString(R.string.no_ui_elements)
                    }
                }
            })
        }else{
            binding.apply {
                infoTextView.text = resources.getString(R.string.no_internet)
                letsGoButton.visibility = GONE
            }
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}