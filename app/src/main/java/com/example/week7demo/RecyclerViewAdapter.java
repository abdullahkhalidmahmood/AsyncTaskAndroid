package com.example.week7demo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<GameModel> gameModel;
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<GameModel> gameModel) {
        this.context = context;
        this.gameModel = gameModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.textView_title.setText(gameModel.get(position).getTitle());
       // holder.imageView.setImageResource(gameModel.get(position).getImage());

        int id = context.getResources().getIdentifier("rainbow_six_siege_thumb.png", "drawable", context.getPackageName());
        Log.i("check",String.valueOf(id));
       holder.imageView.setImageResource(R.drawable.arma_thumb);
        holder.textView_score.setText(gameModel.get(position).getScore());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                Log.i("img",String.valueOf(gameModel.get(position).getImage()));
            }
        });

        //onclick
    }

    @Override
    public int getItemCount() {
        return gameModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
         TextView textView_title, textView_score;
         ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_title=itemView.findViewById(R.id.cardView_title);
            textView_score=itemView.findViewById(R.id.cardView_score);
            imageView=itemView.findViewById(R.id.cardView_image);

        }
    }
}
