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

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    private Context context;
    private List<Service> serviceArrayList;
    private ServiceAdapter.OnItemClickListener onItemClickListener;

    public ServiceAdapter(Context context, ServiceAdapter.OnItemClickListener listener) {
        this.context = context;
        this.serviceArrayList = new ArrayList<Service>();
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new ServiceAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ViewHolder holder, int position) {
        Image image = serviceArrayList.get(position).getImage();
        String urlImagen = "";
        if (image == null) {
            urlImagen = Credentials.URI_IMAGE_NOT_FOUND;
        } else {
            urlImagen = Credentials.URL_API + image.getUrl();
        }

        Picasso.with(context)
                .load(urlImagen)
                .fit().into(holder.newImageView);
        holder.bind(serviceArrayList.get(position), this.onItemClickListener);
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

    //    implementacion sencilla

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView newImageView;
        private TextView newTitleTextView;
        private TextView newDescriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newImageView = (ImageView) itemView.findViewById(R.id.serviceItemImageView);
            newTitleTextView = (TextView) itemView.findViewById(R.id.serviceTitleItemTextView);
            newDescriptionTextView = (TextView) itemView.findViewById(R.id.serviceDescriptionItemTextView);

        }

        public void bind(final Service service, final ServiceAdapter.OnItemClickListener listener) {

            this.newTitleTextView.setText(service.getName());
            this.newDescriptionTextView.setText(service.getDescription());

//            itemView instancia de la vista entera
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(service, getAdapterPosition());
                }
            });
        }
    }
}
