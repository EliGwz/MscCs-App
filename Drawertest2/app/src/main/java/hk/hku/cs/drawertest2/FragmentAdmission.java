package hk.hku.cs.drawertest2;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
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
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FragmentAdmission extends Fragment {

    private TextView header, title1, title2, title3, tp11, tp21, tp31, tp12, tp32, link1, link2, link3, link4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admission,container,false);

        String stringUrl = "https://www.msc-cs.hku.hk/Admission/Requirements";
        header = (TextView) view.findViewById(R.id.admissionText);
        title1 = (TextView) view.findViewById(R.id.eligibility);
        title2 = (TextView) view.findViewById(R.id.regulations);
        title3 = (TextView) view.findViewById(R.id.eng_req);

        tp11 = (TextView) view.findViewById(R.id.eligibilit_p11);
        tp21 = (TextView) view.findViewById(R.id.regulations_p21);
        tp31 = (TextView) view.findViewById(R.id.eng_req_p31);

        tp12 = (TextView) view.findViewById(R.id.eligibilit_p12);
        tp32 = (TextView) view.findViewById(R.id.eng_req_p32);

        link1 = (TextView) view.findViewById(R.id.regulations_link1);
        link2 = (TextView) view.findViewById(R.id.regulations_link2);
        link3 = (TextView) view.findViewById(R.id.regulations_link3);
        link4 = (TextView) view.findViewById(R.id.regulations_link4);

        new DownloadWebpageTask().execute(stringUrl);

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
        protected void onPostExecute(Document doc) {
            String title = doc.title();
            header.setText(title);

            /************************** Eligibility *************************/
            Element titleElement1 = doc.getElementById("Eligibility");
            String titleString1 = titleElement1.select("strong").text();
            title1.setText(titleString1);
            String tpString11 = titleElement1.select("p").get(1).text();
            tp11.setText(tpString11);
            // items = 4
            String tpString12 = "";
            for (int i = 2; i<=5; i++){
                tpString12 = tpString12 + titleElement1.select("p").get(i).text() + "\n";
            }
            tp12.setText(tpString12);

            /************************** regulations *************************/
            Element titleElement2 = doc.getElementById("regulations");
            String titleString2 = titleElement2.select("strong").text();
            title2.setText(titleString2);
            // items = 4
            link1.setText(titleElement2.select("a").get(0).text());
            link2.setText(titleElement2.select("a").get(1).text());
            link3.setText(titleElement2.select("a").get(2).text());
            link4.setText(titleElement2.select("a").get(3).text());
            link1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // find current layout
                    LinearLayout linearLayout = getView().findViewById(R.id.admissionLayout);
                    // add webview
                    WebView webView = new WebView(linearLayout.getContext());
                    String linkUrl = "http://docs.google.com/gview?embedded=true&url=" + link1.getText().toString();
                    webView.loadUrl(linkUrl);
                    link1.setTextColor(getResources().getColor(R.color.link_clicked));
                }
            });
            link2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // find current layout
                    LinearLayout linearLayout = getView().findViewById(R.id.admissionLayout);
                    // add webview
                    WebView webView = new WebView(linearLayout.getContext());
                    String linkUrl = "http://docs.google.com/gview?embedded=true&url=" + link2.getText().toString();
                    webView.loadUrl(linkUrl);
                    link2.setTextColor(getResources().getColor(R.color.link_clicked));
                }
            });
            link3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // find current layout
                    LinearLayout linearLayout = getView().findViewById(R.id.admissionLayout);
                    // add webview
                    WebView webView = new WebView(linearLayout.getContext());
                    String linkUrl = "http://docs.google.com/gview?embedded=true&url=" + link3.getText().toString();
                    webView.loadUrl(linkUrl);
                    link3.setTextColor(getResources().getColor(R.color.link_clicked));
                }
            });
            link4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // find current layout
                    LinearLayout linearLayout = getView().findViewById(R.id.admissionLayout);
                    // add webview
                    WebView webView = new WebView(linearLayout.getContext());
                    String linkUrl = "http://docs.google.com/gview?embedded=true&url=" + link4.getText().toString();
                    webView.loadUrl(linkUrl);
                    link4.setTextColor(getResources().getColor(R.color.link_clicked));
                }
            });


            /************************** English Requirement *************************/
            Element titleElement3 = doc.getElementById("eng-req");
            String titleString3 = titleElement3.select("strong").text();
            title3.setText(titleString3);
            String tpString31 = titleElement3.select("p").get(1).text();
            tp31.setText(tpString31);
            // items = 5
            String tpString32 = "";
            for (int i = 2; i<=6; i++){
                tpString32 = tpString32 + titleElement3.select("p").get(i).text() + "\n";
            }
            tp32.setText(tpString32);
        }
    }
}
