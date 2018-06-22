package android.sdei.com.basicapp

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.sdei.com.basicapp.databinding.ActivitySplashBinding
import android.sdei.com.basicapp.ui.login.LoginActivity
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric


class Splash : AppCompatActivity()
{
    var binding: ActivitySplashBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        Fabric.with(this, Crashlytics())

        Handler().postDelayed({
            val intent=Intent(this, LoginActivity::class.java);
            startActivity(intent);
            finish()
        }, 3000)
    }
}
