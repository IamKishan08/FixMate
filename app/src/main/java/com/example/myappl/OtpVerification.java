package com.example.myappl;

import static com.google.common.collect.ComparisonChain.start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

public class OtpVerification extends AppCompatActivity {



    private EditText otpEt1, otpEt2, otpEt3, otpEt4, otpEt5, otpEt6;
    private TextView resendBtn;

    private boolean resendEnabled = false;

    private int resendTime =60;

    private int selectedETPostion = 0;

    private FirebaseAuth firebaseAuth;

    private FirebaseFirestore firestore;

    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        otpEt1 = findViewById(R.id.otpEt1);
        otpEt2 = findViewById(R.id.otpEt2);
        otpEt3 = findViewById(R.id.otpEt3);
        otpEt4 = findViewById(R.id.otpEt4);
        otpEt5 = findViewById(R.id.otpEt5);
        otpEt6 = findViewById(R.id.otpEt6);

        resendBtn = findViewById(R.id.resendBtn);

        final Button verifyBtn = findViewById(R.id.verifyBtn);


        final TextView otpEmail = findViewById(R.id.otpEmail);
        final TextView otpMobile = findViewById(R.id.otpMobile);

        Intent i = getIntent();

        //getting email and mobile number  from register activity
        final String getEmail = i.getStringExtra("email");
        final String getMobile = i.getStringExtra("mobile");
        final String getName = i.getStringExtra("name");
        final String getPassword = i.getStringExtra("password");

        //setting email and mobile to text view
        otpEmail.setText(getEmail);
        otpMobile.setText(getMobile);


        otpEt1.addTextChangedListener(textWatcher);
        otpEt2.addTextChangedListener(textWatcher);
        otpEt3.addTextChangedListener(textWatcher);
        otpEt4.addTextChangedListener(textWatcher);
        otpEt5.addTextChangedListener(textWatcher);
        otpEt6.addTextChangedListener(textWatcher);

        //default keyboard at otpET1
        showKeyboard(otpEt1);

        // start countdown timer
        startCountdownTimer();

        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resendEnabled) {
                    sendVerificationCode(getMobile);


                    startCountdownTimer();
                }
            }
        });

      verifyBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              final String generateOtp = otpEt1.getText().toString() + otpEt2.getText().toString() + otpEt3.getText().toString() + otpEt4.getText().toString() + otpEt5.getText().toString() + otpEt6.getText().toString();

              if (generateOtp.length() == 6) {

                    verifyCode(generateOtp);
              }
          }
      });

      sendVerificationCode(getMobile);

    }

    private void sendVerificationCode(String mobile) {
        String countryCode = "+91";
        mobile = countryCode + mobile;

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(mobile)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String otp = phoneAuthCredential.getSmsCode();
            if(otp != null){
                //TODO: Split otp into array and add each elements into separate input fields
                verifyCode(otp);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OtpVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void verifyCode(String otp) {
        PhoneAuthCredential authCredential = PhoneAuthProvider.getCredential(verificationId, otp);
        signInWithProvider(authCredential);
    }

    private void signInWithProvider(PhoneAuthCredential credential){
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

//                            String uid = firebaseAuth.getCurrentUser().getUid();
//
//                            DocumentReference documentReference = firestore.collection("Users").document(uid);
//                            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                    if(task.isSuccessful()){
//                                        DocumentSnapshot doc = task.getResult();
//                                        if(!doc.exists()){
//                                        }
//                                    }
//                                }
//                            });
                            Intent i = new Intent(OtpVerification.this, MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        }else{

                        }
                    }
                });
    }

    private void showKeyboard(EditText otpET){
        otpET.requestFocus();

        InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(otpET,InputMethodManager.SHOW_IMPLICIT);
    }




    private  void startCountdownTimer(){
        resendEnabled =false;
        resendBtn.setTextColor(Color.parseColor("#99000000"));

        new CountDownTimer(resendTime *1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

                resendBtn.setText("Resend Code (" +(millisUntilFinished /1000)+")");


            }

            @Override
            public void onFinish() {

                resendEnabled = true;
                resendBtn.setTextColor(getResources().getColor(R.color.primary));

            }
        }.start();
    }





    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {
           if(s.length() > 0){
               if(selectedETPostion ==0){
                   selectedETPostion = 1;
                   showKeyboard(otpEt2);
               }
               else if(selectedETPostion==1){
                   selectedETPostion = 2;
                   showKeyboard(otpEt3);

               }


               else if(selectedETPostion==2){
                   selectedETPostion =3;
                   showKeyboard(otpEt4);

               }
               else if(selectedETPostion==3) {
                   selectedETPostion = 4;
                   showKeyboard(otpEt5);
               }

               else if(selectedETPostion==4){
                       selectedETPostion =5;
                       showKeyboard(otpEt6);

               }
           }
        }
    };


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_DEL){

            if(selectedETPostion ==5){
                selectedETPostion =4 ;
                showKeyboard(otpEt5);
            }

            else if (selectedETPostion ==4 ) {
                selectedETPostion =3;
                showKeyboard(otpEt4);
            }

            else if (selectedETPostion ==3 ) {
                selectedETPostion =2;
                showKeyboard(otpEt3);
            }

            else if (selectedETPostion ==2 ) {
                selectedETPostion =1;
                showKeyboard(otpEt2);
            }

            else if (selectedETPostion ==1) {
                selectedETPostion =0;
                showKeyboard(otpEt1);

            }
            return true;
        }
        else {
            return super.onKeyUp(keyCode, event);
        }

    }
}