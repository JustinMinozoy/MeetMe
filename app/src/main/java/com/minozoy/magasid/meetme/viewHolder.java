package com.minozoy.magasid.meetme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class viewHolder extends RecyclerView.ViewHolder {

    View mView;

    public viewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
    }

    public  void Setdetails(Context ctx, String Title, String Price, String image){
        TextView textView1 = mView.findViewById(R.id.Title);
        TextView textView2 = mView.findViewById(R.id.price);
        ImageView imageView1 = mView.findViewById(R.id.Image);

        textView1.setText(Title);
        textView2.setText(Price);
        Picasso.get().load(image).into(imageView1);
    }

}
