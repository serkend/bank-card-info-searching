package com.example.cfttestapp.screens

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.cfttestapp.Constants
import com.example.cfttestapp.databinding.ActivityResultBinding
import com.example.cfttestapp.model.CardInfo
import com.example.cfttestapp.viewmodels.MainFactory
import com.example.cfttestapp.viewmodels.MainViewModel
import retrofit2.Response
import java.util.*

class ResultActivity : AppCompatActivity() {
    private var cardNumber: String = ""
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(Constants.ET_CARD_NUMBER_KEY)) {
            intent.getStringExtra(Constants.ET_CARD_NUMBER_KEY)?.let {
                cardNumber = it
            }
            Log.d("MyLog: ", cardNumber.toString())
            var viewModel = ViewModelProvider(
                this,
                MainFactory(application, cardNumber)
            ).get(MainViewModel::class.java)
            viewModel.getCardInfo()
            Log.d("MyLog: ", viewModel.cardNumber)

            viewModel.cardInfo.observe(this) {
                setupUI(it)
            }
        }
    }

    private fun setupUI(response: Response<CardInfo>) {
        binding.tvCountry.text = response.body()?.country.toString()
        makeLatLongMap(
            response.body()?.country?.latitude.toString(),
            response.body()?.country?.longitude.toString()
        )
        binding.tvType.text = response.body()?.type.toString()
        binding.tvScheme.text = response.body()?.scheme.toString()
        binding.tvBrand.text = response.body()?.brand.toString()
        binding.tvBank.text = response.body()?.bank.toString()
        makeUrl(response.body()?.bank?.url.toString())
        makePhoneNumber(response.body()?.bank?.phone.toString())
    }

    private fun makeUrl(bankAdress: String) = with(binding) {
        if (bankAdress.isNotEmpty()) {
            tvUrl.text = bankAdress
//            tvUrl.setOnClickListener {
//                val intentUrl = Intent(Intent.ACTION_VIEW)
//                intentUrl.data = Uri.parse(bankAdress)
//                startActivity(intentUrl)
//            }
        }
    }

    private fun makePhoneNumber(phoneNumber: String) = with(binding) {
        tvPhone.text = phoneNumber
    }

    private fun makeLatLongMap(latitude: String, longitude: String) = with(binding) {
        val uri: String = String.format(Locale.ENGLISH, "geo:$latitude,$longitude")
        binding.tvLocation.text = "latitude = $latitude , longitude = $longitude"
        binding.tvLocation.setOnClickListener {
            val intentLocation = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intentLocation)
        }
    }
}