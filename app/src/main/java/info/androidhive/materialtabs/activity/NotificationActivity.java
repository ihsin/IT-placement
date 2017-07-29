package info.androidhive.materialtabs.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import info.androidhive.materialtabs.R;

/**
 * Created by rahulranjansinha on 02-07-2017.
 */

public class NotificationActivity extends AppCompatActivity{
    TextView notificationText;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.notification_data);
        //   Bundle extras = getIntent().getExtras();  //grabs the intent that started the activity
        Notification notification= (Notification) getIntent().getSerializableExtra("Value1");
        notificationText=(TextView) findViewById(R.id.notification);
        if(notification!=null) {
            notificationText.setText(Html.fromHtml(notification.getNotification()));
        }
        else
            throw new NullPointerException("Comany null hai bhai");
    }
}
