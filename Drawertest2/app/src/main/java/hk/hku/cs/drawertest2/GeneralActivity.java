package hk.hku.cs.drawertest2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w3c.dom.Text;

import java.io.IOException;

public class GeneralActivity extends AppCompatActivity {

    private TextView text_general;
    private Button home_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        String stringUrl = "https://www.msc-cs.hku.hk/Curriculum/Programme-Overview";
        text_general = (TextView) findViewById(R.id.text_general);
        home_btn = (Button) findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        new DownloadWebpageTask().execute(stringUrl);
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
            Element element = document.getElementById("SCardProgOver");
            String textString = element.select("p").last().text();
            text_general.setText(textString);
        }
    }
}
