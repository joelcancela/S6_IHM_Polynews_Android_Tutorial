package polynews.polytech.unice.fr.polynews.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import polynews.polytech.unice.fr.polynews.R;

/**
 * Created by Joel CANCELA VAZ on 29/03/2017.
 */

public class ThumbnailsLoader extends AsyncTask<String, Void, Bitmap> {

    private ImageView currentImageView;

    public ThumbnailsLoader(ImageView imageView) {
        currentImageView=imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bmp = null;
        String str[];
        try {
            if (params[0].contains("youtube")) {
                str =  params[0].split("v=");
                params[0] = "http://img.youtube.com/vi/" + str[1] + "/default.jpg";
            }
            InputStream inptStr = (InputStream) new URL(params[0]).getContent();
            bmp = BitmapFactory.decodeStream(inptStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap!=null){
            currentImageView.setImageBitmap(bitmap);
        }
    }
}
