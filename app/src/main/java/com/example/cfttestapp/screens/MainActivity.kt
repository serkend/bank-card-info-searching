package com.example.cfttestapp.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cfttestapp.Constants
import com.example.cfttestapp.databinding.ActivityMainBinding
import com.example.cfttestapp.db.MainApp
import com.example.cfttestapp.db.model.RequestString
import com.example.cfttestapp.viewmodels.RequestFactory
import com.example.cfttestapp.viewmodels.RequestViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var viewModel: RequestViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db = (application as MainApp).database
        viewModel = ViewModelProvider(this, RequestFactory(application, db)).get(
            RequestViewModel::class.java
        )

        binding.btnCheck.setOnClickListener {
            var cardNum: String = ""
            if (binding.spinner.visibility == View.VISIBLE && binding.spinner.selectedItem.toString()
                    .isNotEmpty()
            ) {
                cardNum = binding.spinner.selectedItem.toString()
            } else if (binding.etCardNum.visibility == View.VISIBLE && binding.etCardNum.toString()
                    .isNotEmpty()
            ) {
                cardNum = binding.etCardNum.text.toString()
            }

            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtra(Constants.ET_CARD_NUMBER_KEY, cardNum)

            viewModel?.insertRequest(RequestString(0, cardNum))
            startActivity(intent)
        }

        binding.chbSpinner.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked) {
                binding.etCardNum.visibility = View.INVISIBLE
                binding.spinner.visibility = View.VISIBLE
            } else {
                binding.etCardNum.visibility = View.VISIBLE
                binding.spinner.visibility = View.INVISIBLE
            }
        }


        viewModel?.allRequests?.observe(this) { it ->
            var requestList = it
            var strings = emptyList<String>()
            Log.d("MyLog: ", "Requests : $requestList")
            requestList.let { list ->
                strings = list.map { it.request }
            }
            // Log.d("MyLog: ", "Requests : $strings")
//            var strings1 = listOf<String>("123456", "3486547", "35680580")
            var arrayAdapter: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, strings)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = arrayAdapter
        }
    }

    private fun removeDataBase() {
        val databasesDir = File(this.applicationInfo.dataDir + "/databases")
        var file = File(databasesDir, "request_list.db")
        file.delete()
    }
}