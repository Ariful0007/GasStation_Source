package com.meass.gasstation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {
    private EditText edtemail,edtpass;
    private String email,pass,email_gating;
    private TextView appname,forgotpass;
    Button registernow;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userID;
    UserSession session;
    FirebaseAuth firebaseAuth;
    private HashMap<String, String> user;
    private String name, photo, mobile,username;
    Long tsLong = System.currentTimeMillis()/1000;
    String ts = tsLong.toString();
    TextView forgot_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.e("Login CheckPoint","LoginActivity started");
        //check Internet Connection
        session= new UserSession(getApplicationContext());
        //new CheckInternetConnection(this).checkConnection();
        firebaseAuth= FirebaseAuth.getInstance();

        FirebaseApp.initializeApp(LoginActivity.this);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        forgot_pass=findViewById(R.id.forgot_pass);

        //Typeface typeface = ResourcesCompat.getFont(this, R.font.blacklist);
        // appname = findViewById(R.id.appname);
        // appname.setTypeface(typeface);

        edtemail= findViewById(R.id.reg_name_et);
        edtpass= findViewById(R.id.reg_password_et);








        //Validating login details
        Button login_button=findViewById(R.id.main_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email_gating=edtemail.getText().toString();
                email=email_gating+"@gmail.com";
                pass=edtpass.getText().toString();



                String text1=email_gating;
                String tet2=pass;
                if (TextUtils.isEmpty(text1)||TextUtils.isEmpty(tet2)) {
                    Toasty.error(getApplicationContext(),"Give all information", Toast.LENGTH_SHORT,true).show();
                }
                else {
                    final KProgressHUD progressDialog=  KProgressHUD.create(LoginActivity.this)
                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                            .setLabel("Checking Data.....")
                            .setCancellable(false)
                            .setAnimationSpeed(2)
                            .setDimAmount(0.5f)
                            .show();
                    firebaseFirestore.collection("BlockList")
                            .document(text1.toLowerCase() +"@gmail.com")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        if (task.getResult().exists()) {
                                            progressDialog.dismiss();
                                            Toasty.error(getApplicationContext(),"You  are in blook list.", Toast.LENGTH_SHORT,true).show();
                                        }
                                        else {
                                            firebaseAuth.signInWithEmailAndPassword(text1.toLowerCase() +"@gmail.com","123456").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {

                                                    if(task.isSuccessful()){
                                                        firebaseFirestore.collection("Password")
                                                                .document(firebaseAuth.getCurrentUser().getEmail())
                                                                .get()
                                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                        if (task.isSuccessful()) {
                                                                            if (task.getResult().exists()) {
                                                                                String pass=task.getResult().getString("uuid");
                                                                                if (pass.contains(tet2.toLowerCase())) {
                                                                                    userID = firebaseAuth.getCurrentUser().getUid();
                                                                                    firebaseFirestore.collection("Users").document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                        @Override
                                                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                                                                            if (task.isSuccessful()) {

                                                                                                if (task.getResult().exists()) {


                                                                                                    String sessionname = task.getResult().getString("name");
                                                                                                    String sessionmobile = task.getResult().getString("number");
                                                                                                    String sessionphoto = task.getResult().getString("image");
                                                                                                    String sessionemail = task.getResult().getString("email");
                                                                                                    String sessionusername = task.getResult().getString("username");


                                                                                                    session.createLoginSession(sessionname,sessionemail,sessionmobile,sessionphoto,sessionusername);


                                                                                                    Toasty.success(getApplicationContext(), "Login Successfully .", Toasty.LENGTH_SHORT, true).show();

                                                                                                    Intent loginSuccess = new Intent(LoginActivity.this, HomeACTIVITY.class);

                                                                                                    startActivity(loginSuccess);
                                                                                                    finish();

                                                                                                }
                                                                                            } else {
                                                                                                progressDialog.dismiss();
                                                                                                firebaseAuth.signOut();
                                                                                                Toast.makeText(LoginActivity.this, "Login Error. Please try again.", Toast.LENGTH_SHORT).show();
                                                                                            }

                                                                                        }
                                                                                    });
                                                                                }
                                                                                else {
                                                                                    progressDialog.dismiss();
                                                                                    firebaseAuth.signOut();
                                                                                    Toasty.error(getApplicationContext(),"Password not match", Toast.LENGTH_SHORT,true).show();
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                });





                                                    } else {
                                                        firebaseAuth.signOut();
                                                        progressDialog.dismiss();
                                                        Toasty.error(LoginActivity.this,"Couldn't Log In. Please check your Email/Password",2000).show();
                                                    }
                                                }
                                            });

                                        }

                                    }
                                }
                            });
                }


//////////////////////////


            }
        });
    }




    public void register(View view) {
        startActivity(new Intent(getApplicationContext(),Register2.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void viewRegisterClicked(View view) {
        startActivity(new Intent(getApplicationContext(),Register2.class));
    }
    private void getValues() {
        //validating session


        try {
            //get User details if logged in
            session.isLoggedIn();
            user = session.getUserDetails();

            name = user.get(UserSession.KEY_NAME);
            email = user.get(UserSession.KEY_EMAIL);
            mobile = user.get(UserSession.KEY_MOBiLE);
            photo = user.get(UserSession.KEY_PHOTO);
            username=user.get(UserSession.Username);
        }catch (Exception e) {
            //get User details if logged in
            session.isLoggedIn();
            user = session.getUserDetails();

            name = user.get(UserSession.KEY_NAME);
            email = user.get(UserSession.KEY_EMAIL);
            mobile = user.get(UserSession.KEY_MOBiLE);
            photo = user.get(UserSession.KEY_PHOTO);
            username=user.get(UserSession.Username);
        }
        //Toast.makeText(this, ""+username, Toast.LENGTH_SHORT).show();
    }

    public void foegdet(View view) {
        final FlatDialog flatDialog1 = new FlatDialog(LoginActivity.this);
        flatDialog1.setTitle("Foeget Password")
                .setSubtitle("User forget his/her password.Now as a admin you change it.")
                .setFirstTextFieldHint("Phone number")
                .setSecondTextFieldHint("password")
                .setFirstButtonText("Change")
                .setSecondButtonText("Cancel")
                .withFirstButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flatDialog1.dismiss();
                        final KProgressHUD progressDialog=  KProgressHUD.create(LoginActivity.this)
                                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                .setLabel("Please wait")
                                .setCancellable(false)
                                .setAnimationSpeed(2)
                                .setDimAmount(0.5f)
                                .show();
                        firebaseFirestore.collection("Password")
                                .document(flatDialog1.getFirstTextField().toLowerCase().toString()+"@gmail.com")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful())
                                        {
                                            if (task.getResult().exists()) {
                                                firebaseFirestore.collection("Password")
                                                        .document(flatDialog1.getFirstTextField().toLowerCase().toString()+"@gmail.com")
                                                        .update("uuid",flatDialog1.getSecondTextField().toLowerCase().toString())
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    progressDialog.dismiss();

                                                                    Toasty.success(getApplicationContext(),"Password Changed", Toast.LENGTH_SHORT,true).show();
                                                                }
                                                            }
                                                        });
                                            }
                                            else {
                                                progressDialog.dismiss();

                                                Toasty.error(getApplicationContext(),"No user Found", Toast.LENGTH_SHORT,true).show();
                                            }
                                        }
                                    }
                                });

                    }
                })
                .withSecondButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flatDialog1.dismiss();
                    }
                })
                .show();

    }
}