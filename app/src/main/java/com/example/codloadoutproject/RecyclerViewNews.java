package com.example.codloadoutproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.codloadoutproject.DB.DataBase;
import com.example.codloadoutproject.DB.NewsDAO;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewNews extends RecyclerView.Adapter<RecyclerViewNews.ViewHolder> {

    Context context;
    List<News> arrayList = new ArrayList<>();


    public RecyclerViewNews(Context context,List<News> arrayList){

        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(arrayList.get(position).getTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"News"+position,Toast.LENGTH_LONG).show();
                Intent intent= new Intent(context,NewsDetails.class);
                intent.putExtra("title",arrayList.get(position).getTitle());
                intent.putExtra("body",arrayList.get(position).getBody());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            textView = itemView.findViewById(R.id.titleNews);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
