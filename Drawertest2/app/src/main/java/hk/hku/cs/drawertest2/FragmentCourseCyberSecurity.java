package hk.hku.cs.drawertest2;

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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FragmentCourseCyberSecurity extends Fragment {

    private ListView courseListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_course_cyber_security,container,false);
        String stringUrl = "https://www.msc-cs.hku.hk/Curriculum/Courses";
        courseListView = view.findViewById(R.id.cyberSecurityCourseListView);
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
            String[] strings = {"course_no", "course_name"}; // map key
            int[] ids = {R.id.course_number_text, R.id.course_name_text}; // teacher_list.xml
            Elements couseListElements = doc.getElementsByClass("z-depth-3");
            Elements eachLineElements = couseListElements.select("tbody tr");
            System.out.println("eachLineElements.size() = " + eachLineElements.size());

            /************** For Cyber Security Stream **********************/
            ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
            HashMap<String, Object> map = null;
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
