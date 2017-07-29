package info.androidhive.materialtabs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.fragments.OneFragment;

/**
 * Created by rahulranjansinha on 02-07-2017.
 */

public class NotificationListActivity extends AppCompatActivity implements NotificationAdapter.NotificationAdapterOnClickHandler{

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Notification> listItems;

    public NotificationListActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_list_activity);
        recyclerView=(RecyclerView)findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar=(ProgressBar)findViewById(R.id.circular_progress);
        listItems=new ArrayList<>();
        initFirebase();
        addEventFirebaseListener();

    /*   for(int i=0;i<20;i++)
        {
            Company listItem=new Company(Integer.toString(i+1),Integer.toString(i+i));
            listItems.add(listItem);
        }
        adapter=new CompanyAdapter(listItems,this.getContext());
        recyclerView.setAdapter(adapter); */
    }

    private void initFirebase(){
        FirebaseApp.initializeApp(this);
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();
    }


    private void addEventFirebaseListener() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        databaseReference.child("Notification").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(listItems.size()>0)
                    listItems.clear();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    Notification notification=new Notification();          //neccesary to initialize
                    notification.setDate(postSnapshot.getValue(Notification.class).getDate());
                    notification.setNotification_title(postSnapshot.getValue(Notification.class).getNotification_title());
                    notification.setNotification(postSnapshot.getValue(Notification.class).getNotification());
                    listItems.add(notification);
                }
                if(listItems.size()>0) {
                    NotificationAdapter adapter = new NotificationAdapter(listItems,NotificationListActivity.this,NotificationListActivity.this);
                    // Context must not be null if context instead of getContext()
                    recyclerView.setAdapter(adapter);
                }else{
                    Toast.makeText(NotificationListActivity.this,"No data to display",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void onClick(Notification notification) {
        if(notification!=null){
            Intent txt = new Intent(NotificationListActivity.this, NotificationActivity.class);  //Here we just pass getActivity
            txt.putExtra("Value1", notification);  		//key-value pair(a.k.a Extras). Keys to identify value.
            // Set the request code to any code you like, you can identify the
            // callback via this code
            startActivity(txt);
        }
        //  Log.d("One Fragment", company.getName());
        //Toast.makeText(getActivity().getBaseContext(),Integer.toString(adapterPosition), Toast.LENGTH_SHORT).show(); // Use getActivity.getBaseContext() for Toast inside click of Fragment
        else
            throw new NullPointerException("company null hai bhai!!!");
    }
}
