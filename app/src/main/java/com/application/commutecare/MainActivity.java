package com.application.commutecare;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript for web page functionality
        webSettings.setDomStorageEnabled(true); // Enable local storage for web page data

        // Enable pinch zoom gesture to zoom in and out of the web page
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);

        // Set up gesture navigation
        mWebView.setOnTouchListener(new View.OnTouchListener() {
            private float mDownX, mDownY;

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mDownX = event.getX();
                        mDownY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        float xDiff = Math.abs(event.getX() - mDownX);
                        float yDiff = Math.abs(event.getY() - mDownY);
                        if (xDiff > yDiff) {

                            if (event.getX() > mDownX) {
                                if (mWebView.canGoBack()) {
                                    mWebView.goBack();
                                }
                            }

                            else if (event.getX() < mDownX) {
                                if (mWebView.canGoForward()) {
                                    mWebView.goForward();
                                }
                            }
                        }
                        break;
                }
                return false;
            }
        });

        // Load the web page
        mWebView.loadUrl("https://commutecare.vercel.app");
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
