package hk.hku.cs.drawertest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class OnlineApplication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_application);

        WebView myWebView = (WebView) findViewById(R.id.loadOnlineApplication);
        String appUrl = getIntent().getStringExtra("extraTag");
        //String appUrl = "https://aal.hku.hk/tpg/programme/master-science-computer-science";

        //String pdfFilename = "https://www4.hku.hk/pubunit/drcd/files/pgdr2017-18/genreg.pdf";
        //String appUrl = "http://docs.google.com/gview?embedded=true&url="+pdfFilename;
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(appUrl);
    }
}
