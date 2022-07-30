package com.meass.gasstation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class Register2 extends AppCompatActivity {

    private EditText edtname, edtemail, edtpass, edtcnfpass, edtnumber,username,editTextPassword111;
    private String check, name, email, password, mobile, profile;
    CircleImageView image;
    private Spinner spinnerTextSize,spinnerTextSize1,spinnerTextSize2,package233;
    ImageView upload;
    String valueFromSpinner;
    String getValueFromSpinner10;
    String getCCC;
    String valueFromSpinner1;
    String valueFromSpinner2;
    String getValueFromSpinner;
    Spinner package2;
    private Uri mainImageURI = null;
    boolean IMAGE_STATUS = false;
    Bitmap profilePicture;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private final String TAG = this.getClass().getSimpleName();
    private String userId;

    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;

    private Bitmap compressedImageFile;
    private KProgressHUD progressDialog;
    ImageButton image_button;
    ImageView imageView;
    private final int PICK_IMAGE_REQUEST = 71;
    private Uri filePath;//Firebase

    DocumentReference documentReference;
    Button floatingActionButton;
    FirebaseStorage storage;
    private static final int CAMERA_REQUEST = 1888;
    Button generate_btn;
    //doctor
    private static final int READCODE = 1;
    private static final int WRITECODE = 2;

    private Uri mainImageUri = null;
    FirebaseAuth firebaseAuth;
    UserSession session;
    int package_balance;
    String value2;
    String group;

    Long tsLong = System.currentTimeMillis()/1000;
    String ts = tsLong.toString();
    String big_id;
    String big_Team_Counter;
    CheckBox checkle;
    EditText whatsapp,location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        session =new UserSession(Register2.this);
        firebaseAuth = FirebaseAuth.getInstance();
        checkle=findViewById(R.id.checkle);
        progressDialog=KProgressHUD.create(Register2.this);
        editTextPassword111=findViewById(R.id.editTextPassword111);

        FirebaseApp.initializeApp(Register2.this);
        //username=findViewById(R.id.editTextEmail);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        whatsapp=findViewById(R.id.whatsapp);
        location=findViewById(R.id.location);
        edtemail=findViewById(R.id.email);




        edtname = findViewById(R.id.fucllname);
        // edtemail = findViewById(R.id.email);
        edtpass = findViewById(R.id.editTextPassword);
        edtcnfpass = findViewById(R.id.editTextPassword1);
        edtnumber = findViewById(R.id.mobile);
        username=findViewById(R.id.username);
        Random r = new Random();
        int randomNumber = r.nextInt(99999);

        edtname.addTextChangedListener(nameWatcher);
        username.addTextChangedListener(nameWatcher1);
        //edtemail.addTextChangedListener(emailWatcher);
        edtpass.addTextChangedListener(passWatcher);
        edtcnfpass.addTextChangedListener(cnfpassWatcher);
        editTextPassword111.setText("123445");
        edtnumber.addTextChangedListener(numberWatcher);
        TextView login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
        Button cirLoginButton=findViewById(R.id.loginnn);
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtemail.getText().toString())||TextUtils.isEmpty(whatsapp.getText().toString())||TextUtils.isEmpty(location.getText().toString())||TextUtils.isEmpty(editTextPassword111.getText().toString())||TextUtils.isEmpty(edtname.getText().toString())
                        || TextUtils.isEmpty(edtpass.getText().toString()) || TextUtils.isEmpty(edtcnfpass.getText().toString())
                        || TextUtils.isEmpty(edtnumber.getText().toString())||TextUtils.isEmpty(username.getText().toString())) {
                    Toasty.error(getApplicationContext(), "Error give right information.", Toast.LENGTH_SHORT, true).show();
                    return;
                }
                else {
                    if (!checkle.isChecked()) {
                        AlertDialog.Builder warning=new AlertDialog.Builder(Register2.this);
                        warning.setTitle("Conformation")
                                .setMessage("Are you want to register in our apps")
                                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                progress_check();
                                firebaseFirestore.collection("BlockList")
                                        .document(edtnumber.getText().toString().toLowerCase() + "@gmail.com")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    if (task.getResult().exists()) {
                                                        progressDialog.dismiss();
                                                        Toasty.error(getApplicationContext(), "You  are in blook list.", Toast.LENGTH_SHORT, true).show();
                                                    }
                                                    else {
                                                        firebaseFirestore.collection("Name_Exsiting")
                                                                .document(edtnumber.getText().toString().toLowerCase())
                                                                .get()
                                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                        if (task.isSuccessful()) {
                                                                            if (task.getResult().exists()) {
                                                                                progressDialog.dismiss();
                                                                                Toasty.error(getApplicationContext(), "This username already taken..", Toast.LENGTH_SHORT, true).show();
                                                                                return;
                                                                            }
                                                                            else {
                                                                                final EditText input = new EditText(Register2.this);
                                                                                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                                                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                                                                        LinearLayout.LayoutParams.MATCH_PARENT);
                                                                                input.setLayoutParams(lp);
                                                                                input.setText("amia");
                                                                                input.setHint("Enter Refferal Name");
                                                                                firebaseFirestore.collection("Old_User")
                                                                                        .document(input.getText().toString().toLowerCase())
                                                                                        .get()
                                                                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                                if (task.isSuccessful()) {
                                                                                                    if (task.getResult().exists()) {

                                                                                                        //Toast.makeText(RegistrationActivity.this, "make", Toast.LENGTH_SHORT).show();
                                                                                                        mAuth.createUserWithEmailAndPassword(edtnumber.getText().toString().toLowerCase() + "@gmail.com", "123456")
                                                                                                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                                                                    @Override
                                                                                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                                                                                        if (task.isSuccessful()) {
                                                                                                                            final Map<String, String> userMap19 = new HashMap<>();
                                                                                                                            userMap19.put("refername", input.getText().toString().toLowerCase());
                                                                                                                            userMap19.put("refername_email", input.getText().toString().toLowerCase() + "@gmail.com");
                                                                                                                            userMap19.put("user_id", firebaseAuth.getCurrentUser().getUid());
                                                                                                                            userMap19.put("user_name", username.getText().toString().toLowerCase());
                                                                                                                            firebaseFirestore.collection("ALL_GET")
                                                                                                                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                                                                                                                    .set(userMap19)
                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                        @Override
                                                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                            if (task.isSuccessful()) {
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    });

                                                                                                                            Name_Exsiting name_exsiting = new Name_Exsiting(edtnumber.getText().toString().toLowerCase(),
                                                                                                                                    edtnumber.getText().toString());
                                                                                                                            firebaseFirestore.collection("Name_Exsiting")
                                                                                                                                    .document(edtnumber.getText().toString().toLowerCase())
                                                                                                                                    .set(name_exsiting)
                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                        @Override
                                                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                            if (task.isSuccessful()) {
                                                                                                                                                // old_user(username.getText().toString().toLowerCase());
                                                                                                                                                // sendStatus(mAuth.getCurrentUser().getEmail());
                                                                                                                                                // cris(mAuth.getCurrentUser().getEmail());
                                                                                                                                                //    month_counting(mAuth.getCurrentUser().getEmail(),mAuth.getCurrentUser().getUid());
                                                                                                                                                go_toCount(mAuth.getCurrentUser().getEmail(),mAuth.getCurrentUser().getUid());
                                                                                                                                                name=edtname.getText().toString();
                                                                                                                                                //email=edtemail.getText().toString();
                                                                                                                                                //  todays_free(input.getText().toString().toLowerCase() +"@gmail.com");
                                                                                                                                                password=edtcnfpass.getText().toString();
                                                                                                                                                mobile=edtnumber.getText().toString();
                                                                                                                                                Long tsLong = System.currentTimeMillis()/1000;
                                                                                                                                                String ts = tsLong.toString();

                                                                                                                                                String image22="https://firebasestorage.googleapis.com/v0/b/cash-money-express-ltd.appspot.com/o/profile_images%2Fo8Dnqf5LFodKSwocGQ4nKB7ZEkW2.jpg?alt=media&token=c22700e2-67ca-4497-8bf1-204ac83b6749";
                                                                                                                                                storeToFirestore1(name, "abc@gmail.com",image22, email);

                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    });

                                                                                                                        }
                                                                                                                    }
                                                                                                                });
                                                                                                    }
                                                                                                    else {
                                                                                                        progressDialog.dismiss();
                                                                                                        Toasty.error(getApplicationContext(), "No User Found .", Toast.LENGTH_SHORT, true).show();
                                                                                                        return;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        });


                                                                            }
                                                                        }
                                                                    }
                                                                });
                                                    }
                                                }
                                            }
                                        });
                            }
                        }).create().show();
                    }
                    else {
                        AlertDialog.Builder warning=new AlertDialog.Builder(Register2.this);
                        warning.setTitle("Conformation")
                                .setMessage("Are you want to register in our apps")
                                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                progress_check();
                                firebaseFirestore.collection("BlockList")
                                        .document(username.getText().toString().toLowerCase() + "@gmail.com")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    if (task.getResult().exists()) {
                                                        progressDialog.dismiss();
                                                        Toasty.error(getApplicationContext(), "You  are in blook list.", Toast.LENGTH_SHORT, true).show();
                                                    }
                                                    else {
                                                        firebaseFirestore.collection("Name_Exsiting")
                                                                .document(username.getText().toString().toLowerCase())
                                                                .get()
                                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                        if (task.isSuccessful()) {
                                                                            if (task.getResult().exists()) {
                                                                                progressDialog.dismiss();
                                                                                Toasty.error(getApplicationContext(), "This username already taken..", Toast.LENGTH_SHORT, true).show();
                                                                                return;
                                                                            }
                                                                            else {
                                                                                final EditText input = new EditText(Register2.this);
                                                                                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                                                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                                                                        LinearLayout.LayoutParams.MATCH_PARENT);
                                                                                input.setLayoutParams(lp);
                                                                                input.setText("amia");
                                                                                input.setHint("Enter Refferal Name");
                                                                                firebaseFirestore.collection("Old_User")
                                                                                        .document(input.getText().toString().toLowerCase())
                                                                                        .get()
                                                                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                                if (task.isSuccessful()) {
                                                                                                    if (task.getResult().exists()) {

                                                                                                        //Toast.makeText(RegistrationActivity.this, "make", Toast.LENGTH_SHORT).show();
                                                                                                        mAuth.createUserWithEmailAndPassword(username.getText().toString().toLowerCase() + "@gmail.com", "123456")
                                                                                                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                                                                    @Override
                                                                                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                                                                                        if (task.isSuccessful()) {
                                                                                                                            final Map<String, String> userMap19 = new HashMap<>();
                                                                                                                            userMap19.put("refername", input.getText().toString().toLowerCase());
                                                                                                                            userMap19.put("refername_email", input.getText().toString().toLowerCase() + "@gmail.com");
                                                                                                                            userMap19.put("user_id", firebaseAuth.getCurrentUser().getUid());
                                                                                                                            userMap19.put("user_name", username.getText().toString().toLowerCase());
                                                                                                                            firebaseFirestore.collection("ALL_GET")
                                                                                                                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                                                                                                                    .set(userMap19)
                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                        @Override
                                                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                            if (task.isSuccessful()) {
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    });

                                                                                                                            Name_Exsiting name_exsiting = new Name_Exsiting(username.getText().toString().toLowerCase(),
                                                                                                                                    username.getText().toString());
                                                                                                                            firebaseFirestore.collection("Name_Exsiting")
                                                                                                                                    .document(username.getText().toString().toLowerCase())
                                                                                                                                    .set(name_exsiting)
                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                        @Override
                                                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                            if (task.isSuccessful()) {
                                                                                                                                                // old_user(username.getText().toString().toLowerCase());
                                                                                                                                                // sendStatus(mAuth.getCurrentUser().getEmail());
                                                                                                                                                // cris(mAuth.getCurrentUser().getEmail());
                                                                                                                                                //    month_counting(mAuth.getCurrentUser().getEmail(),mAuth.getCurrentUser().getUid());
                                                                                                                                                go_toCount(mAuth.getCurrentUser().getEmail(),mAuth.getCurrentUser().getUid());
                                                                                                                                                name=edtname.getText().toString();
                                                                                                                                                //email=edtemail.getText().toString();
                                                                                                                                                //  todays_free(input.getText().toString().toLowerCase() +"@gmail.com");
                                                                                                                                                password=edtcnfpass.getText().toString();
                                                                                                                                                mobile=edtnumber.getText().toString();
                                                                                                                                                Long tsLong = System.currentTimeMillis()/1000;
                                                                                                                                                String ts = tsLong.toString();

                                                                                                                                                String image22="https://firebasestorage.googleapis.com/v0/b/cash-money-express-ltd.appspot.com/o/profile_images%2Fo8Dnqf5LFodKSwocGQ4nKB7ZEkW2.jpg?alt=media&token=c22700e2-67ca-4497-8bf1-204ac83b6749";
                                                                                                                                                storeToFirestore1(name, "abc@gmail.com",image22, email);

                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    });

                                                                                                                        }
                                                                                                                    }
                                                                                                                });
                                                                                                    }
                                                                                                    else {
                                                                                                        progressDialog.dismiss();
                                                                                                        Toasty.error(getApplicationContext(), "No User Found .", Toast.LENGTH_SHORT, true).show();
                                                                                                        return;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        });


                                                                            }
                                                                        }
                                                                    }
                                                                });
                                                    }
                                                }
                                            }
                                        });
                            }
                        }).create().show();
                    }




                }
            }
        });
    }



    private void storeToFirestore1(final String name, final String toString, String image22, final String email) {
        Log.d(TAG+"_TXN","8. SAVING DATA TO FIRESTORE");
        // Storing data on Firestore...

        final Map<String, String> userMap = new HashMap<>();
        userMap.put("name",name);
        userMap.put("location",location.getText().toString());
        userMap.put("whatsapp",whatsapp.getText().toString());
        userMap.put("image", image22);
        userMap.put("number", edtnumber.getText().toString());
        userMap.put("email",edtemail.getText().toString().toLowerCase());
        userMap.put("pass",edtpass.getText().toString().toLowerCase());
        userMap.put("username", username.getText().toString().toLowerCase());
        userMap.put("transcationpin",editTextPassword111.getText().toString().toLowerCase().toString());
        // transcationpinstore(firebaseAuth.getCurrentUser().getEmail(),edtpass.getText().toString().toLowerCase());

        final Map<String, String> userMap11 = new HashMap<>();
        userMap11.put("password",editTextPassword111.getText().toString().toLowerCase().toString());
        firebaseFirestore.collection("Transcation_Pin")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .set(userMap11)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });



        firebaseFirestore.collection("Users").document(firebaseAuth.getCurrentUser().getUid()).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if(task.isSuccessful())
                {

                    // Sending Verification Link via Email
                    Log.d(TAG+"_TXN","9. DATA SAVED");
                    Log.d(TAG+"_TXN","10. SENDING EMAIL");
                    firebaseFirestore.collection("User2").document(firebaseAuth.getCurrentUser().getEmail())
                            .set(userMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        User_coun user_coun=new User_coun( name,toString,email,"0");
                                        firebaseFirestore.collection("Users")
                                                .document(firebaseAuth.getCurrentUser().getUid()).collection("Coins")
                                                .document(mAuth.getCurrentUser().getEmail())
                                                .set(user_coun)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            MainCoin mainCoin=new MainCoin("0");
                                                            firebaseFirestore.collection("Users").document(firebaseAuth.getCurrentUser().getUid())
                                                                    .collection("MainCoin")
                                                                    .document(mAuth.getCurrentUser().getEmail().toLowerCase())
                                                                    .set(mainCoin)
                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                            if (task.isSuccessful()) {
                                                                                Map<String, String> userMap1 = new HashMap<>();

                                                                                userMap1.put("username", edtnumber.getText().toString().toLowerCase());
                                                                                firebaseFirestore.collection("Old_User")
                                                                                        .document(edtnumber.getText().toString().toLowerCase())
                                                                                        .set(userMap1)
                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                if (task.isSuccessful()) {
                                                                                                    Map<String, String> userMap7 = new HashMap<>();

                                                                                                    userMap7.put("uuid",firebaseAuth.getCurrentUser().getUid());
                                                                                                    firebaseFirestore.collection("All_UserID")
                                                                                                            .document(mAuth.getCurrentUser().getEmail())
                                                                                                            .set(userMap7)
                                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                @Override
                                                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                                                    if (task.isSuccessful()) {
                                                                                                                        Map<String, String> paSS = new HashMap<>();

                                                                                                                        paSS.put("uuid",edtpass.getText().toString().toLowerCase());
                                                                                                                        firebaseFirestore.collection("Password")
                                                                                                                                .document(firebaseAuth.getCurrentUser().getEmail())
                                                                                                                                .set(paSS)
                                                                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                    @Override
                                                                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                        if (task.isSuccessful()) {
                                                                                                                                            //firebaseAuth.signOut();
                                                                                                                                            Handler handler=new Handler();
                                                                                                                                            handler.postDelayed(new Runnable() {
                                                                                                                                                @Override
                                                                                                                                                public void run() {
                                                                                                                                                    firebaseAuth.signOut();
                                                                                                                                                    Toasty.success(getApplicationContext(), "Account Create  Successfully Done.", Toasty.LENGTH_SHORT, true).show();

                                                                                                                                                    Intent loginSuccess = new Intent(Register2.this,MainActivity.class);

                                                                                                                                                    startActivity(loginSuccess);
                                                                                                                                                    finish();
                                                                                                                                                }
                                                                                                                                            },1000);


                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                });

                                                                                                                    }
                                                                                                                }
                                                                                                            });

                                                                                                }
                                                                                            }
                                                                                        });
                                                                            }
                                                                        }
                                                                    });
                                                        }
                                                    }
                                                });
                                    }
                                }
                            });





                } else {
                    Log.d(TAG+"_ERR5","ERROR IN SAVING DATA");
                    progressDialog.dismiss();
                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(Register2.this,"FIRESTORE Error : "+errorMessage,Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    private void progress_check() {
        progressDialog = KProgressHUD.create(Register2.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Uploading Data....")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

    }
    private void go_toCount(String email, String uid) {
        firebaseFirestore.collection("Users").document(uid)
                .collection("Counter")
                .document(email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            if (task.getResult().exists()) {


                            }
                            else {

                                Map<String, String> userMap1 = new HashMap<>();

                                userMap1.put("counter","1");
                                firebaseFirestore.collection("Users").document(uid)
                                        .collection("Counter")
                                        .document(email)
                                        .set(userMap1)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Map<String, String> userMap2 = new HashMap<>();

                                                    userMap2.put("main_balance","0");
                                                    userMap2.put("purches_balance","0");
                                                    userMap2.put("giving_balance","0");
                                                    userMap2.put("self_income","0");
                                                    userMap2.put("sponsor_income","0");
                                                    userMap2.put("first_level","0");
                                                    userMap2.put("second_level","0");
                                                    userMap2.put("third_level","0");
                                                    userMap2.put("forth_level","0");
                                                    userMap2.put("fifth_level","0");
                                                    userMap2.put("shoping_balance","0");
                                                    userMap2.put("cashwalet","0");
                                                    userMap2.put("monthly_income","0");
                                                    userMap2.put("leader_club","0");
                                                    userMap2.put("gen_bonus","0");
                                                    userMap2.put("ref_bonus","0");
                                                    userMap2.put("daily_income","0");

                                                    firebaseFirestore.collection("Users").document(uid)
                                                            .collection("Main_Balance")
                                                            .document( email)
                                                            .set(userMap2)
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {
                                                                        Map<String, String> userMap3 = new HashMap<>();

                                                                        userMap3.put("rating","0");
                                                                        firebaseFirestore.collection("Users").document(uid)
                                                                                .collection("Rating")
                                                                                .document( email)
                                                                                .set(userMap3)
                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                        if (task.isSuccessful()) {
                                                                                            Map<String, String> userMap4 = new HashMap<>();

                                                                                            userMap4.put("package","Inactive");
                                                                                            firebaseFirestore.collection("Users").document(uid)
                                                                                                    .collection("Package")
                                                                                                    .document( email)
                                                                                                    .set(userMap4)
                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                        @Override
                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                            if (task.isSuccessful()) {
                                                                                                                Map<String, String> userMap5= new HashMap<>();

                                                                                                                userMap5.put("ammount","0");
                                                                                                                firebaseFirestore.collection("SendMoney").document(email)
                                                                                                                        .set(userMap5)
                                                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                            @Override
                                                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                if (task.isSuccessful()) {
                                                                                                                                }
                                                                                                                            }
                                                                                                                        });
                                                                                                            }
                                                                                                        }
                                                                                                    });
                                                                                        }
                                                                                    }
                                                                                });


                                                                    }
                                                                }
                                                            });


                                                }
                                            }
                                        });



                            }////
                        }
                    }
                });
    }




    private boolean validateNumber() {

        check = edtnumber.getText().toString();
        Log.e("inside number", check.length() + " ");
        if (check.length() > 10) {
            return false;
        } else return check.length() >= 10;
    }

    private boolean validateCnfPass() {

        check = edtcnfpass.getText().toString();

        return check.equals(edtpass.getText().toString());
    }
    TextWatcher passWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length() < 4 || check.length() > 20) {
                edtpass.setError("Password Must consist of 4 to 20 characters");
            } else if (!check.matches("^[A-za-z0-9@]+")) {
                edtemail.setError("Only @ special character allowed");
            }
        }

    };
    private boolean validatePass() {


        check = edtpass.getText().toString();

        if (check.length() < 4 || check.length() > 20) {
            return false;
        } else return check.matches("^[A-za-z0-9@]+");
    }



    private boolean validateName() {

        check = edtname.getText().toString();

        return !(check.length() < 4 || check.length() > 20);

    }


    TextWatcher nameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length() < 4 || check.length() > 20) {
                edtname.setError("Name Must consist of 4 to 20 characters");
            }
        }

    };

//username
    //TextWatcher for Name -----------------------------------------------------

    TextWatcher nameWatcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length() < 2 || check.length() > 10) {
                username.setError("Username Must consist of 2 to 10 characters");
            }
        }

    };


    //TextWatcher for repeat Password -----------------------------------------------------

    TextWatcher cnfpassWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (!check.equals(edtpass.getText().toString())) {
                edtcnfpass.setError("Both the passwords do not match");
            }
        }

    };
    //TextWatcher for Mobile -----------------------------------------------------

    TextWatcher numberWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length() > 11) {
                edtnumber.setError("Number cannot be grated than 11 digits");
            } else if (check.length() < 11) {
                edtnumber.setError("Number should be 11 digits");
            }
        }

    };
}