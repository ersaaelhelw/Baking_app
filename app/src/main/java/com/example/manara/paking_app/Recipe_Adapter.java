package com.example.manara.paking_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Recipe_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Recipe> recipeList = new ArrayList<>();



    final private Recipe_Adapter .ListItemClickListener mOnClickListener;
    Context context;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public Recipe_Adapter(List<Recipe> recipeList, Context context,  Recipe_Adapter.ListItemClickListener listener) {
        this.recipeList =recipeList;
        this.context=context;
        mOnClickListener = listener;


    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.resipe_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new Recipe_Adapter.ViewHolder(view);    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        CardView cardView=viewHolder.itemView.findViewById(R.id.card);
        TextView textView=viewHolder.itemView.findViewById(R.id.recipe_name);
        TextView textView1=viewHolder.itemView.findViewById(R.id.serving);
        textView.setText(recipeList.get(i).name);
        textView1.setText(recipeList.get(i).servings);





    }

    @Override
    public int getItemCount() {

        return recipeList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        public ViewHolder(View view) {

            super(view);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {


            int pos = getAdapterPosition();


            mOnClickListener.onListItemClick(pos);

        }
    }



}
