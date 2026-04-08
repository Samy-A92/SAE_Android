package com.example.sae;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<String> noms;
    private ArrayList<String> salles;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public MyAdapter(ArrayList<String> noms, ArrayList<String> salles, OnItemClickListener listener) {
        this.noms = noms;
        this.salles = salles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNom.setText(noms.get(position));
        holder.tvSalle.setText(salles.get(position));

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNom, tvSalle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNom = itemView.findViewById(R.id.textViewNom);
            tvSalle = itemView.findViewById(R.id.textViewSalle);
        }
    }
}