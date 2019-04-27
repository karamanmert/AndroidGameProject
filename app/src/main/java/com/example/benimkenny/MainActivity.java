package com.example.benimkenny;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Runnable runnable;
    Handler handler;
    TextView textViewScore,textViewTime;
    int score;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[]imageArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewScore=findViewById(R.id.textViewScore);
        textViewTime=findViewById(R.id.textViewTime);
        score=0;
        imageView=findViewById(R.id.imageView);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageArray=new ImageView[]{imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};
        hideImages();

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textViewTime.setText("Time: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                textViewTime.setText("Time is over");
                handler.removeCallbacks(runnable);
                for (ImageView image:imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Restart");
                alertDialog.setMessage("Are you sure to restart the game?");
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);

                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game Over!",Toast.LENGTH_LONG).show();
                        textViewTime.setText("Game is over, your score is: "+score);
                        textViewScore.setVisibility(View.INVISIBLE);
                    }
                });
                alertDialog.show();
            }
        }.start();

    }
    public void increaseScore(View view)
    {
        score++;
        textViewScore.setText("Score: "+score);
    }
    public void hideImages()
    {
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView image:imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
}
