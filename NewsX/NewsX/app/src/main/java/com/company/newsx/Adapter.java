package com.company.newsx;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.company.newsx.models.NewsAPIResponse;
import com.company.newsx.models.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import retrofit2.Response;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<NewsHeadlines> headlines;

    public Adapter(Context context, List<NewsHeadlines> headlines) {
        this.context = context;
        this.headlines = headlines;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.headlines_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(headlines.get(position).getTitle());
        holder.tvSource.setText(headlines.get(position).getSource().getName());
        if(headlines.get(position).getUrlToImage() != null){
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.ivHeadline);
        }
        holder.cv.setOnClickListener(view -> {
            Intent i = new Intent(context,NewsDetailsActivity.class);
            context.startActivity(i.putExtra("newsData", (Serializable) headlines.get(position)));
        });
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvSource;
        ImageView ivHeadline;
        CardView cv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSource = itemView.findViewById(R.id.tvSource);
            ivHeadline = itemView.findViewById(R.id.ivHeadline);
            cv = itemView.findViewById(R.id.main_container);
        }
    }
}
