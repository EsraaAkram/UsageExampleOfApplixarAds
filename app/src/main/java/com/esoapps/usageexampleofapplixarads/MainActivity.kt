package com.esoapps.usageexampleofapplixarads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.activity.OnBackPressedCallback
import com.esoapps.applixir.SimplerApplixirAds
import com.esoapps.applixir.WebViewApplixirKotlin

class MainActivity : AppCompatActivity() {
    private var rewardBtn: Button? = null

    private var webviewContainerRv: RelativeLayout? = null
    private var webViewApplixirKotlin: WebViewApplixirKotlin? = null
    private val url = "https://js.appcdn.net/Android-test-1.html"// ADD YOURS
    private var simplerApplixirAds: SimplerApplixirAds? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //DEFINE UI ELEMENTS (PARENT LAYOUT AND THE BUTTON WILL TRIGGER ADS):
        rewardBtn=findViewById(R.id.reward)
        webviewContainerRv=findViewById(R.id.webviewContainerRv)

        //LOAD ADS:
        simplerApplixirAds = SimplerApplixirAds()

        webViewApplixirKotlin = simplerApplixirAds?.loadApplixirAd(
            webviewContainerRv, this, url
        )


        //SHOW ADS:
        rewardBtn!!.setOnClickListener {

            simplerApplixirAds?.showApplixirAd(
                webviewContainerRv,
                webViewApplixirKotlin
            )

        }


        //HANDLE ON BACK PRESS:
        //IN MY CASE I ONLY WILL FINISH THE ACTIVITY IF USER DOES NOT WATCH AD
        onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                    if (simplerApplixirAds?.closeAd(
                            webviewContainerRv,
                            webViewApplixirKotlin
                        ) == false
                    ) {
                        //DO WHATEVER YOU WANT TO DO ON BACK PRESS
                        finish()

                    }

                }
            })









    }
}