package com.example.manara.paking_app;


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Recipe_Details extends Fragment implements Video_Adapter.ListItemClickListener {
    ArrayList<ingredients> ingredients =new ArrayList<>();
    ArrayList<Steps> stepsList =new ArrayList<>();

    TextView ingredient;
    Button add ;
    public Recipe_Details(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_resipe_details, container, false);
         ingredient=view.findViewById(R.id.ingredient);

        add = view.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToWidget();
                Toast.makeText(getContext(),"Done",Toast.LENGTH_LONG).show();
            }
        });

        if(savedInstanceState != null)
        {
            ingredients = savedInstanceState.getParcelableArrayList("ingredient");
            stepsList = savedInstanceState.getParcelableArrayList("steps");

        }
        for (int i=0;i<ingredients.size();i++) {
            ingredient.append(ingredients.get(i).getIngredient());
            ingredient.append(ingredients.get(i).getMeasure());
            ingredient.append(ingredients.get(i).getQuantity());
            ingredient.append("\n");
  }
        RecyclerView recyclerView=view.findViewById(R.id.video);
        Context context=getContext();
        Video_Adapter video_adapter=new Video_Adapter(stepsList,context,this);

        LinearLayoutManager reviewLayoutManager = new LinearLayoutManager(context);
        reviewLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(reviewLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(video_adapter);
        return view;
    }

    public void set_data(ArrayList <ingredients> ingredients,ArrayList<Steps> steps)
    {
    this.ingredients=ingredients;
    this.stepsList=steps;

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("ingredient",ingredients);
        outState.putParcelableArrayList("steps",stepsList);

        super.onSaveInstanceState(outState);
    }



    @Override
    public void onListItemClick(int clickedItemIndex) {

        Ingredients_Activity ingredients_activity= (Ingredients_Activity) getActivity();
        ingredients_activity.fire(stepsList,clickedItemIndex);
    }


    public void addToWidget(){

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getContext());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(getContext(), NewAppWidget.class));
        NewAppWidget.updateWidget(getContext(),appWidgetManager,appWidgetIds,String.valueOf(ingredient.getText()));

    }
}
