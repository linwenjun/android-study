package thoughtworks.academy.myandroiddemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NumberAdderLayout extends LinearLayout{

    interface ICallback {
        public void onNumberChange(int num);
    }

    Button addButton = null;
    TextView numberText = null;
    ICallback callback = null;
    int number;

    Handler handler = null;

    public NumberAdderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.number_adder, this);

        addButton = (Button) findViewById(R.id.add_button);
        numberText = (TextView) findViewById(R.id.number);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                callback.onNumberChange(message.what);
                return false;
            }
        });

        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                number = getNumber();
                number++;
                numberText.setText(number + "");

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        handler.sendEmptyMessage(number);
                    }
                }.start();

            }
        });
    }

    public void setCallBack(ICallback callBack) {
        callback = callBack;
    }

    private int getNumber() {
        return Integer.parseInt(numberText.getText().toString());
    }
}
