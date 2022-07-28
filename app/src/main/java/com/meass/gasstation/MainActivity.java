package com.meass.gasstation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private TextView appname;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    UserSession session;
    private FirebaseUser user;
    private String userID;
    int totall;
    int main_total;
    String ts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session =new UserSession(MainActivity.this);
        MultiDex.install(this);
        // new CheckInternetConnection(this).checkConnection();
        Long tsLong = System.currentTimeMillis()/1000;
        ts = tsLong.toString();
        appname = findViewById(R.id.appname);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore=FirebaseFirestore.getInstance();
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseFirestore = FirebaseFirestore.getInstance();






        //Typeface typeface = ResourcesCompat.getFont(this, R.font.blacklist);
        // appname.setTypeface(typeface);

        YoYo.with(Techniques.Bounce)
                .duration(7000)
                .playOn(findViewById(R.id.logo));


        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {


                if (firebaseUser != null) {


                    startActivity(new Intent(getApplicationContext(), HomeACTIVITY.class));
                    finish();


                } else {
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();



                }



            }
        }, 1000);



    }





}