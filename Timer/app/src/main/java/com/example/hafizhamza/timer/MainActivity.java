package com.example.hafizhamza.timer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekBar;
    boolean TimerisActive=false;
    Button buttongo;
    CountDownTimer countDownTimer;
    public  void resert()
    {
        textView.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        buttongo.setText("Go");
        TimerisActive=false;
    }
    public void Go(View view) {

        if(TimerisActive)
        {
            resert();
        }
        else {
            TimerisActive = true;
            seekBar.setEnabled(false);
            buttongo.setText("Stop");

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    update((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.letsstart);
                    mediaPlayer.start();
                    resert();
                    Toast toast = Toast.makeText(getApplicationContext(), "Its Time To Play", Toast.LENGTH_SHORT);
                    toast.show();

                }
            }.start();

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            textView = (TextView) findViewById(R.id.MytextView);
            seekBar = (SeekBar) findViewById(R.id.MyseekBar);
            buttongo=(Button)findViewById(R.id.Mybutton);
            seekBar.setMax(600);
            seekBar.setProgress(30);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    update(i);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }

public void update(int i)
{
    int mint=i/60;
    int second=i-(mint*60);
    String secstring=Integer.toString(second);
    if (second<=9)
    {
        secstring="0"+secstring;
    }

    textView.setText(Integer.toString(mint)+":"+secstring);

}

}
