package info.androidhive.materialtabs.activity;

import java.io.Serializable;

/**
 * Created by rahulranjansinha on 30-06-2017.
 */

public class News implements Serializable {
    private String news;
    private String imageURL;
    private String siteURL;

    public News() {
    }

    public News(String news, String imageURL, String siteURL) {
        this.news = news;
        this.imageURL = imageURL;
        this.siteURL = siteURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setSiteURL(String siteURL) {
        this.siteURL = siteURL;
    }
}
