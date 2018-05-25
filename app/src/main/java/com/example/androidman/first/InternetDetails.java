package com.example.androidman.first;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InternetDetails
{

    public static boolean networkinfo(Context context)
    {
        ConnectivityManager connectivemanager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo=connectivemanager.getActiveNetworkInfo();
        if(networkinfo!=null) {
            //Toast.makeText(context,"good",Toast.LENGTH_LONG).show();

            return true;
        }
        else {
            //Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public static HttpURLConnection openconnection(String link) throws IOException
    {
        URL url=new URL(link);
        HttpURLConnection http=(HttpURLConnection)url.openConnection();
        http.setRequestMethod("GET");
        http.setConnectTimeout(1000);
        http.setReadTimeout(10000);
        http.setDoInput(true);
        return http;
    }
    public  static String getdata(HttpURLConnection http) throws IOException
    {
        InputStream inputStream=http.getInputStream();
        StringBuilder stringBuilder=new StringBuilder();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line=bufferedReader.readLine())!=null)
        {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }


}
