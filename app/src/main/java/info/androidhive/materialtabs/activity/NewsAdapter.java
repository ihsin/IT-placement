package info.androidhive.materialtabs.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import info.androidhive.materialtabs.R;

/**
 * Created by rahulranjansinha on 30-06-2017.
 */

public class NewsAdapter extends  RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private List<News> listItems;
    private Context context;


    // COMPLETED (3) Create a final private ForecastAdapterOnClickHandler called mClickHandler
    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
    private final NewsAdapter.NewsAdapterOnClickHandler mClickHandler;

    // COMPLETED (1) Add an interface called ForecastAdapterOnClickHandler
    // COMPLETED (2) Within that interface, define a void method that access a String as a parameter
    /**
     * The interface that receives onClick messages.
     */
    public interface NewsAdapterOnClickHandler {
        void onClick(News news);
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



    public NewsAdapter(List<News> listItems, Context context,NewsAdapterOnClickHandler mClickHandler ) {
        this.listItems = listItems;
        this.context = context;
        this.mClickHandler = mClickHandler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News  news=listItems.get(position);
        holder.textViewHead.setText(Html.fromHtml(news.getNews()));/////////////////////////////////////////////////
        Picasso.with(context).load(news.getImageURL()).resize(400,200).centerCrop().into(holder.imageViewHead);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textViewHead;
        public ImageView imageViewHead;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewHead=(TextView)itemView.findViewById(R.id.news);
            imageViewHead=(ImageView)itemView.findViewById(R.id.newsImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            News news=listItems.get(adapterPosition);
            mClickHandler.onClick(news);
        }
    }
}
