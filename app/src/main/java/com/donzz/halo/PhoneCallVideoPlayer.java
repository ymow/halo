package com.donzz.halo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.ShareActionProvider;
import android.widget.VideoView;

import java.io.File;

public class PhoneCallVideoPlayer extends ActionBarActivity {

    private VideoView mVideoView;
    private ImageButton mShareButton;
    private String mRecordingPath;
    private ShareActionProvider mShareActionProvider;
    private static final String TAG = PhoneCallVideoPlayer.class.getSimpleName();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_call_video_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mVideoView = (VideoView) findViewById(R.id.my_videoview);

        mShareButton = (ImageButton) findViewById(R.id.share_button);
        Intent intent = this.getIntent();
        mRecordingPath = intent.getStringExtra(CameraService.VIDEO_PATH);
        Log.i(TAG, mRecordingPath);
        mVideoView.setVideoURI(Uri.parse(mRecordingPath));
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        mVideoView.start();

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, mRecordingPath);
                shareIntent.setType("video/*");
                File media = new File(mRecordingPath);
                Uri uri = Uri.fromFile(media);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "I have a new Halo | PhoneCall movie");
                startActivity(shareIntent);
            }
        });
    }
}
