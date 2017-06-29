package polynews.polytech.unice.fr.polynews.db;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import polynews.polytech.unice.fr.polynews.R;
import polynews.polytech.unice.fr.polynews.model.EMediaType;
import polynews.polytech.unice.fr.polynews.model.News;
import polynews.polytech.unice.fr.polynews.util.ThumbnailsLoader;

/**
 * Created by Joel CANCELA VAZ on 30/03/2017.
 */

public class DBCursorAdapter extends CursorAdapter {


    private Context context;

    public DBCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        this.context=context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.news_card_layout,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView date = (TextView) view.findViewById(R.id.dateNews);
        TextView category = (TextView) view.findViewById(R.id.categoryNews);
        TextView description = (TextView) view.findViewById(R.id.descriptionNews);
        ImageView thumbnail = (ImageView) view.findViewById(R.id.imageNews);
        thumbnail.setImageResource(R.drawable.placeholder);
        thumbnail.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView videoIcon = (ImageView)  view.findViewById(R.id.videoIcon);

        News currentNews = NewsDBHelper.getNews(cursor);
        date.setText(new SimpleDateFormat("dd/MM/yy").format(currentNews.getDate()));
        category.setText(currentNews.getCategory().name());
        description.setText(currentNews.getContent());
        if(currentNews.getMediaType() == EMediaType.PICTURE){
            videoIcon.setVisibility(View.INVISIBLE);
        }
        ThumbnailsLoader thumbnailsLoader = new ThumbnailsLoader(thumbnail);
        thumbnailsLoader.execute(currentNews.getThumbnailURL());
    }

    public Context getContext() {
        return context;
    }
}
