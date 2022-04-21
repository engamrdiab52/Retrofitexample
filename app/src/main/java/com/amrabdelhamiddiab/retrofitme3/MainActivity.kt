package com.amrabdelhamiddiab.retrofitme3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.amrabdelhamiddiab.retrofitme3.data.api.ApiHelper
import com.amrabdelhamiddiab.retrofitme3.data.api.RetrofitBuilder
import com.amrabdelhamiddiab.retrofitme3.data.model.User
import com.amrabdelhamiddiab.retrofitme3.data.repository.MainRepository
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var paymentIntentClientSecret: String
    private lateinit var publishableKey: String
    private lateinit var paymentSheet: PaymentSheet

    private lateinit var loginButton: Button
    private lateinit var productsButton: Button
    private lateinit var textView: TextView
    private lateinit var repository: MainRepository
    private var token: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginButton = findViewById(R.id.login_btn)
        productsButton = findViewById(R.id.products_rtn)
        textView = findViewById(R.id.textView)

        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)

        repository = MainRepository(ApiHelper(RetrofitBuilder.apiService))
        fetchPublishableKey()
        loginButton.setOnClickListener {
            fetchPaymentIntent()
            //  loginUser()
        }
        productsButton.setOnClickListener {
            //  getProducts( "Bearer $token" )
            pay()
        }

    }


    private fun pay(){

        val configuration = PaymentSheet.Configuration("Pharmacy")
        //fetchPaymentIntent()
        // Present Payment Sheet
        paymentSheet.presentWithPaymentIntent(paymentIntentClientSecret, configuration)
    }

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }


    private fun displayAlert(title: String, message: String? = null) {
        runOnUiThread {
            val builder = AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
            builder.setPositiveButton("Ok", null)
            builder.create().show()
        }
    }
//----------------------------------------------


    //--------------------------------------------


    private fun onPaymentSheetResult(paymentResult: PaymentSheetResult) {
        when (paymentResult) {
            is PaymentSheetResult.Completed -> {
                showToast("Payment complete!")
            }
            is PaymentSheetResult.Canceled -> {
                Log.i(Companion.TAG, "Payment canceled!")
            }
            is PaymentSheetResult.Failed -> {
                displayAlert("Payment failed", paymentResult.error.localizedMessage)
            }
        }
    }

    private fun getData() {
        lifecycleScope.launch(Dispatchers.IO) {
            val data = repository.getUsers()
            withContext(Dispatchers.Main) {
                textView.text = data
            }
            //  Log.d(TAG, data.toString())

        }
    }

    private fun registerUser() {
        lifecycleScope.launch(Dispatchers.IO) {
            val user = User(name = "Amy Amr_8", email = "amyAmr_8@gmail.com", password = "123456")
            val token = repository.registerUser(user)
            withContext(Dispatchers.Main) {
                textView.text = token
                Log.d(TAG, token)
            }
        }
    }

    private fun loginUser() {
        lifecycleScope.launch(Dispatchers.IO) {
            val user = User(name = "Amy Amr_2", email = "amyAmr_3@gmail.com", password = "123456")
            token = repository.loginUser(user)
            withContext(Dispatchers.Main) {
                textView.text = token
                Log.d(TAG, token)
            }
        }
    }

    private fun getProducts(token: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val listOfMedicines = repository.getProducts(token)
            withContext(Dispatchers.Main) {
                Log.d(TAG, listOfMedicines.toString())

                textView.text = listOfMedicines.toString()
            }
        }

    }

    private fun fetchPublishableKey() {
        try {
            lifecycleScope.launch(Dispatchers.IO) {
                publishableKey = repository.fetchPublishableKey()
                PaymentConfiguration.init(applicationContext, publishableKey)
                Log.d(TAG, publishableKey)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, publishableKey.toString())

                    textView.text = publishableKey.toString()
                }
            }

        } catch (e: Error) {
            Log.d(TAG, e.toString())
        }

    }

    private fun fetchPaymentIntent() {
        try {

            lifecycleScope.launch(Dispatchers.IO) {
                paymentIntentClientSecret = repository.fetchPaymentIntent()
                withContext(Dispatchers.Main) {
                    Log.d(TAG, publishableKey.toString())

                    textView.text = publishableKey.toString()
                }
            }

        } catch (e: Error) {

        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}