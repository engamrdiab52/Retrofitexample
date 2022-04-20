package com.amrabdelhamiddiab.retrofitme3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.coroutineScope
import com.amrabdelhamiddiab.retrofitme3.data.api.ApiHelper
import com.amrabdelhamiddiab.retrofitme3.data.api.RetrofitBuilder
import com.amrabdelhamiddiab.retrofitme3.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var repository: MainRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        repository = MainRepository(ApiHelper(RetrofitBuilder.apiService))
        button.setOnClickListener {
            getData()
        }

    }

    private fun getData() {
        lifecycle.coroutineScope.launch(Dispatchers.IO) {
            val data = repository.getUsers()
            withContext(Dispatchers.Main) {
                textView.text = data.name
            }
          //  Log.d(TAG, data.toString())

        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}