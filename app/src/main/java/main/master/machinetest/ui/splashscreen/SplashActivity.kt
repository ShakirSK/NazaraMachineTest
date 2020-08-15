package main.master.machinetest.ui.splashscreen

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.BounceInterpolator
import kotlinx.android.synthetic.main.activity_splash.*
import main.master.machinetest.R
import main.master.machinetest.ui.football.FootballActivity

class SplashActivity : AppCompatActivity() {
    //Delay it for 2second
    val SPLASH_TIME_OUT = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //TRANSLATION animation
        val ty1 = ObjectAnimator.ofFloat(image, View.TRANSLATION_Y, 0f, 200f)
        ty1.setDuration(2000)
        ty1.interpolator = BounceInterpolator()
        ty1.start()

        //Delay it for 2second
        Handler().postDelayed (object:Runnable{
            public override fun run() {
                    val intent = Intent(applicationContext, FootballActivity::class.java)
                    startActivity(intent)
                    finish()
            }
        },SPLASH_TIME_OUT.toLong())

    }
}
