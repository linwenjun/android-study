package thoughtworks.academy.myandroiddemo.ListViewAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import thoughtworks.academy.myandroiddemo.R;
import thoughtworks.academy.myandroiddemo.views.UrlImageView;

public class NetworkImageActivity extends Activity {

    UrlImageView urlImage = null;
    Button nextButton = null;

    String[] imageSrcList = {
            "http://www.hers.cn/uploadfile/2011/1203/43c4bc4dad841f970a088825f5a71a3e.jpg",
            "http://www.lznews.cn/uploadfile/2011/0523/20110523110657804.jpg",
            "http://img21.mtime.cn/mg/2010/05/24/181835.75899679.jpg",
            "http://ent.hangzhou.com.cn/gdxw/images/attachement/jpg/site2/20101206/001ec947cdc60e6691f804.jpg"
    };

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_image);
        nextButton = (Button) findViewById(R.id.next_button);
        urlImage = (UrlImageView) findViewById(R.id.url_image);

        nextImage();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextImage();
            }
        });
    }

    private void nextImage() {
        int imgIndex = index++ % imageSrcList.length;
        urlImage.setSrc(imageSrcList[imgIndex]);
    }
}
