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
import com.maximoprog.lomasgo.models.Service;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardServiceAdapter extends RecyclerView.Adapter<CardServiceAdapter.ViewHolder> {
    private Context context;
    private List<Service> serviceArrayList;
    private CardServiceAdapter.OnItemClickListener onItemClickListener;

    public CardServiceAdapter(Context context, CardServiceAdapter.OnItemClickListener listener) {
        this.context = context;
        this.serviceArrayList = new ArrayList<Service>();
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public CardServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_card, parent, false);
        return new CardServiceAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CardServiceAdapter.ViewHolder holder, int position) {
        Service servicio = serviceArrayList.get(position);
        holder.newTitleTextView.setText(servicio.getName());

        Image image = servicio.getImage();
        String urlImagen = "";
        if (image == null) {
            urlImagen = Credentials.URI_IMAGE_NOT_FOUND;
        } else {
            urlImagen = Credentials.URL_API + image.getUrl();
        }

        Picasso.with(context)
                .load(urlImagen)
                .fit().into(holder.newImageView);
        holder.bind(servicio, this.onItemClickListener);

    }

    @Override
    public int getItemCount() {
        return serviceArrayList.size();
    }

    public void addServices(List<Service> services) {
//        newArrayList.clear();
        serviceArrayList.addAll(services);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Service service, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView newImageView;
        private TextView newTitleTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newTitleTextView = itemView.findViewById(R.id.titleCardService);
            newImageView = itemView.findViewById(R.id.imageCardItemService);
        }

        public void bind(final Service service, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(service, getAdapterPosition());
                }
            });
        }
    }

}
