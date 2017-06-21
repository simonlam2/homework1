package com.example.nomis.newsapp;

/**
 * Created by Nomis on 6/21/2017.
 */

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
public class NetworkUtils {
    //https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=
    //9afb25d9435f4779934d24d24cb2cc33
    final static String base_url =
            "https://newsapi.org/v1/articles";
    final static String PARAM_SOURCE = "source";
    final static String source0 = "the-next-web";

    final static String PARAM_SORT = "sortBy";
    final static String sortBy0 = "latest";

    final static String PARAM_KEY = "apiKey";
    final static String apiKey0 = "9afb25d9435f4779934d24d24cb2cc33";


    public static URL buildUrl(String testing){
        Uri buildUri = Uri.parse(base_url).buildUpon()
                .appendQueryParameter(PARAM_SOURCE, testing)
                .appendQueryParameter(PARAM_SORT,sortBy0)
                .appendQueryParameter(PARAM_KEY,apiKey0)
                .build();


        URL url = null;
        try{
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}

