package com.example.eugenia.starwars;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eugenia.starwars.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.MyViewHolder> {

    private List<Hero> mHeroes = new ArrayList<>();

    private Listener listener;
    interface Listener {
        void onClick(int position);
    }


    public void addHeroes(List<Hero> heroes) {
        mHeroes.addAll(heroes);
        notifyDataSetChanged();
    }

    public void clear() {
        mHeroes.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HeroAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView cardView = (CardView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hero_card_view, viewGroup, false);
        return new MyViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroAdapter.MyViewHolder myViewHolder, final int i) {
        final CardView cardView = myViewHolder.cardView;

        Hero hero = mHeroes.get(i);
        TextView textView = cardView.findViewById(R.id.hero_name);
        textView.setText(hero.getmName());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHeroes.size();
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        MyViewHolder(@NonNull CardView v) {
            super(v);
            cardView = v;
        }
    }


}
