package com.example.myappl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private  int selectedtab =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Button logoutBtn = findViewById(R.id.button);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

    private void logout(){
        mAuth.signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

}

//SHA1: 3B:D8:DA:F3:13:D9:09:F5:58:27:98:67:09:9D:F9:DC:B0:75:CA:75
//SHA-256: 2D:57:A4:8E:F2:35:55:3A:50:4C:1F:17:F2:F1:2D:42:17:FB:0D:3D:57:6E:FB:C9:D0:8A:41:D7:4C:70:89:2F