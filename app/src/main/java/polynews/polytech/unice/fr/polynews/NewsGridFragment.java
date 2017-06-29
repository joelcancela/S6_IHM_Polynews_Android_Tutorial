package polynews.polytech.unice.fr.polynews;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import polynews.polytech.unice.fr.polynews.db.DBCursorAdapter;
import polynews.polytech.unice.fr.polynews.db.NewsDBHelper;
import polynews.polytech.unice.fr.polynews.model.ECategory;
import polynews.polytech.unice.fr.polynews.model.EMediaType;
import polynews.polytech.unice.fr.polynews.model.News;

/**
 * Created by Joel CANCELA VAZ on 22/03/2017.
 */

public class NewsGridFragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_grid, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<News> newsList = new ArrayList<>();
//        News firstNews = new News("0","MyFirstArticle","MyContent","J.C.",new Date(), ECategory.POLITICS, EMediaType.PICTURE,"http://static.eyrolles.com/img/2/7/5/4/0/3/1/7/9782754031790_h430.jpg","http://static.eyrolles.com/img/2/7/5/4/0/3/1/7/9782754031790_h430.jpg");
//        News secondNews = new News("1","Another one","MyContent2","Adam Jensen",new Date(), ECategory.SOCIETY, EMediaType.VIDEO,"https://www.youtube.com/watch?v=dQw4w9WgXcQ","http://img.youtube.com/vi/dQw4w9WgXcQ/default.jpg");
//        newsList.add(firstNews);
//        newsList.add(secondNews);
        NewsDBHelper newsDBHelper = new NewsDBHelper(getContext());
        SQLiteDatabase database = null;
        try {
//            newsDBHelper.createDataBase();
//            newsDBHelper.openDataBase();
//            newsList = newsDBHelper.getAllArticles();
//            newsDBHelper.close();
            newsDBHelper.createDataBase();
            newsDBHelper.openDataBase();
            database = newsDBHelper.getMyDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ListAdapter listAdapter = new NewsCustomAdapter(this.getContext(),0,newsList);

//        GridView gridView = (GridView) getView().findViewById(R.id.newsGridView);
//        gridView.setAdapter(listAdapter);


        Cursor cursor = database.rawQuery("SELECT * FROM news ORDER BY date DESC", null);


        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.newsGridView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

//        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(newsList);
//        recyclerView.setAdapter(recyclerViewAdapter);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(new DBCursorAdapter(this.getContext(), cursor));
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
