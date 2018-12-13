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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w3c.dom.Text;

import java.io.IOException;

public class FragmentOverview extends Fragment {

    private TextView capstone_p1;
    private Button security_btn, multimedia_btn, financial_btn, general_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_overview,container,false);

        security_btn = view.findViewById(R.id.security);
        multimedia_btn = view.findViewById(R.id.multimedia);
        financial_btn = view.findViewById(R.id.financial);
        general_btn = view.findViewById(R.id.general);
        capstone_p1 = view.findViewById(R.id.capstone_p1);

        security_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to cyber security activity
                Intent intent = new Intent(v.getContext(),SecurityActivity.class);
                startActivity(intent);
            }
        });

        multimedia_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to multimedia activity
                Intent intent = new Intent(v.getContext(), MultimediaActivity.class);
                startActivity(intent);
            }
        });

        financial_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to financial activity
                Intent intent = new Intent(v.getContext(), FinancialActivity.class);
                startActivity(intent);
            }
        });

        general_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to general activity
                Intent intent = new Intent(v.getContext(), GeneralActivity.class);
                startActivity(intent);
            }
        });

        String stringUrl = "https://www.msc-cs.hku.hk/Curriculum/Programme-Overview";
        new DownloadWebpageTask().execute(stringUrl);

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
            // Programme Overview
            for (int i=0;i<3;i++){
                Element element = document.getElementById("programme-structure");
                String textString = element.select("p").get(i).text();

                // find overviewLayout by id
                LinearLayout overviewLayout = getView().findViewById(R.id.overviewLayout);
                // add TextView to overviewlayout
                TextView overviewTv = new TextView(overviewLayout.getContext());
                // set layout_width and layout_height
                LinearLayout.LayoutParams ov_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                // set margin
                ov_params.setMargins(1,1,1,20);
                overviewTv.setLineSpacing(overviewTv.getLineSpacingExtra(),1.2f);
                overviewLayout.setLayoutParams(ov_params);
                overviewTv.setText(textString);
                overviewLayout.addView(overviewTv);
            }
            // capstone experience
            Element element2 = document.getElementById("content");
            String textCapstone = element2.select("p").last().text();
            capstone_p1.setText(textCapstone);
        }
    }


}
