package com.hire.foxes.bottom_navigation.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hire.foxes.R;
import com.hire.foxes.bottom_navigation.pojo_class.home_services_data;
import com.hire.foxes.bottom_navigation.provider;

import java.util.List;

public class home_services_adapter extends RecyclerView.Adapter<home_services_adapter.MyViewHolder> {

    Context context;
    List<home_services_data> apps;


    public home_services_adapter(Context context, List<home_services_data> apps) {
        this.context = context;
        this.apps = apps;;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView popular_product_name;
        ImageView popular_product_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            popular_product_name = itemView.findViewById(R.id.name);
            popular_product_image = itemView.findViewById(R.id.image);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.services_layout,parent,false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final home_services_data app = apps.get(position);

        holder.popular_product_name.setText(app.getName());
        Glide.with(context).load(app.getImage()).into(holder.popular_product_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,provider.class);
                intent.putExtra("service_name",app.getName());
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return apps.size();
    }
}
