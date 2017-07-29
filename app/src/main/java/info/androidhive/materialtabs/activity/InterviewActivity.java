package info.androidhive.materialtabs.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import info.androidhive.materialtabs.R;

/**
 * Created by rahulranjansinha on 29-06-2017.
 */

public class InterviewActivity extends AppCompatActivity {

    TextView companyName;
    TextView codingRound;
    TextView interviewRound;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.interview_data);
        //   Bundle extras = getIntent().getExtras();  //grabs the intent that started the activity
        Interviews interview= (Interviews) getIntent().getSerializableExtra("Value1");
        companyName=(TextView) findViewById(R.id.textView1);
        codingRound=(TextView)findViewById(R.id.textView2);
        interviewRound=(TextView)findViewById(R.id.textView3);
        if(interview!=null) {
            companyName.setText(Html.fromHtml(interview.getCompanyName()));
            //    Toast.makeText(getApplicationContext(),"Company Name: "+company.getName(),Toast.LENGTH_LONG).show();
            //      Log.e("CompanyActivity", company.getName());
            codingRound.setText(Html.fromHtml(interview.getCodingRound()));
            interviewRound.setText(Html.fromHtml(interview.getInterviewRound()));
        }
        else
            throw new NullPointerException("Comany null hai bhai");
    }
}
