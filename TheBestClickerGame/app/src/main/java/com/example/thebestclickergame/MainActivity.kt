package com.example.thebestclickergame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thebestclickergame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Click Functionality
        val sharedPreferences = getSharedPreferences("SP_INFO", MODE_PRIVATE)
        var numClicks = sharedPreferences.getString("NUMCLICKS", "0") //set clicker number when app starts
        binding.clickCount.text = numClicks
        var level = sharedPreferences.getString("LEVEL", "1") //set level on Create
        binding.levelNum.text = "Level: " + level.toString()

        binding.clickHere.setOnClickListener {
            binding.clickCount.text = (binding.clickCount.text.toString().toInt() + 1).toString()
            val editor = sharedPreferences.edit()
            editor.putString("NUMCLICKS", binding.clickCount.text.toString())

            level = selectLevel()
            binding.levelNum.text = "Level: " + level
            editor.putString("LEVEL", level)

            editor.apply()
        }
        //End Base Click Functionality



        // Testing tools
        binding.clearBtn.setOnClickListener {
            binding.clickCount.text = "0"
            val editor = sharedPreferences.edit()
            editor.putString("NUMCLICKS", "0")
            editor.apply()
        }
    }

    fun selectLevel() : String{
        when(binding.clickCount.text.toString().toInt()){
            in 1..15 -> return "1"
        }
        return "2"
    }
}