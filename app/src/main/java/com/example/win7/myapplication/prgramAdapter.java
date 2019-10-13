package com.example.win7.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by win7 on 7/8/2019.
 */

public class prgramAdapter extends RecyclerView.Adapter<prgramAdapter.programViewHolder> {

    Context context;
    ArrayList<cardValues> profile;
    public prgramAdapter(Context c, ArrayList<cardValues> p){
        context = c;
        profile = p;
    }

    @Override
    public programViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.hiree_list_layout,parent,false);
        return new programViewHolder(view);
    }

    @Override
    public void onBindViewHolder(programViewHolder holder, int position) {
        holder.Lname.setText("Name: " + profile.get(position).getName());
        holder.Lcity.setText("City: " + profile.get(position).getCity());
        holder.Lspec.setText("Specialisation: " + profile.get(position).getJob());
        holder.Lemail.setText("Email: " + profile.get(position).getEmail());
        holder.Lphone.setText("Phone: " + profile.get(position).getPhone());
        holder.Lsex.setText("Sex: " + profile.get(position).getSex());


    }

    @Override
    public int getItemCount() {
        return profile.size();
    }

    public class programViewHolder extends RecyclerView.ViewHolder{
        TextView Lname;
        TextView Lcity;
        TextView Lspec;
        TextView Lemail;
        TextView Lphone;
        TextView Lsex;
        public programViewHolder(View itemView) {
            super(itemView);
            Lname = itemView.findViewById(R.id.listname);
            Lcity = itemView.findViewById(R.id.listcity);
            Lspec = itemView.findViewById(R.id.listspec);
            Lemail = itemView.findViewById(R.id.listemail);
            Lphone = itemView.findViewById(R.id.listphone);
            Lsex = itemView.findViewById(R.id.listsex);
        }
    }
}
