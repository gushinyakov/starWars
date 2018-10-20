package com.example.eugenia.starwars;

import android.app.Application;

import com.example.eugenia.starwars.rest.RestApiHero;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private RestApiHero mHeroRestService;
    private static final String BASE_URL_API = "https://swapi.co/";

    @Override
    public void onCreate() {
        super.onCreate();

        buildRest();
    }

    private void buildRest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mHeroRestService = retrofit.create(RestApiHero.class);
    }

    public RestApiHero getmRestApiHeroService() {
        return mHeroRestService;
    }
}
