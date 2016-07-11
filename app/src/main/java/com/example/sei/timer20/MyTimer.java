package com.example.sei.timer20;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MyTimer extends Activity implements View.OnClickListener{
    ProgressBar bar = null;
    ProgressBar bar2 = null;
    Button startButton = null;
    TextView tab2,tab3,text1,text2,text3;
    private int TIME = 1000;
    private int j = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_timer);
        bar = (ProgressBar) findViewById(R.id.bar);
        bar2 = (ProgressBar) findViewById(R.id.bar2);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);

        tab2 = (TextView) findViewById(R.id.tab2);
        tab3 = (TextView) findViewById(R.id.tab3);

        text1.setOnClickListener(this);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);

        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setVisibility(View.VISIBLE);
                updateBarHandler.sendEmptyMessage(0);
            }
        });
        handler.postDelayed(runnable, TIME);
    }
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            // 标签2中的苹果钟实现
            try {
                handler.postDelayed(this, TIME);
                tab2.setText(Integer.toString(j++));
                System.out.println("do...");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("exception...");
            }
            switch (j){
                case 18:
                    Toast toast = Toast.makeText(getApplicationContext(), "Break Time", Toast.LENGTH_LONG);//显示时间较长
                    toast.setGravity(Gravity.CENTER, 0, 0);// 居中显示
                    toast.show();
                    break;
                case 22:
                    toast = Toast.makeText(getApplicationContext(), "Work Time", Toast.LENGTH_LONG);//显示时间较长
                    toast.setGravity(Gravity.CENTER, 0, 0);// 居中显示
                    toast.show();
                    j=0;
                    break;
            }
        }
    };
        //标签1中的蹲坑提醒实现
        Handler updateBarHandler = new Handler(){
        int i = 0;
        @Override
        public void handleMessage(Message msg){
            if (msg.what == 0){
                updateBarHandler.removeMessages(0);
                i++;
                Log.d("Sei","i="+i);
                bar.setProgress(i);
                if (i <= 100) {
                    updateBarHandler.sendEmptyMessageDelayed(0,100);
                } else {
                    i = 0;
                    Toast toast = Toast.makeText(getApplicationContext(), "蹲的时间太长了，快起来", Toast.LENGTH_LONG);//显示时间较长
                    toast.setGravity(Gravity.CENTER, 0, 0);// 居中显示
                    toast.show();
                }
            }
        }
    };
    //标签之间切换实现
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text1:
                text1.setBackgroundResource(R.mipmap.ic_launcher);
                text2.setBackground(null);
                text3.setBackground(null);
                bar.setVisibility(View.VISIBLE);
                bar2.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.VISIBLE);
                tab2.setVisibility(View.INVISIBLE);
                tab3.setVisibility(View.INVISIBLE);
                break;
            case R.id.text2:
                text2.setBackgroundResource(R.mipmap.ic_launcher);
                text1.setBackground(null);
                text3.setBackground(null);
                bar.setVisibility(View.INVISIBLE);
                bar2.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.INVISIBLE);
                tab2.setVisibility(View.VISIBLE);
                tab3.setVisibility(View.INVISIBLE);
                break;
            case R.id.text3:
                text3.setBackgroundResource(R.mipmap.ic_launcher);
                text1.setBackground(null);
                text2.setBackground(null);
                bar.setVisibility(View.INVISIBLE);
                bar2.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.INVISIBLE);
                tab2.setVisibility(View.INVISIBLE);
                tab3.setVisibility(View.VISIBLE);
                break;
            default:
                bar.setVisibility(View.VISIBLE);
                bar2.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.VISIBLE);
                tab2.setVisibility(View.INVISIBLE);
                tab3.setVisibility(View.INVISIBLE);
                break;
    }
}}