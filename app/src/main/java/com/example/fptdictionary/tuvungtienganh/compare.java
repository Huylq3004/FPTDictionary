package com.example.fptdictionary.tuvungtienganh;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1000);
        }
    }

    private void addControls() {
        txtTenChuDe = findViewById(R.id.txtTenChuDe);
        txtTenChuDe.setText("Phát âm");
        btnBackMain = findViewById(R.id.btnBackDsChuDe);
        start = findViewById(R.id.btnStart);
        stop = findViewById(R.id.btnStop);
        play = findViewById(R.id.btnStartPlaying);
        stop.setEnabled(false);
        play.setEnabled(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            outputFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/recording.3gp";
        } else {
            outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
        }

        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);
    }

    private void addEvents() {
        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(compare.this, TuDien.class);
                startActivity(intent2);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                    start.setEnabled(false);
                    stop.setEnabled(true);
                    Toast.makeText(compare.this, "Bắt đầu ghi âm", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(compare.this, "Lỗi khi bắt đầu ghi âm", Toast.LENGTH_SHORT).show();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    myAudioRecorder.stop();
                    myAudioRecorder.release();
                    myAudioRecorder = null;
                    start.setEnabled(true);
                    stop.setEnabled(false);
                    play.setEnabled(true);
                    Toast.makeText(getApplicationContext(), "Ghi âm thành công", Toast.LENGTH_LONG).show();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Lỗi khi dừng ghi âm", Toast.LENGTH_LONG).show();
                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(outputFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "Đang phát âm thanh", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Lỗi khi phát âm thanh", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}