package com.example.fptdictionary.tuvungtienganh;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fptdictionary.R;

import java.io.IOException;

public class compare extends AppCompatActivity {
    private ImageButton btnBackMain;
    private TextView txtTenChuDe;
    private Button start, stop, play;
    private MediaRecorder myAudioRecorder;
    private String outputFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        addControls();
        addEvents();

    }

}