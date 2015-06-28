package thoughtworks.academy.myandroiddemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import thoughtworks.academy.myandroiddemo.Util.HttpGetWork;


public class AsyncTaskActivity extends Activity {

    private Button startButton;
    private TextView resultView;

    class MyAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... integers) {
            String result = null;

            try {
                result = HttpGetWork.loadBaidu();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("=============================");
            System.out.println(Thread.currentThread());

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            System.out.println(s);
            System.out.println("=============================");
            System.out.println(Thread.currentThread());
            resultView.setText("获取结果成功: 长度为" + s.length());
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        startButton = (Button) findViewById(R.id.start_button);
        resultView = (TextView) findViewById(R.id.textView);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute(10);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_async_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up toastButton, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
