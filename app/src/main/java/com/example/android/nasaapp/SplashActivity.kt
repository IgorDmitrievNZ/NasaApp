package com.example.android.nasaapp

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.nasaapp.ui.MainActivity

@SuppressLint("CustomSplashScreen") //FIXME что ты такое
class SplashActivity : AppCompatActivity() {

    /*private val handler: Handler by lazy {   // another way to get handler
        Handler(mainLooper)
    }*/

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler = Handler(mainLooper)

        /*findViewById<ImageView>(R.id.imageView33).animate().rotationBy(720f).setInterpolator(
            LinearInterpolator()
        ).duration = 3000*/

        val animator =
            ObjectAnimator.ofFloat(findViewById<ImageView>(R.id.imageView33), View.SCALE_Y, -1f);
        animator.duration = 500
        animator.repeatCount = 5
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()

        handler.postDelayed(Runnable {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 2000)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        handler.removeCallbacksAndMessages(null)
        onDestroy()
    }
}