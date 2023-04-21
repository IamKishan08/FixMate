package com.example.myappl.domain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myappl.R;

import java.util.List;

public class LocalServicesAdapter extends RecyclerView.Adapter<LocalServicesAdapter.LocalServiceViewHolder> {

    private List<LocalService> localServices;

    public LocalServicesAdapter(List<LocalService> localServices) {
        this.localServices = localServices;
    }

    @NonNull
    @Override
    public LocalServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_local_service, parent, false);
        return new LocalServiceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalServiceViewHolder holder, int position) {
        LocalService localService = localServices.get(position);
        holder.localServiceName.setText(localService.getName());
        holder.localServiceDescription.setText(localService.getDescription());
        holder.localServiceCost.setText(localService.getCost());
        holder.localServiceTiming.setText(localService.getTiming());
        holder.localServiceImage.setImageResource(localService.getImageResource());
    }

    @Override
    public int getItemCount() {
        return localServices.size();
    }

    public static class LocalServiceViewHolder extends RecyclerView.ViewHolder {
        TextView localServiceName;
        TextView localServiceDescription;
        TextView localServiceCost;
        TextView localServiceTiming;
        ImageView localServiceImage;

        public LocalServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            localServiceName = itemView.findViewById(R.id.local_service_name);
            localServiceDescription = itemView.findViewById(R.id.local_service_description);
            localServiceCost = itemView.findViewById(R.id.local_service_cost);
            localServiceTiming = itemView.findViewById(R.id.local_service_timing);
            localServiceImage = itemView.findViewById(R.id.local_service_image);
        }
    }
}



