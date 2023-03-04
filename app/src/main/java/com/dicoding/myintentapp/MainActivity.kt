package com.dicoding.myintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if(result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null) {
            val selectedValue = result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
            tvResult.text = "Hasil : $selectedValue"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_moveActivity)
        btnMoveActivity.setOnClickListener{
            when(it?.id){
                R.id.btn_moveActivity -> {
                    val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                    startActivity(moveIntent)
                }
            }
        }

        val btnMoveWithDataActivity: Button = findViewById(R.id.btn_moveActivityWithData)
        btnMoveWithDataActivity.setOnClickListener{
            when (it.id){
                R.id.btn_moveActivityWithData -> {
                    val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                    moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Rafi")
                    moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 20)
                    startActivity(moveWithDataIntent)
                }
            }
        }

        val btnMoveWithObject:Button = findViewById(R.id.btn_moveActivityWithObject)
        btnMoveWithObject.setOnClickListener {
            when (it.id) {
                R.id.btn_moveActivityWithObject -> {
                    val person = Person (
                        "Rafi",
                        20,
                        "krafime@gmail.com",
                        "Karawang"
                            )

                    val moveWithObjectIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                    moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                    startActivity(moveWithObjectIntent)
                }
            }
        }

        val btnDialPhone:Button = findViewById(R.id.btn_dialNumber)
        btnDialPhone.setOnClickListener {
            when(it.id) {
                R.id.btn_dialNumber -> {
                    val phoneNumber = "08123456789"
                    val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                    startActivity(dialPhoneIntent)
                }
            }
        }

        val btnMoveForResult:Button = findViewById(R.id.btn_moveForResult)
        btnMoveForResult.setOnClickListener {
            when(it.id) {
                R.id.btn_moveForResult -> {
                    val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                    resultLauncher.launch(moveForResultIntent)
                }
            }
        }
        tvResult = findViewById(R.id.tv_result)
    }
}