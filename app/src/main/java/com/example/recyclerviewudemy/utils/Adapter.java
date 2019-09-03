package com.example.recyclerviewudemy.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.recyclerviewudemy.R;
import com.example.recyclerviewudemy.model.Article;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Article> articles;
    private View.OnClickListener listener;

    public Adapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = articles.get(position).getTitle();
        String publishedAt = articles.get(position).getPublishedAt();
        String description = articles.get(position).getDescription();
        String source = articles.get(position).getName();
        String urlToImage = articles.get(position).getUrlToImage();

        holder.textViewTitle.setText(title);
        holder.textViewPublishedAt.setText("Fecha de publicación: " + publishedAt);
        holder.textViewDescription.setText(description);
        //holder.textViewSource.setText("Fuente: " + source);
        Glide.with(context).load(urlToImage).centerCrop().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle, textViewPublishedAt, textViewDescription, textViewSource;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewPublishedAt = itemView.findViewById(R.id.textViewPublishedAt);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewSource = itemView.findViewById(R.id.textViewSource);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }


}
