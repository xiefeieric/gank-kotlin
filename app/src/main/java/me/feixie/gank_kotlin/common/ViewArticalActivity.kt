package me.feixie.gank_kotlin.common

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import me.feixie.gank_kotlin.R

import kotlinx.android.synthetic.main.activity_view_artical.*
import kotlinx.android.synthetic.main.content_view_artical.*
import timber.log.Timber

class ViewArticalActivity : AppCompatActivity() {

    private var mUrl: String? = null

    companion object {

        val URL = "url"

        fun startActivity(context: Activity, url: String) {
            val intent = Intent(context, ViewArticalActivity::class.java)
            intent.putExtra(URL, url)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_artical)
//        setSupportActionBar(toolbar)
        initData()
        initViews()
    }

    private fun initData() {

        mvContent.settings.loadWithOverviewMode = true
        mvContent.settings.useWideViewPort = true
        mvContent.settings.javaScriptEnabled = true
        mvContent.settings.pluginState = WebSettings.PluginState.ON
        mvContent.settings.allowFileAccess = true
        mvContent.settings.setSupportZoom(true)
        mvContent.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        mvContent.settings.domStorageEnabled = true
        mvContent.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        mvContent.settings.javaScriptCanOpenWindowsAutomatically = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mvContent.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;
        }
        mvContent.webChromeClient = WebChromeClient()

        mvContent.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pbContent.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pbContent.visibility = View.GONE
            }
        }

    }

    private fun initViews() {
        mUrl = intent?.getStringExtra(URL)
        mUrl?.let {
            mvContent.loadUrl(it)
        }
    }

    override fun onResume() {
        super.onResume()
        mvContent.onResume()
    }

    override fun onPause() {
        super.onPause()
        mvContent.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        flContent.removeView(mvContent)
        mvContent.destroy()
    }

}
