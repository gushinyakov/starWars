package com.example.eugenia.starwars;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class HeroDetailActivity extends AppCompatActivity {

    public static final String HERO_DETAIL_ID = "heroDetail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        int heroId = (int) getIntent().getExtras().get(HERO_DETAIL_ID);
        TextView textView = findViewById(R.id.hero_name);
        textView.setText(HeroStarWars.heroesStarWars[heroId].getName());
        ImageView imageView = findViewById(R.id.hero_image);
        imageView.setImageDrawable(
                ContextCompat.getDrawable(this, HeroStarWars.heroesStarWars[heroId].getImageResourcesId()));
        imageView.setContentDescription(textView.getText());

    }
}
