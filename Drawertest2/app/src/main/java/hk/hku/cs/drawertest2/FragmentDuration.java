package hk.hku.cs.drawertest2;

import android.os.AsyncTask;
import android.os.Bundle;
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

public class FragmentDuration extends Fragment {

    private TextView tp11,tp12,tp13,tp14,tp21;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_duration,container,false);
        String stringUrl = "https://www.msc-cs.hku.hk/Curriculum/Schedule";
        tp11 = view.findViewById(R.id.duration_p11);
        tp12 = view.findViewById(R.id.duration_p12);
        tp13 = view.findViewById(R.id.duration_p13);
        tp14 = view.findViewById(R.id.duration_p14);
        tp21 = view.findViewById(R.id.schedule_p21);

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
        protected void onPostExecute(Document doc){
            Element elementDuration = doc.getElementById("duration-of-study");
            tp11.setText(elementDuration.select("p").get(0).text());
            tp12.setText(elementDuration.select("p").get(1).text());
            tp13.setText(elementDuration.select("p").get(2).text());
            tp14.setText(elementDuration.select("p").get(3).text());
            tp21.setText(elementDuration.select("p").get(4).text());
        }

    }
}
