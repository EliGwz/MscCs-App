package hk.hku.cs.drawertest2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentCourse extends Fragment {

    private ListView courseListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment cyberSecurityFragment = new FragmentCourseCyberSecurity();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.cyberSecurityFragment, cyberSecurityFragment).commit();

        Fragment financialFragment = new FragmentCourseFinancial();
        FragmentTransaction transaction2 = getChildFragmentManager().beginTransaction();
        transaction2.replace(R.id.financialFragment, financialFragment).commit();

        Fragment multimediaFragment = new FragmentCourseMultimedia();
        FragmentTransaction transaction3 = getChildFragmentManager().beginTransaction();
        transaction3.replace(R.id.multimediaFragment, multimediaFragment).commit();

        Fragment otherCoursesFragment = new FragmentCourseOther();
        FragmentTransaction transaction4 = getChildFragmentManager().beginTransaction();
        transaction4.replace(R.id.otherCoursesFragment, otherCoursesFragment).commit();
    }
}
