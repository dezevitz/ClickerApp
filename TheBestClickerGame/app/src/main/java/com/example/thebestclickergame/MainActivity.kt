package com.example.thebestclickergame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.thebestclickergame.databinding.ActivityMainBinding

//TODO changes that only need to happen once (ex level 2) can occur only once somewhere else

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
            binding.root.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.white))
            binding.clickCount.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.black))
        }
    }

    fun selectLevel() : String{
        when(binding.clickCount.text.toString().toInt()){
            in 1..10 -> return "1"
            in 11..20 -> {
                binding.root.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.black))
                binding.clickCount.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.white))
                return "2"
            }
            in 21..60 -> {
                binding.clickCount.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.black))
                when(binding.clickCount.text.toString().toInt()%10){
                    1 -> binding.root.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.pink))
                    2 -> binding.root.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.red))
                    3 -> binding.root.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.orange))
                    4 -> binding.root.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.yellow))
                    5 -> binding.root.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.lime))
                    6 -> binding.root.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.green))
                    7 -> binding.root.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.bluegreen))
                    8 -> binding.root.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.blue))
                    9 -> binding.root.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.indigo))
                    0 -> binding.root.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.violet))
                }
                return "3"
            }
        }
        return "9999999"
    }
}