package info.androidhive.materialtabs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import info.androidhive.materialtabs.R;

/**
 * Created by rahulranjansinha on 27-06-2017.
 */

public class CompanyActivity extends AppCompatActivity{
        /** Called when the activity is first created. */

        ImageView companyImage;
        TextView name;
        TextView about;
        TextView portfolio;
 //       TextView interviews;

        @Override
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setContentView(R.layout.company_data);
         //   Bundle extras = getIntent().getExtras();  //grabs the intent that started the activity
            Company company= (Company) getIntent().getSerializableExtra("Value1");
            name=(TextView) findViewById(R.id.textView1);
            companyImage=(ImageView) findViewById(R.id.logo);
            about=(TextView)findViewById(R.id.textView2);
            portfolio=(TextView)findViewById(R.id.textView3);
      //      interviews=(TextView)findViewById(R.id.textView4);
            if(company!=null) {
                name.setText(company.getName());
                //    Toast.makeText(getApplicationContext(),"Company Name: "+company.getName(),Toast.LENGTH_LONG).show();
                //      Log.e("CompanyActivity", company.getName());
                Picasso.with(this).load(company.getURL()).resize(400, 200).centerCrop().into(companyImage);
                about.setText(company.getAbout());
                portfolio.setText(Html.fromHtml(company.getPortfolio()));
     //           interviews.setText(Html.fromHtml(company.getInterviews()));

            }
            else
                throw new NullPointerException("Comany null hai bhai");
        }
}
