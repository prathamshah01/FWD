package com.example.fwd;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HistoryDataAdapter  extends RecyclerView.Adapter<HistoryDataAdapter.HistoryViewHolder> {

    Context context;
    ArrayList<Donator> list;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseRef = database.getReference("Donator");


    public HistoryDataAdapter(Context context, ArrayList<Donator> list){
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.history_layout,parent,false);
        return  new HistoryDataAdapter.HistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryDataAdapter.HistoryViewHolder holder, int position) {
        Donator donator = list.get(position);



        String phone = donator.getPhonenumber();




            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.history_layout, null);
            Log.i("A",""+donator);

            holder.Name.setText(donator.getName());
            holder.Foodtype.setText(donator.getFoodtype());
            holder.Foodcount.setText(donator.getFoodcount());
            holder.Status.setText(donator.getStatus());
            holder.myKey.setText(donator.getKey());


  //      else {
//            boolean isPhoneNumberValid = !value.equals(""+phone);
//            View myView = holder.crdHistory;
//            holder.crdHistory.setVisibility(myView.GONE);
//
//            CardView cardView = holder.crdHistory;
//            cardView.setVisibility(cardView.GONE);

      //  }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class HistoryViewHolder extends RecyclerView.ViewHolder{

        TextView Name, Foodtype, Foodcount, Status,myKey;
        CardView crdHistory;


        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.txtHName);
            Foodtype = itemView.findViewById(R.id.txtHFoodType);
            Foodcount = itemView.findViewById(R.id.txHIncludes);
            Status = itemView.findViewById(R.id.txtHStatus);
            myKey = itemView.findViewById(R.id.txtHKey);
        }
    }

}
