package com.demo.baiduditu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.demo.baiduditu.SlideBottomPanel.SlideBottomPanel;

public class MainActivity extends AppCompatActivity{

    private String webUrl = "http://www.zhihu.com/question/29416073/answer/44340933";
    private WebView webView;
    private SlideBottomPanel sbv;

    private int height;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            height = getActivityInsideViewHeight(this);
            Log.v("focusHeight",String.valueOf(getActivityInsideViewHeight(this)));
            sbv.setHeight(height);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbv = (SlideBottomPanel) findViewById(R.id.sbv);
        webView = (WebView) findViewById(R.id.wv_main);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(webUrl);
        webView.setWebChromeClient(new WebChromeClient());
//        Log.v("setHeight", String.valueOf(height));

    }

    @Override
    public void onBackPressed() {
        if (sbv.isPanelShowing()) {
            sbv.hide();
            return;
        }
        super.onBackPressed();
    }

    private int getActivityInsideViewHeight(Activity activity){
        // 用户绘制区域
        Rect outRect = new Rect();
        activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);
        int mHeight = outRect.height();

        Log.v("getActivityHeight", String.valueOf(mHeight));
        // end
        return mHeight;
    }

}
