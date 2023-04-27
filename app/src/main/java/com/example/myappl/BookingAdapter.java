package com.example.myappl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
    private List<Booking> bookings;

    public BookingAdapter(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookings.get(position);
        //holder.serviceName.setText(booking.getServiceName());
       // holder.serviceProviderName.setText(booking.getServiceProviderName());
      //  holder.serviceTime.setText(booking.getServiceTime());
        //holder.serviceDate.setText(booking.getServiceDate());
       // holder.serviceAddress.setText(booking.getServiceAddress());
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        private TextView serviceName;
        private TextView serviceProviderName;
        private TextView serviceTime;
        private TextView serviceDate;
        private TextView serviceAddress;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.bookingServiceName);
            serviceProviderName = itemView.findViewById(R.id.bookingServiceProviderName);
          //  serviceTime = itemView.findViewById(R.id.bookingServiceTime);
            //serviceDate = itemView.findViewById(R.id.bookingServiceDate);
            serviceAddress = itemView.findViewById(R.id.bookingServiceCost);
        }
    }
}

