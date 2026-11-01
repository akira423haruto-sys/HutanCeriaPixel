package id.hutanceria.pixel

import android.app.Activity
import android.os.Bundle
import android.os.Build
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.view.View
import android.view.WindowManager

class MainActivity : Activity() {
    private lateinit var gameView: WebView

    @Suppress("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        gameView = WebView(this).apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                allowFileAccess = true // Required to load the bundled offline game.
                allowContentAccess = false
                javaScriptCanOpenWindowsAutomatically = false
                setSupportMultipleWindows(false)
                mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW
                cacheMode = WebSettings.LOAD_DEFAULT
                textZoom = 100
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    safeBrowsingEnabled = true
                }
                setGeolocationEnabled(false)
            }
            webChromeClient = WebChromeClient()
            webViewClient = WebViewClient()
            setBackgroundColor(android.graphics.Color.rgb(177, 231, 255))
        }
        setContentView(gameView)
        gameView.loadUrl("file:///android_asset/index.html")
    }

    @Deprecated("Deprecated by Android, retained for compatibility with the project's minimum SDK.")
    override fun onBackPressed() {
        // The game is a single local page; avoid navigating away from it accidentally.
        if (::gameView.isInitialized && gameView.url != "file:///android_asset/index.html") {
            gameView.loadUrl("file:///android_asset/index.html")
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        if (::gameView.isInitialized) {
            gameView.stopLoading()
            gameView.loadUrl("about:blank")
            gameView.removeAllViews()
            gameView.destroy()
        }
        super.onDestroy()
    }
}
