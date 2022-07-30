package com.meass.gasstation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import es.dmoral.toasty.Toasty;

public class Add_New extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    EditText membername,fathernmae,mothername,address,mobile,id_member,
            magrib,Isha,Jumma,note,conn;
    Button cirLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__new);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Add My Gas Station");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        firebaseFirestore=FirebaseFirestore.getInstance();
        membername=findViewById(R.id.membername);
        fathernmae=findViewById(R.id.fathernmae);
        mothername=findViewById(R.id.mothername);
        address=findViewById(R.id.address);
        mobile=findViewById(R.id.mobile);
        id_member=findViewById(R.id.id_member);
        magrib=findViewById(R.id.magrib);
        Isha=findViewById(R.id.Isha);
        Jumma=findViewById(R.id.Jumma);
        note=findViewById(R.id.note);
        conn=findViewById(R.id.conn);
        cirLoginButton=findViewById(R.id.cirLoginButton);
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mosque_name=membername.getText().toString();
                String location=fathernmae.getText().toString();
                String fazar=mothername.getText().toString();
                String sunrise=address.getText().toString();
                String juhur=mobile.getText().toString();
                String asor=id_member.getText().toString();
                String magribb=magrib.getText().toString();
                String isha=Isha.getText().toString();
                String jumma=Jumma.getText().toString();
                String mootes=note.getText().toString();
                String mainconn=conn.getText().toString();
                if (TextUtils.isEmpty(mosque_name)||TextUtils.isEmpty(location)||
                        TextUtils.isEmpty(fazar)||TextUtils.isEmpty(sunrise)||TextUtils.isEmpty(juhur)||
                        TextUtils.isEmpty(asor)||TextUtils.isEmpty(magribb)||TextUtils.isEmpty(isha)||
                        TextUtils.isEmpty(mainconn)
                ) {
                    Toasty.error(getApplicationContext(),"Enter all information",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {
                    if (TextUtils.isEmpty(jumma)) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Add_New.this);
                        builder.setTitle("Confirmation")
                                .setMessage("Are you want  to add this gas station?")
                                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                final KProgressHUD progressDialog=  KProgressHUD.create(Add_New.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("Uploading Data.....")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                Long tsLong = System.currentTimeMillis()/1000;
                                String ts = tsLong.toString();
                                mosjidmodel mosjidmodel=new mosjidmodel(mosque_name,location,fazar,juhur,asor,magribb,isha,"not set","not set",sunrise,mainconn,ts);
                                firebaseFirestore.collection("Pending")
                                        .document(ts)
                                        .set(mosjidmodel)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                progressDialog.dismiss();
                                                Toasty.success(getApplicationContext(),"Request is pending.",Toasty.LENGTH_SHORT,true).show();
                                                startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));
                                            }
                                        });
                            }
                        }).create().show();

                    }
                    else
                    if (TextUtils.isEmpty(mootes)) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Add_New.this);
                        builder.setTitle("Confirmation")
                                .setMessage("Are you want  to add this gas station?")
                                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                final KProgressHUD progressDialog=  KProgressHUD.create(Add_New.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("Uploading Data.....")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                Long tsLong = System.currentTimeMillis()/1000;
                                String ts = tsLong.toString();
                                mosjidmodel mosjidmodel=new mosjidmodel(mosque_name,location,fazar,juhur,asor,magribb,isha,jumma,"not set",sunrise,mainconn,ts);
                                firebaseFirestore.collection("Pending")
                                        .document(ts)
                                        .set(mosjidmodel)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                progressDialog.dismiss();
                                                Toasty.success(getApplicationContext(),"Request is pending.",Toasty.LENGTH_SHORT,true).show();
                                                startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));
                                            }
                                        });
                            }
                        }).create().show();

                    }
                    else {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Add_New.this);
                        builder.setTitle("Confirmation")
                                .setMessage("Are you want  to add this gas station?")
                                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                final KProgressHUD progressDialog=  KProgressHUD.create(Add_New.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("Uploading Data.....")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                Long tsLong = System.currentTimeMillis()/1000;
                                String ts = tsLong.toString();
                                mosjidmodel mosjidmodel=new mosjidmodel(mosque_name,location,fazar,juhur,asor,magribb,isha,jumma,mootes,sunrise,mainconn,ts);
                                firebaseFirestore.collection("Pending")
                                        .document(ts)
                                        .set(mosjidmodel)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                progressDialog.dismiss();
                                                Toasty.success(getApplicationContext(),"Request is pending.",Toasty.LENGTH_SHORT,true).show();
                                                startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));
                                            }
                                        });
                            }
                        }).create().show();
                    }
                }
            }
        });

    }
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        return true;
    }

}