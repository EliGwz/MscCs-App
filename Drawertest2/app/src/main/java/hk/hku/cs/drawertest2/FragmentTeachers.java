package hk.hku.cs.drawertest2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentTeachers extends Fragment {

    private ListView teacherListView;
    private String urlString;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher,container,false);
        urlString = "https://www.msc-cs.hku.hk/About/Faculty";
        teacherListView = view.findViewById(R.id.teacherListView);
        imageView = view.findViewById(R.id.teacher_img);

        new DownloadWebpageTask().execute(urlString);
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
            //Elements teacherElements = doc.select("article");
            Elements teacherElements = doc.getElementsByClass("portfolio-masonry-entry");
            System.out.println("/**************** YOU ARE IN FRAGMENT TEACHER ********************/");
            if (teacherElements.hasText() == true){
                System.out.println("teacherElements size = "+ Integer.toString(teacherElements.size()));

                Elements imgElements = teacherElements.select("img[src]");
                Elements nameElements = teacherElements.select("span.activator");
                Elements titleElements = teacherElements.select("div.card-reveal").select("span[style]");
                Elements fieldElements = teacherElements.select("div.rFieldContent");

                /***
                System.out.println("imgElements size = " + Integer.toString(imgElements.size()));
                System.out.println("nameElements size = " + Integer.toString(nameElements.size()));
                System.out.println("titleElements size = " + Integer.toString(titleElements.size()));
                System.out.println("fieldElements size = " + Integer.toString(fieldElements.size()));
                 ***/

                String[] strings = {"img", "name", "title", "field"}; // map key
                int[] ids = {R.id.teacher_img, R.id.teacher_name, R.id.teacher_title, R.id.teacher_researchField}; // teacher_list.xml

                ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
                HashMap<String, Object> map = null;

                for (int i = 0; i < teacherElements.size(); i++){
                    map = new HashMap<String, Object>();
                    map.put("img", "https://www.msc-cs.hku.hk"+imgElements.get(i).attr("src"));
                    String name = nameElements.get(i).text();
                    map.put("name", name.substring(0, name.length()-9)); // cut off the redundant string "more_vert"
                    map.put("title", titleElements.get(i).text());
                    map.put("field", fieldElements.get(i).text());
                    list.add(map);
                }

                //SimpleAdapter myAdapter = new SimpleAdapter(getContext(), list, R.layout.teacher_list, strings, ids);
                MyAdapter myAdapter = new MyAdapter(getContext(), list, R.layout.teacher_list, strings, ids);
                teacherListView.setAdapter(myAdapter);

            } else {
                System.out.println("teacherElements is empty");
            }
            System.out.println("/**************** END OF FRAGMENT TEACHER ********************/");
        }
    }

    // https://stackoverflow.com/questions/28064264/android-how-can-diplay-pictures-in-a-simpleapapter-list-view
    public class MyAdapter extends SimpleAdapter{

        public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to){
            super(context, data, resource, from, to);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            // here you let SimpleAdapter built the view normally.
            View v = super.getView(position, convertView, parent);

            // Then we get reference for Picasso
            ImageView img = (ImageView) v.getTag();
            if(img == null){
                img = (ImageView) v.findViewById(R.id.teacher_img);
                v.setTag(img); // <<< THIS LINE !!!!
            }
            // get the url from the data you passed to the `Map`
            String url = ((Map)getItem(position)).get((Object)"img").toString();
            System.out.println(url);
            // do Picasso
            // maybe you could do that by using many ways to start
            Picasso.get().load(url).into(img);

            // return the view
            return v;
        }
    }
}
