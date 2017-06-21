package com.example.nomis.newsapp;

import android.os.AsyncTask;
//import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.example.nomis.newsapp.NetworkUtils;
import java.io.IOException;
import java.net.URL;
//import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView mSearchResultsTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchResultsTextView = (TextView) findViewById(R.id.tv_newsapp_results);
    }
    class NewsAppTask extends AsyncTask<URL, Void, String>{
        String source;

        NewsAppTask(String s){
            source = s;
        }
        @Override
        protected String doInBackground(URL...params){
            //String results = null;
            String results = null;
            URL url = NetworkUtils.buildUrl(NetworkUtils.source0);
            //Log.d("mainactivity", "url: " + url.toString());
            try{
                results = NetworkUtils.getResponseFromHttpUrl(url);
            }catch (IOException e){
                e.printStackTrace();
            }
            return results;
        }
        @Override
        protected void onPostExecute(String results){
            if(results != null && !results.equals("")){
                mSearchResultsTextView.setText(results);
            }
        }

    }
}
