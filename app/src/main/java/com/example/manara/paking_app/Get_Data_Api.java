package com.example.manara.paking_app;

import android.graphics.Movie;
import android.provider.ContactsContract;
import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Get_Data_Api {

    ArrayList<Recipe>recipeList=new ArrayList<>();
    Recipe recipe=new Recipe();
    ingredients ingredients=new ingredients();
    Steps steps=new Steps();

    private static final String NAME = "name";
    private static final String INGREDIENTS = "ingredients";
    private static final String QUANTITY = "quantity";
    private static final String MEASURE = "measure";
    private static final String INGREDIENT = "ingredient";
    private static final String SHORTDESC = "shortDescription";
    private static final String DESC = "description";
    private static final String VIDEO_URL = "videoURL";
    private static final String IMAGE_URL = "thumbnailURL";
    private static final String SERVINGS = "servings";
    private static final String STEPS = "steps";

    String data="";

    public ArrayList<Recipe> getRecipeList() throws IOException {
        //read url
        try {
            URL url= new URL("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");

            //make connection to get data
            HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
            // read data by input stream
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedInputStream= new BufferedReader(new InputStreamReader(inputStream));
            String line ="";
            while (line!=null)
            {
                line =bufferedInputStream.readLine();
                data+=line;
            }
            JSONArray jsonArray=new JSONArray(data);

            for (int i =0;i<jsonArray.length();i++)
            {
                JSONObject recipeObject  = jsonArray.optJSONObject(i);
                recipe.setName(recipeObject.optString(NAME,"no"));
                recipe.setServings(recipeObject.optString(SERVINGS,"no"));

                JSONArray ing = recipeObject.optJSONArray(INGREDIENTS);
                ArrayList<ingredients>ingredientsList=new ArrayList<>();
                ArrayList<Steps>stepsList=new ArrayList<>();


                for (int j=0;j<ing.length();j++){
                    JSONObject ingArray = ing.optJSONObject(j);
                    ingredients.setQuantity( ingArray.optString(QUANTITY,"no"));
                     ingredients.setMeasure(ingArray.optString(MEASURE,"no"));
                    ingredients.setIngredient(ingArray.optString(INGREDIENT,"no"));
                    ingredientsList.add(ingredients);
                    ingredients=new ingredients();
                }
                JSONArray stepArray = recipeObject.optJSONArray(STEPS);

                for (int j=0;j<stepArray.length();j++){
                    JSONObject stepObject = stepArray.optJSONObject(j);
                    steps.setShortshortDescription(stepObject.optString(SHORTDESC,"no")) ;
                    steps.setDescription( stepObject.optString(DESC,"no"));
                    steps.setVideoURL(stepObject.optString(VIDEO_URL,"no"));
                    String Image = stepObject.optString("thumbnailURL","no");
                    steps.setImage(Image);
                    stepsList.add(steps);
                    steps=new Steps();
                }
                recipe.setIngredients(ingredientsList);
                recipe.setStepsList(stepsList);
                recipeList.add(recipe);
                 recipe=new Recipe();

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return recipeList;
    }

}
