package thoughtworks.academy.myandroiddemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.StackView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StackViewActivity extends Activity {

    StackView stackView = null;
    Button nextButton = null;
    int[] imageIds = new int[]{
            R.drawable.calculator, R.drawable.camera, R.drawable.chrome, R.drawable.drive
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack_view);

        stackView = (StackView) findViewById(R.id.mStackView);
        nextButton = (Button) findViewById(R.id.next_button);

        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < imageIds.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("image", imageIds[i]);
            listItems.add(listItem);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                listItems, R.layout.cell, new String[]{"image"}, new int[]{R.id.image});

        stackView.setAdapter(simpleAdapter);
    }
}
