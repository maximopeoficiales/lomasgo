package com.maximoprog.lomasgo.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maximoprog.lomasgo.NoticiaDetalleActivity;
import com.maximoprog.lomasgo.R;
import com.maximoprog.lomasgo.enviroments.Credentials;
import com.maximoprog.lomasgo.models.Image;
import com.maximoprog.lomasgo.models.New;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderNoticiaAdapter extends SliderViewAdapter<SliderNoticiaAdapter.Holder> {
    Context context;
    List<New> noticias;

    public SliderNoticiaAdapter(Context context) {
        this.context = context;
        this.noticias = new ArrayList<New>();


    }

    public void addNews(List<New> news) {
        noticias.addAll(news);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_noticia_item, parent, false);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        New noticia = noticias.get(position);
        Image imageNoticia = noticia.getImage();
        String urlImagen = "";
        if (imageNoticia == null) {
            urlImagen = Credentials.URI_IMAGE_NOT_FOUND;
        } else {
            urlImagen = Credentials.URL_API + imageNoticia.getUrl();
        }
        Picasso.with(context)
                .load(urlImagen)
                .fit().into(viewHolder.imageViewNoticia);
        viewHolder.imageTitleNoticia.setText(noticia.getTitle());
        viewHolder.imageViewNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        context, NoticiaDetalleActivity.class
                );
//                le pasa el item osea el objecto como parametro
                intent.putExtra("new", noticia);
//                inicia la actividad
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getCount() {
        return noticias.size();
    }

    public static class Holder extends SliderViewAdapter.ViewHolder {
        private ImageView imageViewNoticia;
        private TextView imageTitleNoticia;

        public Holder(View itemView) {
            super(itemView);
            imageViewNoticia = itemView.findViewById(R.id.imageViewNoticia);
            imageTitleNoticia = itemView.findViewById(R.id.imageTitleNoticia);
        }


    }

}
