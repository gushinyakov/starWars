package com.example.eugenia.starwars;

public class HeroStarWars {

    private int name;
    private int imageResourcesId;

    public static final HeroStarWars[] heroesStarWars = {
        new HeroStarWars(R.string.yoda, R.drawable.icons8_yoda_48),
        new HeroStarWars(R.string.darth_vader, R.drawable.icons8_darth_vader_48),
        new HeroStarWars(R.string.luke_skywalker, R.drawable.icons8_luke_skywalker_48),
        new HeroStarWars(R.string.cylon_head, R.drawable.icons8_cylon_head_48),
        new HeroStarWars(R.string.cylon_head_new, R.drawable.icons8_cylon_head_new_48),
        new HeroStarWars(R.string.c3po, R.drawable.icons8_r2_d2_48),
        new HeroStarWars(R.string.asteroid, R.drawable.icons8_asteroid_48),
        new HeroStarWars(R.string.astronaut, R.drawable.icons8_astronaut_48),
        new HeroStarWars(R.string.launch, R.drawable.icons8_launch_48),
        new HeroStarWars(R.string.planet, R.drawable.icons8_planet_48),
        new HeroStarWars(R.string.star_wars, R.drawable.icons8_star_wars_48)
    };

    private HeroStarWars(int name, int imageResourcesId) {
        this.name = name;
        this.imageResourcesId = imageResourcesId;
    }

    public int getName() {
        return name;
    }

    public int getImageResourcesId() {
        return imageResourcesId;
    }
}
