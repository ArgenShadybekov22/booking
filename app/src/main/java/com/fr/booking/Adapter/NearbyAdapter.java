package com.fr.booking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fr.booking.Activity.DetailActivity;
import com.fr.booking.Domain.PropertyDomain;
import com.fr.booking.databinding.NearbyViewholderBinding;
import com.fr.booking.databinding.RecommendedViewholderBinding;

import java.util.ArrayList;

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.Viewholder> {
    ArrayList<PropertyDomain> items;
    Context context;
    NearbyViewholderBinding binding;

    public NearbyAdapter(ArrayList<PropertyDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public NearbyAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding=NearbyViewholderBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        context= parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NearbyAdapter.Viewholder holder, int position) {
        binding.titleTxt.setText(items.get(position).getTitle());
        binding.priceTxt.setText("$"+items.get(position).getPrice());
        binding.typeTxt.setText(items.get(position).getType());
        binding.addressTxt.setText(items.get(position).getAddress());
        binding.scoreTxt.setText(""+items.get(position).getScore());

        int drawableResourceId=holder.itemView.getResources()
                .getIdentifier(items.get(position).getPicPath(), "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(binding.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(context, DetailActivity.class);
            intent.putExtra("object", items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        public Viewholder(NearbyViewholderBinding binding) {
            super(binding.getRoot());
        }
    }
}
