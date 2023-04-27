package com.example.myappl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myappl.domain.LocalService;

import java.util.Objects;

public class ServiceDetailActivity extends AppCompatActivity {

    private ImageView serviceImage;
    private TextView serviceName, serviceProviderName, serviceCost, serviceTiming, serviceDetail,serviceProviderDetails;
    private Button bookNowButton;

    private String name , provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);

        // Get the intent and the service object passed from the previous activity


        // Initialize the views
        serviceImage = findViewById(R.id.serviceImage);
        serviceName = findViewById(R.id.serviceName);
        serviceProviderName = findViewById(R.id.serviceProviderName);

        serviceDetail = findViewById(R.id.serviceDescription);
        serviceProviderDetails = findViewById(R.id.serviceProviderDetails);
        bookNowButton = findViewById(R.id.bookButton);

        Intent intent = getIntent();
        String from = intent.getStringExtra("from");
        if (Objects.equals(from, "localservice")){
            LocalService service1 = (LocalService) intent.getSerializableExtra("service");
            name = service1.getName();
            provider = service1.getProvider();
            serviceImage.setImageResource(service1.getImageResource());
            serviceName.setText(service1.getName());
            serviceProviderName.setText(service1.getProvider());
            serviceDetail.setText(service1.getServicedetail());
            serviceProviderDetails.setText(service1.getProviderdetail());
        }
        else {
            PopularService service = (PopularService) intent.getSerializableExtra("service");

            name = service.getName();
            provider = service.getProvider();

            // Set the data to the views
            serviceImage.setImageResource(service.getImage());
            serviceName.setText(service.getName());
            serviceProviderName.setText(service.getProvider());
            serviceDetail.setText(service.getServicedetail());
            serviceProviderDetails.setText(service.getProviderdetail());
        }









        // Set the click listener for the book now button
        bookNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ServiceDetailActivity.this, BookingActivity.class);
                // pass required data using intent.putExtra()
                intent.putExtra("service", name);
                intent.putExtra("provider", provider);

                startActivity(intent);
                // Handle the book now button click here
                // e.g. Show a dialog to confirm booking or navigate to the bookings page
            }
        });
    }
}
