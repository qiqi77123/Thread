package com.example.ssl.thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    private TextView show;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.Btn);
        show = (TextView)findViewById(R.id.Show);
        btn.setOnClickListener(this);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                String str = bundle.getString("data");
                show.setText(str);
            }
        };
    }

    @Override
    public void onClick(View v) {
        new Thread(){
            @Override
            public void run() {
//                handmessage
                int count=0;
                while(true){
                    count++;
                    if(count>10){
                        count=0;
                    }else{
                        Message msg = handler.obtainMessage();
                        Bundle bundle = new Bundle();
                        bundle.putString("data",String.valueOf(count));
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
//                for (int i=0;i<10;i++){
//                    Message msg=new Message();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("data", String.valueOf(i));
//                    msg.setData(bundle);
//                    handler.sendMessage(msg);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }

//                UIThread
//                for (int i=0;i<10;i++){
//                    final int finalI = i;
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            show.setText("显示为："+ finalI);
//                        }
//                    });
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                handlerThread

//                for(int i=0;i<10;i++){
//                    final int finalI = i;
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            show.setText("handlerThread:"+ finalI);
//                        }
//                    });
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }.start();

    }
}
