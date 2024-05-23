package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VideoRecorderActivity extends AppCompatActivity  {
    private MediaRecorder mediaRecorder;
    private boolean isRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_recorder);

        // Initialize the MediaRecorder
        initializeRecorder();
    }

    private void initializeRecorder() {
        mediaRecorder = new MediaRecorder();

        // Set the source, output format, and output file
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setOutputFile(getOutputMediaFile().toString());

        // Set other parameters such as video size, encoding bitrate, etc.
        // (This part depends on your requirements)

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle IOException (e.g., show an error message)
        }
    }

    private File getOutputMediaFile() {
        // Check if external storage is available
        if (!isExternalStorageWritable()) {
            // Handle the case where external storage is not writable
            return null;
        }

        // Create a directory for the video files
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MOVIES), "MyVideos");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                // Handle the case where directory creation fails
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "VIDEO_" + timeStamp + ".mp4");
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public void startRecording(View view) {
        if (!isRecording) {
            try {
                mediaRecorder.start();
                isRecording = true;
                // Update UI (e.g., change button text to "Stop")
            } catch (IllegalStateException e) {
                e.printStackTrace();
                // Handle IllegalStateException (e.g., show an error message)
            }
        }
    }

    public void stopRecording(View view) {
        if (isRecording) {
            mediaRecorder.stop();
            mediaRecorder.release();
            isRecording = false;
            // Update UI (e.g., change button text to "Record")
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Stop recording if the activity is paused
        stopRecording(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaRecorder when the activity is destroyed
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
}
