package com.example.myappl;

import static com.google.common.collect.ComparisonChain.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class OtpVerification extends AppCompatActivity {



    private EditText otpEt1, otpEt2, otpEt3, otpEt4;
    private TextView resendBtn;

    private boolean resendEnabled = false;

    private int resendTime =60;

    private int selectedETPostion = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        otpEt1 = findViewById(R.id.otpEt1);
        otpEt2 = findViewById(R.id.otpEt2);
        otpEt3 = findViewById(R.id.otpEt3);
        otpEt4 = findViewById(R.id.otpEt4);

        resendBtn = findViewById(R.id.resendBtn);

        final Button verifyBtn = findViewById(R.id.verifyBtn);


        final TextView otpEmail = findViewById(R.id.otpEmail);
        final TextView otpMobile = findViewById(R.id.otpMobile);


        //getting email and mobile number  from register activity
        final String getEmail = getIntent().getStringExtra("email");
        final String getMobile = getIntent().getStringExtra("mobile");

        //setting email and mobile to text view
        otpEmail.setText(getEmail);
        otpMobile.setText(getMobile);


        otpEt1.addTextChangedListener(textWatcher);
        otpEt2.addTextChangedListener(textWatcher);
        otpEt3.addTextChangedListener(textWatcher);
        otpEt4.addTextChangedListener(textWatcher);

        //default keyboard at otpET1
        showKeyboard(otpEt1);

        // start countdown timer
        startCountdownTimer();

        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resendEnabled) {



                    startCountdownTimer();
                }
            }
        });

      verifyBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              final String generateOtp = otpEt1.getText().toString() + otpEt2.getText().toString() + otpEt3.getText().toString() + otpEt4.getText().toString();

              if (generateOtp.length() == 4) {


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
           }
        }
    };


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_DEL){

            if(selectedETPostion ==3){
                selectedETPostion =2 ;
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