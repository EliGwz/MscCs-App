package hk.hku.cs.drawertest2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentTeachers extends Fragment {

    private TextView aboutText;
    private ListView teacherListView;
    private String urlString;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher,container,false);
        urlString = "https://www.msc-cs.hku.hk/About/Faculty";
        teacherListView = view.findViewById(R.id.teacherListView);

        String[] strings = {"img", "name", "title", "field"}; // map key
        int[] ids = {R.id.teacher_img, R.id.teacher_name, R.id.teacher_title, R.id.teacher_researchField}; // teacher_list.xml
        SimpleAdapter simpleAdapter = new SimpleAdapter(this.getContext(), getData(), R.layout.teacher_list, strings, ids);
        teacherListView.setAdapter(simpleAdapter);
        new DownloadWebpageTask().execute(urlString);
        return view;
    }

    private List<HashMap<String, Object>> getData() {
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = null;
        for (int i=0; i<10; i++){
            map = new HashMap<String, Object>();
            map.put("img", R.drawable.t1_chanhth);
            map.put("name", "Dr. Chan H.T.H."+i);
            map.put("title", "PhD (Carnegie Mellon)");
            map.put("field", "Algorithms, Combinatorial Optimization, Graphs, Information Networks, Security & Privacy");
            list.add(map);
        }
        return list;
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
            Elements teacherElements = doc.select("mportfolio-masonry-entry");
            System.out.println("/**************** YOU ARE IN FRAGMENT TEACHER ********************/");
            for (Element e: teacherElements){
                String imgUrl = e.select("img[src$=.jpg]").text();
                String name = e.select("card-title").first().text();
                String title = e.select("font-size:14px").text();
                String field = e.select("rFieldContent").text();
                System.out.println(name + "\n");
            }
            System.out.println("/**************** END OF FRAGMENT TEACHER ********************/");
        }
    }
}
