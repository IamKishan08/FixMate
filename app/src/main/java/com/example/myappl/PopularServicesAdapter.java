package com.example.myappl;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PopularServicesAdapter extends RecyclerView.Adapter<PopularServicesAdapter.viewHolder> {
    private List<PopularService> services;

    public PopularServicesAdapter(List<PopularService> services) {
        this.services = services;
    }
    @NonNull
    @Override
    public PopularServicesAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PopularService service = services.get(position);
        holder.serviceImageView.setImageResource(service.getImage());
        holder.serviceNameTextView.setText(service.getName());
        holder.serviceProviderTextView.setText(service.getProvider());






        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ServiceDetailActivity.class);
                intent.putExtra("service", service);
                intent.putExtra("from","popularservice");
                view.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return services.size();
    }

    public  static class viewHolder extends RecyclerView.ViewHolder{
        ImageView serviceImageView;
        TextView serviceNameTextView;
        TextView serviceProviderTextView;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            serviceImageView = itemView.findViewById(R.id.serviceImageView);
            serviceNameTextView = itemView.findViewById(R.id.serviceNameTextView);
            serviceProviderTextView = itemView.findViewById(R.id.serviceProviderTextView);

        }
    }

}
