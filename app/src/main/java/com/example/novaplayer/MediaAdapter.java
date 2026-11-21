package com.example.novaplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MediaAdapter
        extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {

    private final List<Media> medias;

    public MediaAdapter(List<Media> medias) {
        this.medias = medias;
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View vue = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_media, parent, false);

        return new MediaViewHolder(vue);
    }

    @Override
    public void onBindViewHolder(
            @NonNull MediaViewHolder holder, int position) {

        Media media = medias.get(position);

        holder.titre.setText(media.getTitre());
        holder.details.setText(media.getDetail());
    }

    @Override
    public int getItemCount() {
        return medias.size();
    }

    public static class MediaViewHolder
            extends RecyclerView.ViewHolder {
        private final TextView titre;
        private final TextView details;

        public MediaViewHolder(@NonNull View itemView) {
            super(itemView);

            titre = itemView.findViewById(R.id.titre_media);
            details = itemView.findViewById(R.id.details_media);
        }
    }
}
