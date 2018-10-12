package com.example.eugenia.starwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRecyclerView = findViewById(R.id.hero_recucler_view);

        int[] names = new int[HeroStarWars.heroesStarWars.length];
        int[] imagesId = new int[HeroStarWars.heroesStarWars.length];

        for (int i=0; i < names.length; i++){
            names[i] = HeroStarWars.heroesStarWars[i].getName();
            imagesId[i] = HeroStarWars.heroesStarWars[i].getImageResourcesId();
        }

        HeroAdapter heroAdapter = new HeroAdapter(names, imagesId);
        mRecyclerView.setAdapter(heroAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        heroAdapter.setListener(new HeroAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getApplicationContext(), HeroDetailActivity.class);
                intent.putExtra(HeroDetailActivity.HERO_DETAIL_ID, position);
                startActivity(intent);
            }
        });

    }
}
