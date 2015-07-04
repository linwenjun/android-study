package thoughtworks.academy.myandroiddemo.ListViewAdapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import thoughtworks.academy.myandroiddemo.R;

public class NetworkImageActivity extends Activity {

    ImageView image = null;
    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_image);

        image = (ImageView) findViewById(R.id.network_image);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bitmap bm = (Bitmap) msg.obj;
                Log.i("height", bm.getHeight() + "");
                Log.i("width", bm.getWidth() + "");

                image.setImageBitmap(bm);
            }
        };

        new Thread() {
            @Override
            public void run() {
                Bitmap bm = getBitMapFromUrl("http://npic7.edushi.com/cn/zixun/zh-chs/2015-07/03/2015070312022028.jpg");
                Message message = new Message();
                message.obj = bm;
                handler.sendMessage(message);
            }
        }.start();
    }

    private Bitmap getBitMapFromUrl(String src) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();

            InputStream is = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
