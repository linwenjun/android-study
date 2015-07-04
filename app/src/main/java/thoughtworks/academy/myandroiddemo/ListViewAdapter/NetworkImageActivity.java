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
                Double bitmapRatio = 1.0 * bm.getWidth() / bm.getHeight();
                Double imageViewRatio = 1.0 * image.getWidth() / image.getHeight();
                Log.i("Bitmap Ratio", bm.getWidth() + "");
                Log.i("ImageView Height", bm.getHeight() + "");
                int startX = 0;
                int startY = 0;
                int width = bm.getWidth();
                int height = bm.getHeight();

                if(bitmapRatio > imageViewRatio) {
                    startX = (int) ((bm.getWidth() - bm.getHeight() * imageViewRatio) / 2);
                    width = width - startX * 2;
                } else {
                    startY = (int) ((bm.getHeight() - bm.getWidth() / imageViewRatio) / 2);
                    height = height - startY * 2;
                }

                image.setImageBitmap(Bitmap.createBitmap(bm, startX, startY, width , height));
            }
        };

        new Thread() {
            @Override
            public void run() {
                Bitmap bm = getBitMapFromUrl("http://img2.iqilu.com/ed/10/11/05/93/49_101105123647_1.jpg");
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
