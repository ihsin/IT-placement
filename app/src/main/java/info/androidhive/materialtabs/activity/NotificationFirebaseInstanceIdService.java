package info.androidhive.materialtabs.activity;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by rahulranjansinha on 02-07-2017.
 */

public class NotificationFirebaseInstanceIdService extends FirebaseInstanceIdService {
//EveryTime a Token is added this method will be called
    private static final String REG_TOKEN="REG_TOKEN";
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String recent_token= FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN,recent_token);
    }
}
