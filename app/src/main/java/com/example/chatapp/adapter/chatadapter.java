package com.example.chatapp.adapter;


import android.content.Context;

import java.util.ArrayList;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.R;
import com.example.chatapp.models.chatmodel;
import com.google.firebase.auth.FirebaseAuth;


public class chatadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<chatmodel> model;
    Context context;
    int Senderviewtype = 1;
    int reciverviewtype = 2;
    String reciverid;

    public chatadapter(ArrayList<chatmodel> model, Context context, String reciverid) {
        this.model = model;
        this.context = context;
        this.reciverid = reciverid;
    }

    public chatadapter(ArrayList<chatmodel> model, Context context) {
        this.model = model;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Senderviewtype) {

            View view = LayoutInflater.from(context).inflate(R.layout.right, parent, false);
            return new senderviewholder(view);

        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.left, parent, false);
            return new reciverviewholder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        chatmodel chatmodel = model.get(position);
        if (holder.getClass() == senderviewholder.class) {
            ((senderviewholder) holder).righttext.setText(chatmodel.getMessage());
        } else {
            ((reciverviewholder) holder).lefttext.setText(chatmodel.getMessage());

        }

    }

    @Override
    public int getItemCount() {
        return model.size();
    }
    @Override
    public int getItemViewType(int position) {
        if (FirebaseAuth.getInstance().getUid().equals(model.get(position).getUserid())) {
            return Senderviewtype;
        } else {
            return reciverviewtype;

        }
    }

    public class reciverviewholder extends RecyclerView.ViewHolder {
        TextView lefttext;

        public reciverviewholder(@NonNull View itemView) {
            super(itemView);
            lefttext = itemView.findViewById(R.id.lefttext);

        }
    }

    public class senderviewholder extends RecyclerView.ViewHolder {
        TextView righttext;

        public senderviewholder(@NonNull View itemView) {
            super(itemView);
            righttext = itemView.findViewById(R.id.righttext);


        }
    }
}
