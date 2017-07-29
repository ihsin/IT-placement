package info.androidhive.materialtabs.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import info.androidhive.materialtabs.activity.Company;
import info.androidhive.materialtabs.activity.CompanyActivity;
import info.androidhive.materialtabs.activity.CompanyAdapter;
import info.androidhive.materialtabs.activity.InterviewActivity;
import info.androidhive.materialtabs.activity.InterviewAdapter;
import info.androidhive.materialtabs.activity.Interviews;
import info.androidhive.materialtabs.activity.SimpleDividerItemDecoration;


public class ThreeFragment extends Fragment implements InterviewAdapter.InterviewsAdapterOnClickHandler{

    private Context context=getContext();
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Interviews> listItems;

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_three, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this.getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
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
        databaseReference.child("interviews").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(listItems.size()>0)
                    listItems.clear();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    Interviews interview=new Interviews();          //neccesary to initialize
                    interview.setCompanyName(postSnapshot.getValue(Interviews.class).getCompanyName());
                    interview.setInterviewerName(postSnapshot.getValue(Interviews.class).getInterviewerName());
                    interview.setDate(postSnapshot.getValue(Interviews.class).getDate());
                    interview.setCodingRound(postSnapshot.getValue(Interviews.class).getCodingRound());
                    interview.setInterviewRound(postSnapshot.getValue(Interviews.class).getInterviewRound());
                    listItems.add(interview);
                }
                if(listItems.size()>0) {
                    InterviewAdapter adapter = new InterviewAdapter(listItems,getContext(),ThreeFragment.this);
                    // Context must not be null if context instead of getContext()
                    recyclerView.setAdapter(adapter);
                }else{
                    Toast.makeText(context,"No data to display",Toast.LENGTH_SHORT).show();
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
    public void onClick(Interviews interview) {
        if(interview!=null){
            Intent txt = new Intent(getActivity(), InterviewActivity.class);  //Here we just pass getActivity
            txt.putExtra("Value1",interview);  		//key-value pair(a.k.a Extras). Keys to identify value.
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
