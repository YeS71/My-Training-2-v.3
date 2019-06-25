package com.example.sevchuk.mytraininng2;

import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.Button;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends YouTubeBaseActivity {
    YouTubePlayerView myYouTubePlayerView1;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    // For train view
    Button btnVideo; // button for start exercise
    VideoView videoV; // view for exercises in train screen
    TextView timerEx; // timer for exercise
    TextView nameEx; // execise name
    int indexV = 0; // index in array of video files
    int[] resVideos; // array of video for exercises
    int seconds = 30; // duration of all count in seconds output

    int lenVideo; // length of array with video file for training
    int lenTimers; // length of array with timers for training
    int lenAudio; // length of array with audio files for training

    String defaultPath = "android.resource://com.example.sevchuk.mytraininng2/";

    CountDownTimer countDownTimer;
    private long timeCountInMilliSeconds = 1 * 60000; // for progress bar circle
    private ProgressBar progressBarCircle; // circle of progress bar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Log.d(TAG,"onCreate: Starting");
        btnPlay = (Button)findViewById(R.id.btnPlay);
        myYouTubePlayerView1 = (YouTubePlayerView)findViewById(R.id.youtubePlay);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//          Log.d(TAG,"onClick: Done initializing");
//       For list of video with play from YouTube:
                List<String> videoList = new ArrayList<>();
                videoList.add("A2or3oHYFME");
                videoList.add("Gf8rEGgYoSI");
                videoList.add("1v8SouAqcEg");
                youTubePlayer.loadVideos(videoList);
//                youTubePlayer.loadVideo("yl_0qHua8pI&list=PLJRvzVNxEVuk-ozgesNPxfwQ48SsFXAww&index=2&t=218s");
//                youTubePlayer.loadVideo("PLJRvzVNxEVuk-ozgesNPxfwQ48SsFXAww&index");

//       For one video:
//                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
//                youTubePlayer.setPlayerStateChangeListener(myPlayerStateChangeListener);
//                youTubePlayer.setPlaylistEventListener(myPlaylistEventListener);
//                youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE);
//                youTubePlayer.loadVideo("A2or3oHYFME");
//                youTubePlayer.loadVideo("FoCSwWqJHx8&list=PLLsi3fZxvvTKQndzBfIguJgRloeaevXl_&index=2");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//          Log.d(TAG,"onClick: Filed initialize");

            }
        };

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
//          Log.d(TAG,"onClick: Initialize YouTube player");
            myYouTubePlayerView1.initialize(YouTubeConfig.getApiKey(),onInitializedListener);

            }
        });

    }

    /*
        Initialization screen 2 (for training)
        videoArray - refereces array of resourse with vidoe for current training
     */
    public void setInit(View view) {
        //    set Content View to screen;
        setContentView(R.layout.activity_train);

        btnVideo = (Button) findViewById(R.id.btnStart);
        videoV = (VideoView) findViewById(R.id.videoView);
        timerEx = (TextView) findViewById(R.id.txtSec);
        nameEx = (TextView)findViewById(R.id.textExName);

        progressBarCircle = (ProgressBar) findViewById(R.id.progressBarCircle);

//        TypedArray ar = getResources().obtainTypedArray(videoArray);
//
//        int len = ar.length(); // length of array
//        resVideos = new int[len]; // create new array with name of files
//        for (int i = 0; i < len; i++)
//            resVideos[i] = ar.getResourceId(i, 0); // load array from resourses to new array

//        link=new LinkedList<String>();
//        //declare linklist globally
//        File file = new File(Environment.getExternalStorageDirectory()
//                + File.separator + "Funtube/UserData/Videos/" + File.separator);
//        File[] list = file.listFiles();
//        for (File f : list) {
//            String name = f.getName();
//            if (name.endsWith(".mp4"))
//                String path = file.getAbsolutePath() + name;
//            //adding all Videos To List
//            link.add(path);
//        }

//  Start videoplayer for exercises after click button
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onCreate(R.array.video_ex1_arr); // create playlist for exercises training #1
                initExercises();
//                playExercises();
            }
        });
    }


    /*
        Init for exercises
    */
    public void initExercises() {
        // initialisation
        // Load file for playlist (video+audio)
//        loadFiles();

//     Init array video files from resourses (see res/values/string.xml)
//        TypedArray ar = context.getResources().obtainTypedArray(R.array.video_ex1_arr);
        TypedArray ar = getResources().obtainTypedArray(R.array.video_ex1_arr);

        lenVideo = ar.length(); // length of array with video file for training
        resVideos = new int[lenVideo]; // create new array with name of files
        for (int i = 0; i < lenVideo; i++)
            resVideos[i] = ar.getResourceId(i, 0); // load array from resourses to new array

        ar.recycle(); // recycle temporary array

////     Init array of timers  from resourses (see res/values/string.xml)
//        ar = getResources().obtainTypedArray(R.array.timer_ex1_arr);
//
//        lenTimers = ar.length(); // length of array of timers
//        int[] resTimers = new int[lenTimers]; // create new array with timers
//        for (int i = 0; i < lenTimers; i++)
//            resTimers[i] = ar.getResourceId(i, 0); // load array from resourses to new array
//
//        ar.recycle(); // recycle temporary array

// Do stuff with resolved reference array, resIds[]...
//        for (indexV=0; indexV < len; indexV++ ) {
//            if (! videoV.isPlaying()) {
//                nameEx.setText("" + indexV + " sec: " + seconds);
//                nameEx.append(" " + indexV + " sec: " + seconds);
//                nameEx.refreshDrawableState();

//                try {
//                    Thread.sleep(1000);
//                } catch (Exception e) {
//                }

//            nameEx.setText("" + resTimers[ind]);
//
//            initCountDownTimer(seconds); // init and start countdown timer
////            // Play current file from playlist
//            videoplay(videoV, resVideos[0]);
//            }
//        }
        indexV = 0; // first exercise of training
        playExercises();
    }

    /*
        play new video for exercises
    */
    public void playExercises() {
            initCountDownTimer(seconds); // init and start new countdown timer for new exercise
//            initCountDownTimer(resTimers[indexV]); // init and start new countdown timer for new exercise
//            audioplay(audioV, resAudios[indexV]); // init and start new audio for new exercise
            videoplay(videoV, resVideos[indexV]); // init and start new video for new exercise
    }

    /*
     method to set circular progress bar values
     */
    private void setProgressBarValues(int sec) {

//        progressBarCircle.setMax((int) timeCountInMilliSeconds / 1000);
//        progressBarCircle.setProgress((int) timeCountInMilliSeconds / 1000);
        progressBarCircle.setMax((int) sec * 1000 / 1000);
        progressBarCircle.setProgress((int) sec * 1000 / 1000);
    }

    /*
    * Initialisation of countdown timer
    * */
    public void initCountDownTimer(int sec){
        setProgressBarValues(sec);
        // set countdown timer for
        countDownTimer = new CountDownTimer(sec * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
//                        timerEx.setText((int)(millisUntilFinished/1000));
                timerEx.setText("" + (int) (millisUntilFinished / 1000));
                progressBarCircle.setProgress((int) (millisUntilFinished / 1000));

            }

            @Override
            public void onFinish() {
                timerEx.setText("Finish "+indexV);
                videoV.stopPlayback();
                if (indexV < lenVideo-1){
                    indexV++;
                    playExercises();
                }
            }
        }.start();

    }


    public void videoplay(View v, int currentVideoResource){

//        String videoPath = "android.resource://com.example.sevchuk.mytraininng2/"+R.raw.two;
        Uri uri = Uri.parse(defaultPath+currentVideoResource);

//        Uri uri = Uri.parse(videoPath);
        videoV.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true); // set looping for exercise video
            }
        });

        videoV.setVideoURI(uri);
        videoV.start();
    }


}
