package com.example.manara.paking_app;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ingredients implements Parcelable{

    String ingredient,measure;
    String quantity;



    public ingredients() {

    }


    protected ingredients(Parcel in) {
        ingredient = in.readString();
        measure = in.readString();
        quantity = in.readString();
    }

    public static final Creator<ingredients> CREATOR = new Creator<ingredients>() {
        @Override
        public ingredients createFromParcel(Parcel in) {
            return new ingredients(in);
        }

        @Override
        public ingredients[] newArray(int size) {
            return new ingredients[size];
        }
    };

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ingredient);
        dest.writeString(measure);
        dest.writeString(quantity);
    }
}
