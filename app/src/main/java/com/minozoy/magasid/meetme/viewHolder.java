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

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v,getAdapterPosition());

            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                mClickListener.onLongCickListener(v, getAdapterPosition());
                return true;
            }
        });
    }

    public  void Setdetails(Context ctx, String Title, String Price, String image){
        TextView textView1 = mView.findViewById(R.id.Title);
        TextView textView2 = mView.findViewById(R.id.price);
        ImageView imageView1 = mView.findViewById(R.id.Image);

        textView1.setText(Title);
        textView2.setText(Price);
        Picasso.get().load(image).into(imageView1);
    }

    private viewHolder.ClickListener mClickListener;

    //interface to send call backs
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onLongCickListener(View view, int position);

    }

    public void setOnClickListener(viewHolder.ClickListener clickListener){
        mClickListener = clickListener;

    }

}
