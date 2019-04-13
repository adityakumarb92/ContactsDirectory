package com.aditya.passwordprofile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private ArrayList<User> list_members;
    View view;
    MyViewHolder holder;
    private Context context;

    public UserAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        list_members = users;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(context).inflate(R.layout.custom_row, parent, false);
        holder = new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {


        User list_items = list_members.get(position);
        holder.user_name.setText(list_items.getName());
        holder.user_pass.setText(list_items.getPasscode());
        holder.pic.setImageBitmap(convertToBitmap(list_items.getImage()));

    }


    @Override
    public int getItemCount() {
        return list_members.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView user_name, user_pass;
        private final ImageView pic;

        public MyViewHolder(View itemView) {
            super(itemView);

            user_name = itemView.findViewById(R.id.user_name);
            user_pass = itemView.findViewById(R.id.user_pass);
            pic = itemView.findViewById(R.id.user_icon);
        }

    }

    private Bitmap convertToBitmap(byte[] b) {

        return BitmapFactory.decodeByteArray(b, 0, b.length);

    }


}
