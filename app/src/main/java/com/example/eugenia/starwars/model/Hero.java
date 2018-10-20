package com.example.eugenia.starwars.model;

import com.google.gson.annotations.SerializedName;

public class Hero {

    @SerializedName("name")
    private String mName;

    @SerializedName("height")
    private String mHeight;

    @SerializedName("mass")
    private String mMass;

    @SerializedName("hair_color")
    private String mHairColor;

    @SerializedName("skin_color")
    private String mSkinColor;

    @SerializedName("eye_color")
    private String mEyeColor;

    @SerializedName("birth_year")
    private String mBirthYear;

    @SerializedName("gender")
    private String mGender;

    @SerializedName("homeworld")
    private String mHomeworld;

    public String getmName() {
        return mName;
    }

    /*"name": "Luke Skywalker",
    "height": "172",
    "mass": "77",
    "hair_color": "blond",
    "skin_color": "fair",
    "eye_color": "blue",
    "birth_year": "19BBY",
    "gender": "male",
    "homeworld": "https://swapi.co/api/planets/1/",*/
}
