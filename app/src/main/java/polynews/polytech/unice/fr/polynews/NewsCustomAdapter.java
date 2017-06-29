package polynews.polytech.unice.fr.polynews;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import polynews.polytech.unice.fr.polynews.model.News;
import polynews.polytech.unice.fr.polynews.util.ThumbnailsLoader;

/**
 * Created by Joel CANCELA VAZ on 22/03/2017.
 */

public class NewsCustomAdapter extends ArrayAdapter<News> {
    private LayoutInflater inflater;

    public NewsCustomAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<News> objects) {
        super(context, 0, objects);
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.news_adapter_layout, null);
        }
        TextView date = (TextView) convertView.findViewById(R.id.dateNews);
        TextView category = (TextView) convertView.findViewById(R.id.categoryNews);
        TextView description = (TextView) convertView.findViewById(R.id.descriptionNews);
        ImageView thumbnail = (ImageView) convertView.findViewById(R.id.imageNews);
        thumbnail.setImageResource(R.drawable.placeholder);

        News currentNews = this.getItem(position);
        date.setText(new SimpleDateFormat("dd/MM/yy").format(currentNews.getDate()));
        category.setText(currentNews.getCategory().name());
        description.setText(currentNews.getContent());
        ThumbnailsLoader thumbnailsLoader = new ThumbnailsLoader(thumbnail);
        thumbnailsLoader.execute(currentNews.getThumbnailURL());

        return convertView;
    }
}
