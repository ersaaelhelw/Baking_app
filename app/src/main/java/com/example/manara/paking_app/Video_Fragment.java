package com.example.manara.paking_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;


public class Video_Fragment extends Fragment {

    public Video_Fragment() {
        // Required empty public constructor
    }
    String description,videoURL ;
    TextView textView;
    ImageView no_video;
    private static Long time = Long.valueOf(0);
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;

    String image;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_video_, container, false);
        textView=view.findViewById(R.id.des);
        no_video=view.findViewById(R.id.no_video);
        mPlayerView=view.findViewById(R.id.exoplayer);

        if (savedInstanceState != null) {
            description = savedInstanceState.getString("des");
            videoURL = savedInstanceState.getString("url");
            time = savedInstanceState.getLong("time");
            SharedPreferences.Editor editor = getContext().getSharedPreferences("shared", MODE_PRIVATE).edit();
            editor.putLong("time", time);
            editor.apply();
        }


        textView.setText(description);
        if (mExoPlayer != null) {
            mExoPlayer.setPlayWhenReady(false);
            mExoPlayer.release();
            mExoPlayer.stop();
            mExoPlayer = null;
        }

        if (videoURL.equals(""))
        {
            mPlayerView.setVisibility(View.GONE);
            no_video.setVisibility(View.VISIBLE);
            if (image.equals("")) {
                Picasso.with(getContext()).load(R.drawable.recipe_placeholder).into(no_video);
            }

        }
        else {
            mPlayerView.setVisibility(View.VISIBLE);
            no_video.setVisibility(View.GONE);


            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                //  landscape mode
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams
                        (ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
                textView.setVisibility(View.GONE);
                mPlayerView.setLayoutParams(params);

            }
        }


            int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams
                    (ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
            textView.setVisibility(View.GONE);
            mPlayerView.setLayoutParams(params);


        }

            return view;
        }


    public void set_data(String description , String videoURL,String image)
    {
        this.image=image;
     this.description=description;
     this.videoURL=videoURL;
    }

    private void initializePlayer(Uri mediaUri , Long time) {
        if(mediaUri!=null) {
            if (mExoPlayer == null) {
                // Create an instance of the ExoPlayer.
                TrackSelector trackSelector = new DefaultTrackSelector();
                LoadControl loadControl = new DefaultLoadControl();
                mExoPlayer = ExoPlayerFactory.newSimpleInstance(this.getContext(), trackSelector, loadControl);
                mPlayerView.setPlayer(mExoPlayer);


                String userAgent = Util.getUserAgent(this.getContext(), "Paking_App");
                MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                        this.getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
                mExoPlayer.prepare(mediaSource);
                mExoPlayer.seekTo(time);
                mExoPlayer.setPlayWhenReady(true);
            }
        }
        else
        {


        }



    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        time = getContext().getSharedPreferences("shared", MODE_PRIVATE).getLong("time",Long.valueOf(0));
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        if (mExoPlayer != null) {
            time = mExoPlayer.getCurrentPosition();
            mExoPlayer.setPlayWhenReady(false);
            mExoPlayer.release();
            mExoPlayer = null;
        }
        super.onPause();
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("des",description);
        outState.putString("url",videoURL);
        outState.putLong("time",time);
        super.onSaveInstanceState(outState);
    }



    @Override
    public void onStart() {
            initializePlayer(Uri.parse(videoURL), time);

            super.onStart();


    }

   @Override
    public void onStop() {
        SharedPreferences.Editor sharedPreferences = getContext().getSharedPreferences("shared", MODE_PRIVATE).edit();
        sharedPreferences.putLong("time",Long.valueOf(0));
        sharedPreferences.apply();
        super.onStop();
    }

}
