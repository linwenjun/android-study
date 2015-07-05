package thoughtworks.academy.myandroiddemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import thoughtworks.academy.myandroiddemo.ListViewAdapter.NetworkImageActivity;


public class WelcomeActivity extends Activity implements View.OnClickListener {

    Button toastButton = null;
    Button loginButton = null;
    Button signInButton = null;
    Button openBaiduButton = null;
    Button dialButton = null;
    Button sendDataButton = null;
    EditText sendDataText = null;
    Button layoutButton = null;
    Button receiveDataButton = null;
    Button touchBallButton = null;
    Button networkImageButton = null;
    Button stackViewButton = null;

    TextView receiveDataText = null;

    NumberAdderLayout numberAdderLayout = null;

    NetWorkChangeReceiver netWorkChangeReceiver = null;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        toastButton = (Button) findViewById(R.id.button);
        loginButton = (Button) findViewById(R.id.login_button);
        signInButton = (Button) findViewById(R.id.sign_in_button);
        openBaiduButton = (Button) findViewById(R.id.open_baidu_button);
        dialButton = (Button) findViewById(R.id.dial_button);
        sendDataButton = (Button) findViewById(R.id.send_data_button);
        sendDataText = (EditText) findViewById(R.id.send_data_text);
        receiveDataButton = (Button) findViewById(R.id.receive_data_button);
        receiveDataText = (TextView) findViewById(R.id.receive_data_text);
        layoutButton = (Button) findViewById(R.id.layout_button);
        touchBallButton = (Button) findViewById(R.id.touch_ball_button);
        networkImageButton = (Button) findViewById(R.id.network_image_button);
        stackViewButton = (Button) findViewById(R.id.stack_view_button);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netWorkChangeReceiver = new NetWorkChangeReceiver();
        registerReceiver(netWorkChangeReceiver, intentFilter);

        registClickEvent();
        registCustomCallBack();
    }

    private void registCustomCallBack() {
        numberAdderLayout = (NumberAdderLayout) findViewById(R.id.number_adder);
        numberAdderLayout.setCallBack(new NumberAdderLayout.ICallback() {
            @Override
            public void onNumberChange(int num) {
                Toast.makeText(getApplicationContext(), num + "", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void registClickEvent() {
        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "You Click The Button " + (count++) + " Times", Toast.LENGTH_SHORT).show();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SimpleListViewActivity.class);
                startActivity(intent);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.GIRL_LIST_VIEW");
                startActivity(intent);
            }
        });

        openBaiduButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

        dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });

        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, ReceiveDataActivity.class);
                intent.putExtra("value", sendDataText.getText().toString());
                startActivity(intent);
            }
        });

        receiveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReturnResultActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        layoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LayoutActivity.class);
                startActivity(intent);
            }
        });

        touchBallButton.setOnClickListener(this);
        networkImageButton.setOnClickListener(this);
        stackViewButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up toastButton, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                finish();
                break;
            case R.id.action_login:
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
                break;
            default:
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                receiveDataText.setText(data.getStringExtra("value"));
                break;
            default:
        }
    }


    @Override
    public void onClick(View view) {
        System.out.println(view);

        Intent intent;
        switch (view.getId()) {
            case R.id.touch_ball_button:
                intent = new Intent(WelcomeActivity.this, TouchBallActivity.class);
                startActivity(intent);
                break;
            case R.id.network_image_button:
                intent = new Intent(WelcomeActivity.this, NetworkImageActivity.class);
                startActivity(intent);
                break;
            case R.id.stack_view_button:
                intent = new Intent(WelcomeActivity.this, StackViewActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netWorkChangeReceiver);
    }

    class NetWorkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            Toast.makeText(getApplicationContext(), "网络情况有变哦", Toast.LENGTH_SHORT).show();
        }
    }
}
