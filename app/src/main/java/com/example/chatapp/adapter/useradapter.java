package com.example.chatapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.R;
import com.example.chatapp.chatactivity;
import com.example.chatapp.models.modelofuser;
// UserAdapter.java
public class useradapter extends RecyclerView.Adapter<useradapter.UserViewHolder> {

    private List<modelofuser> userList;
    Context context;
//    userList = new ArrayList<>();

    public useradapter(Context context , List<modelofuser>userList) {
        this.userList = userList;
        this.context = context;
    }

    public void setUserList(List<modelofuser> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coustomuserlist, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        modelofuser user = userList.get(position);
        holder.bind(user);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, chatactivity.class);
                intent.putExtra("userid",user.getUserid());
                intent.putExtra("name",user.getName());
                intent.putExtra("lastmessage",user.getLastmessgae());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView name,lastmessage;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);


        }

        public void bind(modelofuser user) {
            name.setText(user.getName());

        }
    }
}
