package thoughtworks.academy.myandroiddemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NumberAdderLayout extends LinearLayout{

    Button addButton = null;
    TextView number = null;

    public NumberAdderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.number_adder, this);

        addButton = (Button) findViewById(R.id.add_button);
        number = (TextView) findViewById(R.id.number);

        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentValue = Integer.parseInt(number.getText().toString());
                System.out.println(currentValue);
                number.setText((++currentValue) + "");
            }
        });
    }

    public int getNumber() {
        return Integer.parseInt(number.getText().toString());
    }
}
