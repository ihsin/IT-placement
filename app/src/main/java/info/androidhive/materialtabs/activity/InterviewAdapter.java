package info.androidhive.materialtabs.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import info.androidhive.materialtabs.R;

/**
 * Created by rahulranjansinha on 29-06-2017.
 */

public class InterviewAdapter extends RecyclerView.Adapter<InterviewAdapter.ViewHolder>{

private List<Interviews> listItems;
private Context context;


// COMPLETED (3) Create a final private ForecastAdapterOnClickHandler called mClickHandler
    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
private final InterviewsAdapterOnClickHandler mClickHandler;

// COMPLETED (1) Add an interface called ForecastAdapterOnClickHandler
// COMPLETED (2) Within that interface, define a void method that access a String as a parameter
/**
 * The interface that receives onClick messages.
 */
public interface InterviewsAdapterOnClickHandler {
    void onClick(Interviews interview);
}


    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new ForecastAdapterViewHolder that holds the View for each list item
     */



    public InterviewAdapter(List<Interviews> listItems, Context context, InterviewsAdapterOnClickHandler mClickHandler ) {
        this.listItems = listItems;
        this.context = context;
        this.mClickHandler = mClickHandler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.interviews_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Interviews  interview=listItems.get(position);
        Log.d("fffffffffffffffffffffff",Integer.toString(position));
        holder.InterviewerName.setText(Html.fromHtml(interview.getInterviewerName()));
        holder.CompanyName.setText(Html.fromHtml(interview.getCompanyName()));
        holder.Date.setText(Html.fromHtml(interview.getDate()));
        if((position)%2==0) {
            holder.Date.setBackgroundResource(R.drawable.line_divider2);
            holder.InterviewerName.setBackgroundResource(R.drawable.line_divider2);
        }
        else {
            holder.Date.setBackgroundResource(R.drawable.line_divider1);
            holder.InterviewerName.setBackgroundResource(R.drawable.line_divider1);
        }
  //      Picasso.with(context).load(interview.getURL()).resize(400,200).centerCrop().into(holder.imageViewHead);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView InterviewerName;
    public TextView CompanyName;
    public TextView Date;
    public ViewHolder(View itemView) {
        super(itemView);
        InterviewerName=(TextView)itemView.findViewById(R.id.interviewerName);
        CompanyName=(TextView)itemView.findViewById(R.id.companyName);
        Date=(TextView)itemView.findViewById(R.id.date);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int adapterPosition = getAdapterPosition();
        Interviews interviews=listItems.get(adapterPosition);
        mClickHandler.onClick(interviews);
    }
}
}
