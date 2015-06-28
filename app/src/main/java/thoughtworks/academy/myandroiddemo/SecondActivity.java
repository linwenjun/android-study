package thoughtworks.academy.myandroiddemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SecondActivity extends Activity {

    private Button button;
    private TextView textView;
    private MyThread myThread;
    private Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.title_text_view);

        myThread = new MyThread();
        myThread.start();
        try {
            myThread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myThread.handler.sendEmptyMessage(1);
            }
        });

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                textView.setText(msg.what + "");
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class MyThread extends Thread {
        public Handler handler;
        private int index;
        @Override
        public void run() {
            Looper.prepare();
            handler = new Handler() {
                @Override

                public void handleMessage(Message msg) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread());
                    System.out.println(index++);
                    mHandler.sendEmptyMessage(index);
                }
            };

            Looper.loop();
        }
    }
}
