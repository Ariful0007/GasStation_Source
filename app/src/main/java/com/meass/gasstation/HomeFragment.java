package com.meass.gasstation;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


public class HomeFragment extends Fragment {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    LottieAnimationView empty_cart;
    DocumentReference documentReference;
    RecyclerView recyclerView;
    Mosjidadapter getDataAdapter1;
    List<mosjidmodel> getList;
    String url;

    FirebaseUser firebaseUser;
    KProgressHUD progressHUD;
    String cus_name;

    String classify;
    SearchView name;
  View view;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        firebaseFirestore= FirebaseFirestore.getInstance();
        getList = new ArrayList<>();
        getDataAdapter1 = new Mosjidadapter(getList);
        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference  =    firebaseFirestore.collection("All_Gas_Station").document();
        recyclerView =view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(getDataAdapter1);
        reciveData();
        name=view.findViewById(R.id.name);
        name.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //fullsearch(query);

                //phoneSerach(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchAllUser(newText);

                //phoneSerach1(newText);
                return false;
            }
        });
        return view;
    }
    private void searchAllUser(String newText) {
        firebaseFirestore
                .collection("All_Gas_Station")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        getList.clear();
                        for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots) {
                            String dta = documentSnapshot.getString("name");
                            if (dta.toLowerCase().toString().contains(newText.toLowerCase().toString())) {
                                mosjidmodel add_customer=new mosjidmodel(documentSnapshot.getString("name"),
                                        documentSnapshot.getString("location")
                                        , documentSnapshot.getString("fazor"),
                                        documentSnapshot.getString("jahor"),
                                        documentSnapshot.getString("asor")
                                        , documentSnapshot.getString("magrib"),
                                        documentSnapshot.getString("esha"),
                                        documentSnapshot.getString("jumma"),
                                        documentSnapshot.getString("sunset"),
                                        documentSnapshot.getString("sunrise"),
                                        documentSnapshot.getString("datetime"),
                                        documentSnapshot.getString("time")
                                );
                                getList.add(add_customer);

                            }
                            getDataAdapter1 = new Mosjidadapter(getList);
                            recyclerView.setAdapter(getDataAdapter1);


                        }
                    }
                });
    }
    private void reciveData() {

        firebaseFirestore.collection("All_Gas_Station")
                .orderBy("time", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                    if (ds.getType() == DocumentChange.Type.ADDED) {

                 /*String first;
                 first = ds.getDocument().getString("name");
                 Toast.makeText(MainActivity2.this, "" + first, Toast.LENGTH_SHORT).show();*/
                        mosjidmodel get = ds.getDocument().toObject(mosjidmodel.class);
                        getList.add(get);
                        getDataAdapter1.notifyDataSetChanged();
                    }

                }
            }
        });

    }
}