package com.example.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

/** Called when the Application is started i.e Launch Activity
 *
 * Activity for the Nav Host Fragment
 */
class SampleMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_main)
        Timber.e("In oncreate")
    }
}
