package thoughtworks.academy.myandroiddemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

    private Button startButton = null;
    private TextView textView = null;
    private ImageView imageView = null;
    private MyHandler handler = new MyHandler();
    private Handler mHandler = new Handler();
    private int images[] = new int[]{R.drawable.image001, R.drawable.image002};
    private int index;
    private MyRunnable myRunnable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        startButton = (Button) findViewById(R.id.start_button);
        imageView = (ImageView) findViewById(R.id.imageView);

//        imageView.setImageResource();

        myRunnable = new MyRunnable();
//        myRunnable.run();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mHandler.removeCallbacks(myRunnable);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.post(myRunnable);
                        System.out.println("The Click Thread Id");
                        System.out.println(Thread.currentThread().getId());
                        handler.sendEmptyMessage(3);
                    }
                }).start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            System.out.println(msg.what);
            System.out.println("The Handle Message Thread Id Is");
            System.out.println(Thread.currentThread().getId());
        }
    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            int idx = index++ % 2;
            imageView.setImageResource(images[idx]);
            mHandler.postDelayed(myRunnable, 1000);
            System.out.println(Thread.currentThread().getId());
        }
    }
}
