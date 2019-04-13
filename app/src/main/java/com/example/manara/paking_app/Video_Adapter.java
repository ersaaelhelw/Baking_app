package com.example.manara.paking_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Video_Adapter extends RecyclerView.Adapter<Video_Adapter.ViewHolder> {

    ArrayList<Steps> stepsList = new ArrayList<>();



    final private Video_Adapter.ListItemClickListener mOnClickListener;
    Context context;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public Video_Adapter(ArrayList<Steps> stepsList, Context context,  Video_Adapter.ListItemClickListener listener) {
        this.stepsList =stepsList;
        this.context=context;
         mOnClickListener = listener;


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.video_items;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new Video_Adapter.ViewHolder(view);    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.button.setText(stepsList.get(i).shortshortDescription);


    }

    @Override
    public int getItemCount() {

        return stepsList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        public Button button;
        public ViewHolder(View view) {

            super(view);
            itemView.setOnClickListener(this);
            button=itemView.findViewById(R.id.video_button);
        }

        @Override
        public void onClick(View v) {


            int pos = getAdapterPosition();


            mOnClickListener.onListItemClick(pos);

        }
    }

}
