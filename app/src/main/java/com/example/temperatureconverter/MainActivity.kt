package com.example.temperatureconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import kotlin.math.max
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var seekBarCelsius: SeekBar
    private lateinit var seekBarFahrenheit: SeekBar

    private lateinit var celsiusText: TextView
    private lateinit var fahrenheitText: TextView
    private lateinit var interestingText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarCelsius = findViewById<SeekBar>(R.id.celsiusSlider)
        seekBarFahrenheit = findViewById<SeekBar>(R.id.fahrenheitSlider)

        celsiusText = findViewById<TextView>(R.id.celsius)
        fahrenheitText = findViewById<TextView>(R.id.fahrenheit)
        interestingText = findViewById<TextView>(R.id.interesting)


        seekBarCelsius.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val fahrenheit = celsiusToFahrenheit(progress)
                    seekBarFahrenheit.progress = fahrenheit.roundToInt()

                    celsiusText.text = "Celsius: $progress"
                    fahrenheitText.text = "Fahrenheit: $fahrenheit"

                    interestingText.text = if (progress <= 20) {
                        "I wish it were warmer."
                    } else {
                        "I wish it were colder."
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        seekBarFahrenheit.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val celsius = fahrenheitToCelsius(progress)
                    seekBarCelsius.progress = celsius.roundToInt()

                    fahrenheitText.text = "Fahrenheit: $progress"
                    celsiusText.text = "Celsius: $celsius"

                    interestingText.text = if (celsius <= 20) {
                        "I wish it were warmer."
                    } else {
                        "I wish it were colder."
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                if (seekBar.progress < 32) {
                    seekBar.progress = 32

                    fahrenheitText.text = "Fahrenheit: 32"
                    celsiusText.text = "Celsius: 0"
                }


            }
        })
    }

    private fun celsiusToFahrenheit(celsius: Int): Double {
        return celsius * 9.0 / 5 + 32
    }

    private fun fahrenheitToCelsius(fahrenheit: Int): Double {
        return (fahrenheit - 32) * 5.0 / 9
    }
}