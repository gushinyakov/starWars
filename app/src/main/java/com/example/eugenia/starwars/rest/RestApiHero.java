package com.example.eugenia.starwars.rest;

import com.example.eugenia.starwars.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiHero {

    @GET("/api/people/?format=json")
    Call<Result> getAllHeroes(
            @Query("page") Integer page
    );
}
