package info.androidhive.materialtabs.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import info.androidhive.materialtabs.R;

/**
 * Created by rahulranjansinha on 30-06-2017.
 */

public class NewsActivity extends AppCompatActivity {

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // This line right here is what you're missing.
            // Use the url provided in the method.  It will match the member URL!
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_data);

        WebView mywebview = (WebView) findViewById(R.id.webView1);

        //mywebview.loadUrl("http://www.javatpoint.com/");

  /* String data = "<html><body><h1>Hello, Javatpoint!</h1></body></html>";
        mywebview.loadData(data, "text/html", "UTF-8");*/

        WebSettings wbset=mywebview.getSettings();
        wbset.setJavaScriptEnabled(true);
        mywebview.setWebViewClient(new HelloWebViewClient());
        Bundle extras=getIntent().getExtras();
        String url=extras.getString("Value1");
        mywebview.loadUrl(url);
        // mywebview.loadUrl("file:///android_asset/myresource.html");
    }
}
