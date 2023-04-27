package com.example.myappl;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookingActivity extends AppCompatActivity {


    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        //Database storing
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("booking");



        // Get references to the UI elements

        EditText problemDescriptionTextView = findViewById(R.id.problemDescriptionTextView);
        EditText buildingNumberEditText = findViewById(R.id.buildingNumberEditText);
        EditText streetAreaEditText = findViewById(R.id.streetAreaEditText);
        EditText localityEditText = findViewById(R.id.localityEditText);
        EditText stateEditText = findViewById(R.id.stateEditText);
        EditText countryEditText = findViewById(R.id.countryEditText);
        EditText pincodeEditText = findViewById(R.id.pincodeEditText);
        EditText phoneEditText = findViewById(R.id.phoneEditText);

        Button submitButton = findViewById(R.id.bookNowButton);




        RadioButton radioButton1 = findViewById(R.id.radioButton1);
        RadioButton radioButton2 = findViewById(R.id.radioButton2);
        RadioButton radioButton3 = findViewById(R.id.radioButton3);
        RadioButton radioButton4 = findViewById(R.id.radioButton4);

        // Get reference to the RadioGroup
        RadioGroup timeSlotRadioGroup = findViewById(R.id.timeSlotRadioGroup);

        // Get the ID of the selected RadioButton from the RadioGroup
        int selectedRadioButtonId = timeSlotRadioGroup.getCheckedRadioButtonId();


        //radio button
        timeSlotRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Get the selected RadioButton
                RadioButton selectedRadioButton = findViewById(checkedId);

                // Get the text of the selected RadioButton
                String selectedTimeSlot = selectedRadioButton.getText().toString();
                if (selectedRadioButtonId != -1) {
                    selectedRadioButton = findViewById(selectedRadioButtonId);
                    selectedTimeSlot = selectedRadioButton.getText().toString();
                }

                // Do something with the selected time slot
                // For example, you could store it in a variable or display it in a TextView
            }
        });


        // Initialize the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("bookings");


        // Set a click listener for the submit button
        submitButton.setOnClickListener(view -> {
            // Get the user input
            String problemdescription = problemDescriptionTextView.getText().toString().trim();
            String buildingnumber = buildingNumberEditText.getText().toString().trim();
            String streetarea = streetAreaEditText.getText().toString().trim();
            String loacality = localityEditText.getText().toString().trim();
            String state = stateEditText.getText().toString().trim();
            String country = countryEditText.getText().toString().trim();
            String pincode = pincodeEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
           // String service = serviceEditText.getText().toString().trim();

            // Validate the input
            if (problemdescription.isEmpty()) {
                problemDescriptionTextView.setError("Problem Description is required");
                problemDescriptionTextView.requestFocus();
                return;
            }

            if (buildingnumber.isEmpty()) {
                buildingNumberEditText.setError("Building number is required");
                buildingNumberEditText.requestFocus();
                return;
            }

            if (streetarea.isEmpty()) {
                streetAreaEditText.setError("Street is required");
                streetAreaEditText.requestFocus();
                return;
            }

            if (loacality.isEmpty()) {
                localityEditText.setError("Address is required");
                localityEditText.requestFocus();
                return;
            }
            if (state.isEmpty()) {
                stateEditText.setError("Address is required");
                stateEditText.requestFocus();
                return;
            }
            if (country.isEmpty()) {
                countryEditText.setError("Address is required");
                countryEditText.requestFocus();
                return;
            }
            if (pincode.isEmpty()) {
                pincodeEditText.setError("Address is required");
                pincodeEditText.requestFocus();
                return;
            }

            if (phone.isEmpty()) {
                phoneEditText.setError("Phone number is required");
                phoneEditText.requestFocus();
                return;
            }



            // Create a new booking object
            Booking booking = new Booking(problemdescription, buildingnumber,streetarea,loacality,state,country,pincode, phone);

            // Push the booking to the database
            databaseReference.push().setValue(booking).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        // Show a success message
                        Toast.makeText(BookingActivity.this, "Booking submitted successfully", Toast.LENGTH_SHORT).show();

                        // Start a new activity to show the confirmation message
//                        startActivity(new Intent(BookingActivity.this, BookingActivity.class));
                        Intent intent =new Intent(BookingActivity.this,ConfirmationActivity.class);
                        new Intent(getApplicationContext(), ConfirmationActivity.class);
                        startActivity(intent);
                        //  Intent(BookingActivity.this, ConfirmationActivity.class);
                        finish();
                    } else {
                        // Show an error message
                        Toast.makeText(BookingActivity.this, "Failed to submit booking", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        });
    }
}



