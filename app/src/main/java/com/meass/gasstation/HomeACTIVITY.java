package com.meass.gasstation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.smarteist.autoimageslider.IndicatorView.draw.drawer.Drawer;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class HomeACTIVITY extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView nav_view;
    CardView card_view2;
    KProgressHUD progressHUD;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    // String  email;
    int count = 0,count1=0;
    TextView totaluser,paid,pending,blocking,notificationn,invalid,todaystask,margo_signup;
    int count11=0,countpaid,block=0;


    //
    EditText methodename,minwith,hint;
    Button logo,login_button;
    ImageView myImage;
    FirebaseStorage storage;
    StorageReference storageReference;
    Dialog mDialog;
    // EditText name,ammount;
    private HashMap<String, String> user;
    private String name, email, photo, mobile,username;
    FirebaseFirestore db;
    UserSession session;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_a_c_t_i_v_i_t_y);
        Toolbar toolbar = findViewById(R.id.toolbar);

        blocking=findViewById(R.id.blocking);
        HomeFragment fragment2 = new HomeFragment();


        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
        ft2.replace(R.id.content, fragment2, "");
        ft2.commit();



        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        drawerLayout = findViewById(R.id.drawer);
        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);
        progressHUD = KProgressHUD.create(HomeACTIVITY.this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
    }
    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(HomeACTIVITY.this);
        builder.setTitle("Exit")
                .setMessage("Are you want to exit?")
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();


                    }
                }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finishAffinity();
            }
        });
        builder.create().show();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add:
                startActivity(new Intent(getApplicationContext(),Add_NewMosque.class));
                break;

            case R.id.shareapp2:
                AlertDialog.Builder warning = new AlertDialog.Builder(HomeACTIVITY.this)
                        .setTitle("Logout")
                        .setMessage("Are you want to logout?")
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();



                            }
                        }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // ToDO: delete all the notes created by the Anon user


                                firebaseAuth.signOut();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                finish();


                            }
                        });

                warning.show();
                break;


        }

        return false;
    }
    public void logout(View view) {
        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
    }
}