package com.example.manara.paking_app;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable{
     String name,servings;
     int id;
    ArrayList<ingredients> ingredients=new ArrayList<>();
    ArrayList<Steps>stepsList=new ArrayList<>();



    public Recipe() {

    }

    protected Recipe(Parcel in) {
        name = in.readString();
        servings = in.readString();
        id = in.readInt();
        ingredients = in.createTypedArrayList(com.example.manara.paking_app.ingredients.CREATOR);
        stepsList = in.createTypedArrayList(Steps.CREATOR);
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getServings() {
        return servings;
    }


    public ArrayList<com.example.manara.paking_app.ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<com.example.manara.paking_app.ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Steps> getStepsList() {
        return stepsList;
    }

    public void setStepsList(ArrayList<Steps> stepsList) {
        this.stepsList = stepsList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(servings);
        dest.writeInt(id);
        dest.writeTypedList(ingredients);
        dest.writeTypedList(stepsList);
    }
}
