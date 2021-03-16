package com.kerelos.recyclerviewsample;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kerelos.recyclerviewsample.model.Student;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class MyRVAdapter extends RecyclerView.Adapter <MyRVAdapter.MyViewHolper> {

    ArrayList<Student> all ;

    public MyRVAdapter(ArrayList<Student> all) {
        this.all = all;
    }

    @NonNull
    @Override
    public MyViewHolper onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View customRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_row,viewGroup,false);
        MyViewHolper viewHolper = new MyViewHolper(customRow);

        return viewHolper;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolper myViewHolper, int i) {

        String rowItem = //"Id : "+all.get(i).getId()+"\n"
                "Name : "+all.get(i).getName()+"\n"
                +"Phone : "+all.get(i).getPhone()+"\n\n";
        myViewHolper.tvrow.setText(rowItem);
        myViewHolper.tvcall.setText(all.get(i).getPhone());

    }

    @Override
    public int getItemCount() {
        return all.size();
    }

    public static class MyViewHolper extends RecyclerView.ViewHolder{

        TextView tvrow;
        TextView tvcall;
        ImageButton IBVAShare,IBVACall;

        public MyViewHolper(@NonNull final View itemView) {
            super(itemView);

            tvrow = itemView.findViewById(R.id.txtRow);
            tvcall = itemView.findViewById(R.id.tvCall);
            IBVAShare = itemView.findViewById(R.id.IBVAShare);
            IBVAShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    share(tvrow.getText().toString(),"Share",itemView);
                }
            });

            IBVACall = itemView.findViewById(R.id.IBVACall);
            IBVACall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                     callOne (tvcall.getText().toString(),itemView);
                }
            });
        }

        private void callOne(String phone,View itemView) {

            Intent intent = new Intent(Intent.ACTION_DIAL);
            // TO DO add the link to the phone number

            intent.setData(Uri.parse("tel:"+phone));
            Toast.makeText(itemView.getContext(), phone, Toast.LENGTH_LONG).show();
            itemView.getContext().startActivity(intent);
        }

        // Share the contact data
        private void share (String message,String title, View view){

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
              //  String shareBody = message;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Class application");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
                view.getContext().startActivity(Intent.createChooser(sharingIntent, title));
        }

    }

}
