package hk.hku.cs.drawertest2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class FragmentContact extends Fragment {

    private TextView web1, web2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact,container,false);

        String stringUrl = "https://www.msc-cs.hku.hk/";
//        tp1 = (TextView) view.findViewById(R.id.contact_p1);

        //new DownloadWebpageTask().execute(stringUrl);

        web1 = view.findViewById(R.id.contact_p5);
        web2 = view.findViewById(R.id.contact_p7);
        web1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // find current layout
                LinearLayout linearLayout = getView().findViewById(R.id.contactLayout);
                // add webview
                WebView webView = new WebView(linearLayout.getContext());
                String appUrl = web1.getText().toString();
                webView.loadUrl(appUrl);
            }
        });
        web2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // find current layout
                LinearLayout linearLayout = getView().findViewById(R.id.contactLayout);
                // add webview
                WebView webView = new WebView(linearLayout.getContext());
                String appUrl = web2.getText().toString();
                webView.loadUrl(appUrl);
            }
        });
        return view;
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, Document> {
        @Override
        protected Document doInBackground(String... urls) {
            String url = urls[0];
            Document doc = null;
            try{
                doc = Jsoup.connect(url).get();
            } catch (IOException e){
                e.printStackTrace();
            }
            return doc;
        }
        @Override
        protected void onPostExecute(Document doc){
            // do something
        }

    }

}
