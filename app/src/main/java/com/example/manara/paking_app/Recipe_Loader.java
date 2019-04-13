package com.example.manara.paking_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Recipe_Loader  extends AsyncTaskLoader{


    public Recipe_Loader(@NonNull Context context) {
        super(context);
    }



    //open the connection by using GetTrailer Class
    @Nullable
    @Override
    public List<Recipe> loadInBackground() {

        List<Recipe>recipeList=new ArrayList<>();
        Get_Data_Api getTrailer=new Get_Data_Api();
        try {
            recipeList=getTrailer.getRecipeList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return recipeList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
