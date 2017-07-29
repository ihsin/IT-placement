package info.androidhive.materialtabs.activity;

import java.io.Serializable;

/**
 * Created by rahulranjansinha on 02-07-2017.
 */

public class Notification implements Serializable{
    String date;
    String notification;
    String notification_title;

    public Notification() {
    }

    public Notification(String date, String notification, String notification_title) {
        this.date = date;
        this.notification = notification;
        this.notification_title=notification_title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getNotification_title() {
        return notification_title;
    }

    public void setNotification_title(String notification_title) {
        this.notification_title = notification_title;
    }
}
