package com.hire.foxes.bottom_navigation.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hire.foxes.R;
import com.hire.foxes.bottom_navigation.pojo_class.provider_data;
import com.hire.foxes.bottom_navigation.provider_profile;

import java.util.List;

public class provider_adapter extends RecyclerView.Adapter<provider_adapter.MyViewHolder> {

    Context context;
    List<provider_data> apps;


    public provider_adapter(Context context, List<provider_data> apps) {
        this.context = context;
        this.apps = apps;;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView popular_product_name;
        ImageView popular_product_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            popular_product_name = itemView.findViewById(R.id.provider_name);
            popular_product_image = itemView.findViewById(R.id.provider_image);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.provider_layout,parent,false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final provider_data app = apps.get(position);

        Glide.with(context).load(app.getImage()).into(holder.popular_product_image);
        holder.popular_product_name.setText(app.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, provider_profile.class);
                intent.putExtra("developer_name",app.getName());
                intent.putExtra("developer_image",app.getImage());
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return apps.size();
    }
}
