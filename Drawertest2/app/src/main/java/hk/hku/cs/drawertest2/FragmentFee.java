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

public class FragmentFee extends Fragment {

    private TextView tp11;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fee,container,false);

        String stringUrl = "https://www.msc-cs.hku.hk/Admission/Fees";
        tp11 = (TextView) view.findViewById(R.id.fee_p11);

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
            Element elementFee = doc.getElementById("comp-fee");
            // items = 6
            String tpString11 = "";
            for (int i = 0; i<=5; i++){
                tpString11 = tpString11 + elementFee.select("p").get(i).text() + "\n\n";
            }
            tp11.setText(tpString11);
        }

    }

}
