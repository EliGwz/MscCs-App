package hk.hku.cs.drawertest2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String DEBUG_TAG = "msccsIndexPage";
    private TextView contentTextView;
    private String textString = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // show FragmentHome when first start
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();
            //Intent intent = new Intent(this,HomeActivity.class); startActivity(intent);
        } //else if (id == R.id.nav_about) {
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentAbout()).commit();
        //}
        else if (id == R.id.nav_admission) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentAdmission()).commit();
        } else if (id == R.id.nav_application) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentApplication()).commit();
        } else if (id == R.id.nav_fee) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentFee()).commit();
        } else if (id == R.id.nav_overview) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentOverview()).commit();
        } else if (id == R.id.nav_course) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentCourse()).commit();
        } else if (id == R.id.nav_teacher) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentTeachers()).commit();
        } else if (id == R.id.nav_duration) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentDuration()).commit();
        } else if (id == R.id.nav_regulation) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentRegulation()).commit();
        } else if (id == R.id.nav_contact){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentContact()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


}
