package com.example.eugenia.starwars.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("count")
    private Integer mCount;

    @SerializedName("results")
    private List<Hero> mResult;

    public Integer getmCount() {
        return mCount;
    }

    public List<Hero> getmResult() {
        return mResult;
    }
}
