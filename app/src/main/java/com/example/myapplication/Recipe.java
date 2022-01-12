package com.example.myapplication;

public class Recipe {
    int recipe_code;
    String recipe_name;
    String recipe_sum;
    String type;
    String cook_time;
    String tip;
    String img_main;
    String effect;
    int like_sum;

    public Recipe(){

    }
    public Recipe(int recipe_code, String recipe_name){
        this.recipe_code = recipe_code;
        this.recipe_name = recipe_name;
    }
}
