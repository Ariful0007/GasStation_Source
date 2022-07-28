package com.meass.gasstation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class Mosjidadapter extends RecyclerView.Adapter<Mosjidadapter.myview> {
    public List<mosjidmodel> data;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String classify;
    String dayarabic;
    public Mosjidadapter(List<mosjidmodel> data) {

        this.data = data;

    }

    @NonNull
    @Override
    public Mosjidadapter.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.healthcard,parent,false);
        return new Mosjidadapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Mosjidadapter.myview holder, final int position) {
holder.packagename.setText(data.get(position).getName());
holder.packagetime.setText("Owner Name : "+data.get(position).getLocation());
        if (data.get(position).getSunset().toLowerCase().equals("not set")) {
            holder.blog_detail_desc.setText(  "Manager Name : "+data.get(position).getFazor()+"\n" +
                    "Address : "+data.get(position).getSunrise()+" , "+data.get(position).getJahor()+"\n" +
                    "Permission No : "+data.get(position).getAsor()+"\n" +
                    ""+data.get(position).getMagrib()+" ( Office )"+"\n" +
                    ""+data.get(position).getEsha()+ " ( Manager )\n");

        }
        else {
            holder.blog_detail_desc.setText("Manager Name : "+data.get(position).getFazor()+"\n" +
                    "Address : "+data.get(position).getSunrise()+" , "+data.get(position).getJahor()+"\n" +
                    "Permission No : "+data.get(position).getAsor()+"\n" +
                    ""+data.get(position).getMagrib()+" ( Office )"+"\n" +
                    ""+data.get(position).getEsha()+ " ( Manager )\n" +
                    "Note : "+data.get(position).getSunset());

        }
        holder.makerules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(data.get(position).getJumma()));
                v.getContext().startActivity(intent);
            }
        });
        holder.bloas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s="tel:"+""+data.get(position).getMagrib();
                Intent intent33=new Intent(Intent.ACTION_DIAL);
                intent33.setData(Uri.parse(s));
                v.getContext().startActivity(intent33);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder{
        TextView packagename,packagetime,blog_detail_desc,logout,customer_area3,customer_area8,logout10;
        CardView card_view8;
        ImageView image;
        Button makerules,bloas;
        public myview(@NonNull View itemView) {
            super(itemView);
            packagename=itemView.findViewById(R.id.packagename);
            logout10=itemView.findViewById(R.id.logout10);
            packagetime=itemView.findViewById(R.id.packagetime);
            blog_detail_desc=itemView.findViewById(R.id.blog_detail_desc);

            card_view8=itemView.findViewById(R.id.card_view8);
            makerules=itemView.findViewById(R.id.makerules);
            bloas=itemView.findViewById(R.id.bloas);



        }
    }
}


