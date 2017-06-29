package polynews.polytech.unice.fr.polynews.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import polynews.polytech.unice.fr.polynews.model.ECategory;
import polynews.polytech.unice.fr.polynews.model.EMediaType;
import polynews.polytech.unice.fr.polynews.model.News;


public class NewsDBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "polynews_database";

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public NewsDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void openDataBase() throws SQLException, IOException {
        //Open the database
        String myPath = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                // Copy the database in assets to the application database.
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database", e);
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            //database doesn't exist yet.
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static News getNews(Cursor cursor) {
        News news;
        String id = Integer.toString(cursor.getInt(7));
        String title = cursor.getString(0);
        String content = cursor.getString(1);
        String author = cursor.getString(2);
        String date = cursor.getString(3);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-M-d kk:mm:ss.S");
        Date dateresult = new Date();
        try {
            dateresult = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int category = cursor.getInt(4);
        int mediatype = cursor.getInt(5);
        String mediapath = cursor.getString(6);

        news = new News(id, title, content, author, dateresult, ECategory.intToECategory(category), EMediaType.intToEMediaType(mediatype), mediapath, mediapath);
        return news;
    }

    public List<News> getAllArticles() {
        List<News> newsList = new ArrayList<>();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM news ORDER BY date DESC", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String id = Integer.toString(cursor.getInt(7));
            String title = cursor.getString(0);
            String content = cursor.getString(1);
            String author = cursor.getString(2);
            String date = cursor.getString(3);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-M-d kk:mm:ss.S");
            Date dateresult = new Date();
            try {
                dateresult = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int category = cursor.getInt(4);
            int mediatype = cursor.getInt(5);
            String mediapath = cursor.getString(6);

            News news = new News(id, title, content, author, dateresult, ECategory.intToECategory(category), EMediaType.intToEMediaType(mediatype), mediapath, mediapath);
            newsList.add(news);
            cursor.moveToNext();
        }
        cursor.close();
        return newsList;
    }

    public SQLiteDatabase getMyDataBase(){
        return myDataBase;
    }
}