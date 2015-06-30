package thoughtworks.academy.myandroiddemo;

import android.content.Context;
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
    TextView number = null;
    ICallback iCallback = null;

    public NumberAdderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.number_adder, this);

        addButton = (Button) findViewById(R.id.add_button);
        number = (TextView) findViewById(R.id.number);

        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentValue = getNumber();
                number.setText((++currentValue) + "");

                iCallback.onNumberChange(currentValue);
            }
        });
    }

    public void setCallBack(ICallback callBack) {
        iCallback = callBack;
    }

    private int getNumber() {
        return Integer.parseInt(number.getText().toString());
    }
}
