package com.example.androidman.first;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class PosterAdapter extends BaseAdapter {
    private final ArrayList<MovieData> movie_data;
    private final LayoutInflater inflater;
    private final Context context;
    public PosterAdapter(Context context, ArrayList<MovieData>movie_data)
    {
        this.inflater= LayoutInflater.from(context);
        this.movie_data=movie_data;
        this.context=context;
    }
    @Override
    public int getCount()

    {
        return movie_data.size();
    }
    @Override
    public Object getItem(int i)
    {
        return movie_data.get(i);
    }
    @Override
    public long getItemId(int i)
    {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View scope=inflater.inflate(R.layout.posterlayout,viewGroup,false);
        ImageView image=(ImageView)scope.findViewById(R.id.imageView);
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185/"+ movie_data.get(i).getImageSource() +"").into(image);
        return scope;
    }
}
