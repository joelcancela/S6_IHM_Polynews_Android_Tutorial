package polynews.polytech.unice.fr.polynews.model;

import java.net.URL;
import java.util.Date;

/**
 * Created by Joel CANCELA VAZ on 22/03/2017.
 */

public class News {
    private String id;
    private String title;
    private String content;
    private String author;
    private Date date;
    private ECategory category;
    private EMediaType mediaType;
    private String url;
    private String thumbnailURL;

    public News(String id, String title, String content, String author, Date date, ECategory category, EMediaType mediaType, String url, String thumbnailURL) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
        this.category = category;
        this.mediaType = mediaType;
        this.url = url;
        this.thumbnailURL = thumbnailURL;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public ECategory getCategory() {
        return category;
    }

    public EMediaType getMediaType() {
        return mediaType;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
