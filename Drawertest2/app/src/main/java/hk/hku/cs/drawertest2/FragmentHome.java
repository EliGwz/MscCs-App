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
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class FragmentHome extends Fragment {

    private Button buttonNext;
    private TextView header, overview_context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);


        buttonNext = view.findViewById(R.id.next_btn);
        // Changing Dummy Content
        String stringUrl = "https://www.msc-cs.hku.hk/";
        header = view.findViewById(R.id.title);
        header.setText("Master of Science\nin Computer Science");
        overview_context = view.findViewById(R.id.overview_context);
        new DownloadWebpageTask().execute(stringUrl);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switch to activity "What kind of talent do you want to get?"
                Intent intent = new Intent(v.getContext(),TalentActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, Document> {
        @Override
        protected Document doInBackground(String... strings) {
            String url = strings[0];
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
            }catch (IOException e) {
                e.printStackTrace();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document document) {
            Element titleElement = document.getElementById("layout-before-main");
            String titleString = titleElement.select("p").get(0).text();
            overview_context.setText(titleString);
        }
    }
}
