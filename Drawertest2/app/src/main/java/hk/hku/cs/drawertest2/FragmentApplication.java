package hk.hku.cs.drawertest2;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class FragmentApplication extends Fragment {

    private TextView header, tp11, tp12, tp13, tp14, applyBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application,container,false);

        String stringUrl = "https://www.msc-cs.hku.hk/Admission/Procedures";
        header = (TextView) view.findViewById(R.id.admissionText);
        tp11 = (TextView) view.findViewById(R.id.how_to_p11);
        tp12 = (TextView) view.findViewById(R.id.how_to_p12);
        tp13 = (TextView) view.findViewById(R.id.how_to_p13);
        tp14 = (TextView) view.findViewById(R.id.how_to_p14);
        applyBtn = (TextView) view.findViewById(R.id.applyBtn);

        new DownloadWebpageTask().execute(stringUrl);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),OnlineApplication.class);
                String appUrl = "https://aal.hku.hk/tpg/programme/master-science-computer-science";
                intent.putExtra("extraTag",appUrl);
                startActivity(intent);
                //applyBtn.setTextColor(Color.RED);
            }
        });
        return view;
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, Document>{
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
            String title = doc.title();
            header.setText(title);

            Element elementHowTo = doc.getElementById("how-apply");
            String tpString11 = elementHowTo.select("p").first().text();
            tp11.setText(tpString11);

            String tpString12 = elementHowTo.select("p").get(2).text();
            tp12.setText(tpString12);
        }

    }

}
