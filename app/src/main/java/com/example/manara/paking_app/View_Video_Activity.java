package com.example.manara.paking_app;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class View_Video_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__video_);
        String description ,videoURL;
        Intent intent=getIntent();

        description=intent.getStringExtra("description");
        videoURL=intent.getStringExtra("videoURL");

        Video_Fragment video_fragment=new Video_Fragment();
        video_fragment.set_data( description ,videoURL,intent.getStringExtra("image"));
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container_video,video_fragment).commit();



    }
}
