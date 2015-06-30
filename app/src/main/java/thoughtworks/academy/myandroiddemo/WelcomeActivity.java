package thoughtworks.academy.myandroiddemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


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
    TextView receiveDataText = null;

    NumberAdderLayout numberAdderLayout = null;

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

        numberAdderLayout = (NumberAdderLayout) findViewById(R.id.number_adder);

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
                Intent intent = new Intent("android.intent.action.SIGN_IN");
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

        numberAdderLayout.setCallBack(new NumberAdderLayout.ICallback() {
            @Override
            public void onNumberChange(int num) {
                Toast.makeText(getApplicationContext(), num + "", Toast.LENGTH_LONG).show();
            }
        });
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
        switch(requestCode) {
            case 1:
                receiveDataText.setText(data.getStringExtra("value"));
                break;
            default:
        }
    }


    @Override
    public void onClick(View view) {
        System.out.println(view);
        switch(view.getId()) {
            case R.id.layout_button:
                Intent intent = new Intent(WelcomeActivity.this, LayoutActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
