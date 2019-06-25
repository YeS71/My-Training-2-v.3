package com.example.sevchuk.mytraininng2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class MainActivity_original extends YouTubeBaseActivity {
    YouTubePlayerView myYouTubePlayerView;
    Button button;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d(TAG,"onCreate: Starting");
        button = (Button)findViewById(R.id.btnPlay);
        myYouTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtubePlay);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//          Log.d(TAG,"onClick: Done initializing");
//       For list of video:
//                List<String> videoList = new ArrayList<>();
//                videoList.add("A2or3oHYFME");
//                videoList.add("A2or3oHYFME");
//                videoList.add("A2or3oHYFME");
//                youTubePlayer.loadVideos(videoList);
//       For one video:
                youTubePlayer.loadVideo("A2or3oHYFME");
//                youTubePlayer.loadVideo("FoCSwWqJHx8&list=PLLsi3fZxvvTKQndzBfIguJgRloeaevXl_&index=2");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//          Log.d(TAG,"onClick: Filed initialize");

            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
//          Log.d(TAG,"onClick: Initialize YouTube player");
            myYouTubePlayerView.initialize(YouTubeConfig.getApiKey(),onInitializedListener);

            }
        });

    }
}
