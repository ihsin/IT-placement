package info.androidhive.materialtabs.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import info.androidhive.materialtabs.activity.News;
import info.androidhive.materialtabs.activity.NewsActivity;
import info.androidhive.materialtabs.activity.NewsAdapter;
import info.androidhive.materialtabs.activity.SimpleDividerItemDecoration;


public class TwoFragment extends Fragment implements NewsAdapter.NewsAdapterOnClickHandler{

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<News> listItems;

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_two, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.rvNumbers);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this.getContext()));
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),numberOfColumns));
        progressBar=(ProgressBar)view.findViewById(R.id.circular_progress);
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
        return view;
    }

    private void initFirebase(){
        FirebaseApp.initializeApp(this.getContext());
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();
    }


    private void addEventFirebaseListener() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        databaseReference.child("News").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(listItems.size()>0)
                    listItems.clear();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    News news=new News();          //neccesary to initialize
                    news.setNews(postSnapshot.getValue(News.class).getNews());
                    news.setSiteURL(postSnapshot.getValue(News.class).getSiteURL());
                    news.setImageURL(postSnapshot.getValue(News.class).getImageURL());
                    listItems.add(news);
                }
                if(listItems.size()>0) {
                    NewsAdapter adapter = new NewsAdapter(listItems,getContext(),TwoFragment.this);
                    // Context must not be null if context instead of getContext()
                    recyclerView.setAdapter(adapter);
                }else{
                    Toast.makeText(getContext(),"No data to display",Toast.LENGTH_SHORT).show();
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
    public void onClick(News news) {
        if(news!=null){
            Intent txt = new Intent(getActivity(), NewsActivity.class);  //Here we just pass getActivity
            txt.putExtra("Value1",news.getSiteURL());  		//key-value pair(a.k.a Extras). Keys to identify value.
            // Set the request code to any code you like, you can identify the
            // callback via this code
            startActivity(txt);
        }
        //  Log.d("One Fragment", company.getName());
        //Toast.makeText(getActivity().getBaseContext(),Integer.toString(adapterPosition), Toast.LENGTH_SHORT).show(); // Use getActivity.getBaseContext() for Toast inside click of Fragment
        else
            throw new NullPointerException("news null hai bhai!!!");
    }
}
