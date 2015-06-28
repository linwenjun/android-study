package thoughtworks.academy.myandroiddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;


public class ReturnResultActivity extends Activity {

    TextView returnText = null;

    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_result);

        returnText = (TextView) findViewById(R.id.return_data);
        Random random = new Random();
        value = random.nextDouble() + "";
        returnText.setText("本页所生成数据是:" + value);
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra("value", value);
        setResult(RESULT_OK, intent);
        finish();
    }
}
