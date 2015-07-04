package thoughtworks.academy.myandroiddemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGetHC4;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class SimpleListViewActivity extends Activity {

    private String[] data = {"One", "Two", "Three", "Four", "Five",
            "Six", "Seven", "Eight", "Nine", "Ten"};

    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list_view);



        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String[] result = (String[]) msg.obj;
                Log.i("result", result.toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        SimpleListViewActivity.this, android.R.layout.simple_list_item_1, result);

                ListView listView = (ListView) findViewById(R.id.list_view);
                listView.setAdapter(adapter);
            }
        };


        new Thread() {
            @Override
            public void run() {
                super.run();
                loadData();
            }
        }.start();
    }

    private void loadData() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "http://192.168.1.4:3000/";
        HttpGetHC4 httpGet = new HttpGetHC4(url);
        CloseableHttpResponse response = null;
        Gson gson = new Gson();
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            Log.i("entity", entity.toString());
            if(null != entity) {
                BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
                String line = null;
                String result = "";
                while(null != (line = br.readLine())) {
                    result += line;
                }
                String[] floatArr = gson.fromJson(result, String[].class);
                Message message = new Message();
                message.obj = floatArr;
                handler.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
