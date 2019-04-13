package com.example.manara.paking_app;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Recipe>>,Recipe_Adapter.ListItemClickListener {

    List<Recipe>recipeList=new ArrayList<>();
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //noinspection deprecation
        getSupportLoaderManager().initLoader(3, null, this);

        

    }


    void set_recycler()
    {
        if(findViewById(R.id.main_gridView) != null){
            // tablet mode

            RecyclerView recyclerView = findViewById(R.id.main_gridView);
            Recipe_Adapter recipe_adapter = new Recipe_Adapter(recipeList,context,this);
            recyclerView.setLayoutManager(new GridLayoutManager( context,3));
            recyclerView.setAdapter(recipe_adapter);
        }
        else {
            RecyclerView recyclerView = findViewById(R.id.recipe);
            Recipe_Adapter recipe_adapter = new Recipe_Adapter(recipeList, context, this);
            LinearLayoutManager reviewLayoutManager = new LinearLayoutManager(this);
            reviewLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(reviewLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(recipe_adapter);
        }
    }

    @NonNull
    @Override
    public Loader<List<Recipe>> onCreateLoader(int i, @Nullable Bundle bundle) {

        return new Recipe_Loader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Recipe>> loader, List<Recipe> o) {
        
        recipeList=o;
        set_recycler();

    }


    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {


        ArrayList<ingredients> ingredients=new ArrayList<>();
        ArrayList<Steps>stepsList=new ArrayList<>();
        Context context = MainActivity.this;
        Class destinationActivity = Ingredients_Activity.class;
        Intent startChildActivityIntent = new Intent(context, destinationActivity);


        ingredients=  recipeList.get(clickedItemIndex).getIngredients();
        stepsList=  recipeList.get(clickedItemIndex).getStepsList();


        startChildActivityIntent.putParcelableArrayListExtra("ingredients",  ingredients);
        startChildActivityIntent.putParcelableArrayListExtra("stepsList",  stepsList);
        
        startActivity(startChildActivityIntent);

    }
    @Nullable
    private ForTest mIdlingResource;

    /**
     * Only called from test, creates and returns a new {@link ForTest}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new ForTest();
        }
        return mIdlingResource;
    }


}
