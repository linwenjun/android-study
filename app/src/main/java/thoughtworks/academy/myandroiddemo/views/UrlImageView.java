package thoughtworks.academy.myandroiddemo.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlImageView extends ImageView {

    private Handler handler;
    private String srcStr;

    public UrlImageView(Context context) {
        super(context);
    }

    public UrlImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSrc(String src) {

        final ImageView imageView = this;
        srcStr = src;

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bitmap bm = (Bitmap) msg.obj;
                if(null == bm) {
                    return;
                }
                Double bitmapRatio = 1.0 * bm.getWidth() / bm.getHeight();
                Double imageViewRatio = 1.0 * imageView.getWidth() / imageView.getHeight();
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

                imageView.setImageBitmap(Bitmap.createBitmap(bm, startX, startY, width , height));
            }
        };

        new Thread() {
            @Override
            public void run() {
                Bitmap bm = getBitMapFromUrl(srcStr);
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
