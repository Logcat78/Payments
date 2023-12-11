package test.task.payments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import test.task.payments.databinding.ActivityLoginBinding
import test.task.payments.databinding.ActivityMainBinding
import test.task.payments.model.IntermediateData
import test.task.payments.model.Requests

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.logout.setOnClickListener {
            val intentLogout = Intent(this,Login::class.java)
            startActivity(intentLogout)
            finish()
        }
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://easypay.world/api-test/")
            .build()


        CoroutineScope(Dispatchers.IO).launch {
                val api = retrofit.create(Requests::class.java)
                    val response = api.payments(IntermediateData.token)
                    runOnUiThread{
                        binding.rv.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            setHasFixedSize(true)
                            adapter = CustomAdapter(response.response)
                            val decorator = Decorator(20)
                            addItemDecoration(decorator)
                        }
                    }
                }
            }
    }
