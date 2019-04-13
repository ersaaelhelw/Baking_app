package com.example.manara.paking_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Ingredients_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_);
        ArrayList<ingredients> ingredients;
        ArrayList<Steps>stepsList;

        Intent intent=getIntent();
        ingredients=intent.getParcelableArrayListExtra("ingredients");
        stepsList=intent.getParcelableArrayListExtra("stepsList");

        Recipe_Details recipeDetails=new Recipe_Details();
        recipeDetails.set_data(ingredients,stepsList);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container,recipeDetails).commit();

    }

    public void fire(ArrayList<Steps>steps,int postion)
    {
        if (findViewById(R.id.fragment_ingrediant_Tablet)==null)
        {
            String description ,videoURL;

            Intent startChildActivityIntent = new Intent(this,View_Video_Activity.class);
            description=steps.get(postion).getDescription();
            videoURL=steps.get(postion).getVideoURL();
            startChildActivityIntent.putExtra("description",description);
            startChildActivityIntent.putExtra("videoURL",videoURL);
            startChildActivityIntent.putExtra("image",steps.get(postion).image);

            startActivity(startChildActivityIntent);
        }
        else {
        Video_Fragment  video_fragment= new Video_Fragment();
        video_fragment.set_data(steps.get(postion).getDescription(),steps.get(postion).videoURL,steps.get(postion).getImage());
            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.fragment_ingrediant_Tablet,video_fragment).commit();
        }
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