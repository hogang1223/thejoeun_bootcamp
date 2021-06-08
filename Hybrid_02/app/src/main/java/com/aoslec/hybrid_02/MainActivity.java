package com.aoslec.hybrid_02;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    WebView webView = null;
    Button btnPage1, btnPage2, btnPage3;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        btnPage1 = findViewById(R.id.btn_page1);
        btnPage2 = findViewById(R.id.btn_page2);
        btnPage3 = findViewById(R.id.btn_page3);

//        btnReload.setOnClickListener(onClickListener);
        btnPage1.setOnClickListener(onClickListener);
        btnPage2.setOnClickListener(onClickListener);
        btnPage3.setOnClickListener(onClickListener);


        // Web Setting
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // JavaScript 사용 가능
        webSettings.setBuiltInZoomControls(true); // 확대 축소 가능
        webSettings.setDisplayZoomControls(false); // 돋보기 없애기

        webView.setWebViewClient(webViewClient);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            finish();
        }
    }

    // button click
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_page1:
                    btnPage1Click();
                    break;

                case R.id.btn_page2:
                    btnPage2Click();
                    break;
                case R.id.btn_page3:
                    btnPage3Click();
                    break;
            }
        }
    };

    public void btnPage1Click(){
        String content ="<html><head>"+
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />"+
                        "<head><body>"+
                        "<H1>Hello world</H1>" +
                        "</body></html>";

        webView.loadData(content, "text/html", "UTF-8");
    }
    public void btnPage2Click(){
        String content ="<html><head>"+
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />"+
                        "<head><body>"+
                        "<img src=\"http://192.168.219.105:8080/test/dog.jpeg\">" +
                        "</body></html>";

        webView.loadData(content, "text/html", "UTF-8");
    }
    public void btnPage3Click(){
        String content ="<html><head>"+
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />"+
                        "<head><body>" +
                        "<img src=\"http://192.168.219.105:8080/test/dog.jpeg\" width=\"100%\">" +
                        "</body></html>";

        webView.loadData(content, "text/html", "UTF-8");
    }


    WebViewClient webViewClient = new WebViewClient(){

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    };

}