package thoughtworks.academy.myandroiddemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import thoughtworks.academy.myandroiddemo.Util.HttpGetWork;


public class NetworkActivity extends Activity {

    Button startButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        startButton = (Button) findViewById(R.id.load_button);
        HandlerThread handlerThread = new HandlerThread("ht");
        handlerThread.start();
        final Handler handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                try {
                    System.out.println(HttpGetWork.loadBaidu());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(0);
            }
        });
    }

}
