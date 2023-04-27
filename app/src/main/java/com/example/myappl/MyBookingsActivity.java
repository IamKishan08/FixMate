package com.example.myappl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyBookingsActivity extends AppCompatActivity {

    private List<Booking> bookings;
    private RecyclerView recyclerView;
//    private BookingAdapter adapter;
    private TextView noBookingsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

//        ListView listView = findViewById(R.id.myBookingsList);
//        TextView textView= findViewById(R.id.noBookingsText);
        // Initialize the views
        recyclerView = findViewById(R.id.myBookingsList);
        noBookingsText = findViewById(R.id.noBookingsText);

        // Get the list of bookings
        bookings = getBookings();

        // Set the adapter to the recycler view
        if (bookings.isEmpty()) {
            // If there are no bookings, show the "No bookings" text view
            noBookingsText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            // If there are bookings, set the adapter to the recycler view
            BookingAdapter BookingAdapter = new BookingAdapter(bookings);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(BookingAdapter);
        }
    }

    private List<Booking> getBookings() {
        // Code to get the list of bookings from the database
        List<Booking> bookings = new ArrayList<>();
        // Return an empty list to indicate that there are no bookings
        return bookings;
    }
}