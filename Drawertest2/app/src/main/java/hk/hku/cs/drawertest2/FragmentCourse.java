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

public class FragmentCourse extends Fragment {

    private TextView tp1;
    private ListView courseListView, courseListView2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course,container,false);

        String stringUrl = "https://www.msc-cs.hku.hk/Curriculum/Courses";
        courseListView = view.findViewById(R.id.courseListView);
        //courseListView2 = view.findViewById(R.id.courseListView2);
        //tp1 = view.findViewById(R.id.course_p11);
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
            /***
            Elements courseElements = doc.getElementsByClass("orchard-layouts-root");
            Elements textStringElements = courseElements.select("p");
            String textString = textStringElements.select("p").get(0).text();
            tp1.setText(textString);
             ***/

            String[] strings = {"course_no", "course_name"}; // map key
            int[] ids = {R.id.course_number_text, R.id.course_name_text}; // teacher_list.xml

            ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
            HashMap<String, Object> map = null;

            Elements couseListElements = doc.getElementsByClass("z-depth-3");
            //Elements eachLineElements = couseListElements.select("td.purple");
            Elements eachLineElements = couseListElements.select("tbody tr");
            System.out.println("eachLineElements.size() = " + eachLineElements.size());
            // cyber security
            /*for (Element e:  eachLineElements){
                //map = new HashMap<String, Object>();
                System.out.println(e.select("p").text());
            }*/

            map = new HashMap<String, Object>();
            map.put("course_no", eachLineElements.get(1).select("td").select("p").get(1).text());
            map.put("course_name", eachLineElements.get(1).select("td").select("p").get(2).text());
            list.add(map);

            for (int i=2; i<7;i++){
                map = new HashMap<String, Object>();
                map.put("course_no", eachLineElements.get(i).select("td").select("p").get(0).text());
                map.put("course_name", eachLineElements.get(i).select("td").select("p").get(1).text());
                list.add(map);
                System.out.println(eachLineElements.get(i).select("td").text());
            }

            SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), list, R.layout.course_list, strings, ids);
            courseListView.setAdapter(simpleAdapter);



        }

    }
}
