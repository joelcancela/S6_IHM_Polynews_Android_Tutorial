package polynews.polytech.unice.fr.polynews;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import polynews.polytech.unice.fr.polynews.db.DBCursorAdapter;
import polynews.polytech.unice.fr.polynews.model.News;
import polynews.polytech.unice.fr.polynews.util.ThumbnailsLoader;

/**
 * Created by Joel CANCELA VAZ on 30/03/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NewsViewHolder> {

    private List<News> newsList;
    private DBCursorAdapter cursorAdapter;

    public RecyclerViewAdapter(List<News> newsList) {
        this.newsList = newsList;
    }
    public RecyclerViewAdapter(DBCursorAdapter cursorAdapter) {
        this.cursorAdapter = cursorAdapter;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
        public CardView cardview;

        public NewsViewHolder(CardView cardview) {
            super(cardview);
            this.cardview=cardview;
        }
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        LinearLayout ll = (LinearLayout) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.news_adapter_layout, parent, false);
       CardView cardview = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_card_layout, parent, false);

//        NewsViewHolder vh = new NewsViewHolder(ll);
        NewsViewHolder vh = new NewsViewHolder(cardview);
        return vh;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
//        TextView date = (TextView) holder.linearLayout.findViewById(R.id.dateNews);
//        TextView category = (TextView) holder.linearLayout.findViewById(R.id.categoryNews);
//        TextView description = (TextView) holder.linearLayout.findViewById(R.id.descriptionNews);
//        ImageView thumbnail = (ImageView) holder.linearLayout.findViewById(R.id.imageNews);
//        thumbnail.setImageResource(R.drawable.placeholder);
//
//        News currentNews = this.newsList.get(position);
//        date.setText(new SimpleDateFormat("dd/MM/yy").format(currentNews.getDate()));
//        category.setText(currentNews.getCategory().name());
//        description.setText(currentNews.getContent());
//        ThumbnailsLoader thumbnailsLoader = new ThumbnailsLoader(thumbnail);
//        thumbnailsLoader.execute(currentNews.getThumbnailURL());

        //CARDVIEW
//        TextView date = (TextView) holder.cardview.findViewById(R.id.dateNews);
//        TextView category = (TextView) holder.cardview.findViewById(R.id.categoryNews);
//        TextView description = (TextView) holder.cardview.findViewById(R.id.descriptionNews);
//        ImageView thumbnail = (ImageView) holder.cardview.findViewById(R.id.imageNews);
//        thumbnail.setImageResource(R.drawable.placeholder);
//
//        News currentNews = this.newsList.get(position);
//        date.setText(new SimpleDateFormat("dd/MM/yy").format(currentNews.getDate()));
//        category.setText(currentNews.getCategory().name());
//        description.setText(currentNews.getContent());
//        ThumbnailsLoader thumbnailsLoader = new ThumbnailsLoader(thumbnail);
//        thumbnailsLoader.execute(currentNews.getThumbnailURL());

        cursorAdapter.getCursor().moveToPosition(position);
        cursorAdapter.bindView(holder.itemView, cursorAdapter.getContext(), cursorAdapter.getCursor());


    }

    @Override
    public int getItemCount() {
        return cursorAdapter.getCount();
    }
}
