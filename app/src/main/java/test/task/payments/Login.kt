package test.task.payments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.task.payments.databinding.ActivityLoginBinding
import test.task.payments.model.LoginRequestData
import test.task.payments.model.Requests
import test.task.payments.model.IntermediateData.*
import test.task.payments.model.IntermediateData.Companion.token

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://easypay.world/api-test/")
            .build()

        binding.buttonLogin.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val api = retrofit.create(Requests::class.java)
                val loginRequest = LoginRequestData(
                    binding.editTextLogin.text.toString(),
                    binding.editTextPassword.text.toString()
                )
                val response = api.login(loginRequest)
                token = response.response.token?:"empty"
                if(token != "empty"){
                    runOnUiThread {
                        val intent = Intent(this@Login, MainActivity::class.java)
                        startActivity(intent)
                    }
                }



            }
        }



    }
}