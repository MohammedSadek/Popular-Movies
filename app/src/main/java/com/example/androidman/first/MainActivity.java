package com.example.androidman.first;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    GridView gridView;
    ArrayList<MovieData> movie_data = new ArrayList<>();
    Asynctask asynctask;
    boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridview);
        asynctask = new Asynctask(MainActivity.this);

        flag= InternetDetails.networkinfo(MainActivity.this);
        if(flag==false) {
           print();
        }
        else {

            asynctask.execute("http://api.themoviedb.org/3/movie/top_rated?api_key=" + BuildConfig.MOVIE_API_KEY);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent(MainActivity.this, Details.class);
                    MovieData data = movie_data.get(i);
                    intent.putExtra("title", data.getTitle());
                    intent.putExtra("image", data.getImageSource());
                    intent.putExtra("overview", data.getOverview());
                    intent.putExtra("date_relase", data.getRelaseDate());
                    intent.putExtra("vote", data.getVote());

                    startActivity(intent);
                }
            });
        }

    }

    public class Asynctask extends AsyncTask<String, Void, String> {
        private final Context context;
        String k = null;

        public Asynctask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... strings) {
            String s = strings[0];
            try {
                    HttpURLConnection httpURLConnection = InternetDetails.openconnection(s);
                    k = InternetDetails.getdata(httpURLConnection);
                } catch(IOException e){
                    e.printStackTrace();
                }
                return k;
            }



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray array = jsonObject.getJSONArray("results");
                movie_data.clear();

                for (int i = 0; i < array.length(); i++) {
                    MovieData movie = new MovieData();
                    JSONObject jso = array.getJSONObject(i);
                    movie.setImageSource(jso.getString("poster_path"));
                    movie.setTitle(jso.getString("title"));
                    movie.setOverview(jso.getString("overview"));
                    movie.setVote(jso.getString("vote_count"));
                    movie.setId(jso.getString("id"));
                    movie.setRelaseDate(jso.getString("release_date"));
                    movie_data.add(movie);

                }
                gridView.setAdapter(new PosterAdapter(context, movie_data));

            } catch (JSONException e) {


                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        flag= InternetDetails.networkinfo(MainActivity.this);
        if (item.getItemId() == R.id.top) {
            if(flag==false) {
                print();
            }
            else {

                new Asynctask(MainActivity.this).execute("http://api.themoviedb.org/3/movie/top_rated?api_key=" + BuildConfig.MOVIE_API_KEY);
            }
        }
        if (item.getItemId() == R.id.pop) {
            if (flag == false) {
                print();
            } else {
                new Asynctask(MainActivity.this).execute("http://api.themoviedb.org/3/movie/popular?api_key=" + BuildConfig.MOVIE_API_KEY);
            }
        }

        return super.onOptionsItemSelected(item);
    }
    public  void print()
    {
        new AlertDialog.Builder(this).setMessage("Please Check Your Internet Connection and Try Again")
                .setTitle("Network Error")
                .setCancelable(true)
                .setNeutralButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {
                                finish();
                            }
                        })
                .show();
    }



}


