package thoughtworks.academy.myandroiddemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ForAsyncTaskActivity extends Activity {

    Button startButton = null;
    TextView resultView = null;
    MyAsyncTask myAsyncTask = new MyAsyncTask();

    class MyAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... integers) {
            for(int i=0; i<1000; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(i);
            }
            return 1000 + "";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
            int value = values[0];
            resultView.setText(value + "");
        }

        @Override
        protected void onPostExecute(String s) {
            resultView.setText("end");
        }

        @Override
        protected void onPreExecute() {
            resultView.setText("Starting");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_async_task);

        startButton = (Button) findViewById(R.id.start_button);
        resultView = (TextView) findViewById(R.id.result);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAsyncTask.execute(10);
            }
        });

    }
}
