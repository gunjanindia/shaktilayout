package com.netmaptechnology.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.*
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

@RequiresApi(Build.VERSION_CODES.S)
class MainActivity : AppCompatActivity() {
    lateinit var helpButton: ImageButton
    /*private val vibratorManager: VibratorManager by lazy{
        val vibratorManager = this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        val vibrator = vibratorManager.getDefaultVibrator();
        getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
    }*/
    var handler: Handler? = Handler()
    var runnable = Runnable { // this will disable your button
        helpButton.setEnabled(true)
        helpButton.setBackgroundResource(R.drawable.ic_button)
    }
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        helpButton=findViewById<ImageButton>(R.id.helpButton)
        helpButton.setOnClickListener(){
            Toast.makeText(
                this@MainActivity,
                "Help Button Clicked",
                Toast.LENGTH_SHORT
            ).show()
           vibratePhone()
            helpButton.setBackgroundResource(R.drawable.ic_button_disabled)
            helpButton.setEnabled(false)
            handler?.postDelayed(runnable, 3000);
        }


    }
    fun vibratePhone() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) { // Vibrator availability checking
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)) // New vibrate method for API Level 26 or higher
            } else {
                vibrator.vibrate(500) // Vibrate method for below API Level 26
            }
        }
    }


}