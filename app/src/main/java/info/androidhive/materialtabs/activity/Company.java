package info.androidhive.materialtabs.activity;

import java.io.Serializable;

/**
 * Created by rahulranjansinha on 09-06-2017.
 */

public class Company implements Serializable{
    String name;
    String URL;
    String about;
    String portfolio;
    String interviews;

    public Company(String name, String URL, String about, String portfolio, String interviews) {
        this.name = name;
        this.URL = URL;
        this.about=about;
        this.portfolio=portfolio;
        this.interviews=interviews;
    }

    public Company() {          //Without this there was error it wasn't able to load company names
    }                           // so First all objects in database must be initialized.

    public String getName() {
        return name;
    }

    public String getURL() {
        return URL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setURL(String arrivaldate) {
        this.URL = arrivaldate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getInterviews() {
        return interviews;
    }

    public void setInterviews(String interviews) {
        this.interviews = interviews;
    }
}
