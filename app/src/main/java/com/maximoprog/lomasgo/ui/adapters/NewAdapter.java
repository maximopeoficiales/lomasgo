package com.maximoprog.lomasgo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maximoprog.lomasgo.R;
import com.maximoprog.lomasgo.enviroments.Credentials;
import com.maximoprog.lomasgo.models.Image;
import com.maximoprog.lomasgo.models.New;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {
    private Context context;
    private List<New> newArrayList;
    private OnItemClickListener onItemClickListener;

    public NewAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.newArrayList = new ArrayList<New>();
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Image imageNoticia = newArrayList.get(position).getImage();
        String urlImagen = "";
        if (imageNoticia == null) {
            urlImagen = "https://as2.ftcdn.net/jpg/01/19/98/29/500_F_119982932_Cv6iHvH4GF7qiTdSDrF37Q0hHIpVbOrd.jpg";
        } else {
            urlImagen = Credentials.URL_API + imageNoticia.getUrl();
        }

        Picasso.with(context)
                .load(urlImagen)
                .fit().into(holder.newImageView);
        holder.bind(newArrayList.get(position), this.onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return newArrayList.size();
    }

    public void addNews(List<New> news) {
//        newArrayList.clear();
        newArrayList.addAll(news);
        notifyDataSetChanged();
    }

    //    implementacion sencilla
    public interface OnItemClickListener {
        void onItemClick(New noticia, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView newImageView;
        private TextView newTitleTextView;
        private TextView newSummaryTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newImageView = (ImageView) itemView.findViewById(R.id.newItemImageView);
            newTitleTextView = (TextView) itemView.findViewById(R.id.newTitleItemTextView);
            newSummaryTextView = (TextView) itemView.findViewById(R.id.newSummaryItemTextView);

        }

        public void bind(final New noticia, final OnItemClickListener listener) {

            this.newTitleTextView.setText(noticia.getTitle());
            this.newSummaryTextView.setText(noticia.getSummary());

//            itemView instancia de la vista entera
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(noticia, getAdapterPosition());
                }
            });
        }

    }
}
