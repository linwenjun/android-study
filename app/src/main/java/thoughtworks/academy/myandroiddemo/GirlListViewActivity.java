package thoughtworks.academy.myandroiddemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import thoughtworks.academy.myandroiddemo.ListViewAdapter.GirlAdapter;
import thoughtworks.academy.myandroiddemo.ListViewModel.Girl;


public class GirlListViewActivity extends Activity {

    private List<Girl> girlList = new ArrayList<Girl>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl_list_view);

        initData();

        GirlAdapter girlAdapter = new GirlAdapter(GirlListViewActivity.this,
                R.layout.girl_item, girlList);

        ListView listView = (ListView) findViewById(R.id.girl_list_view);
        listView.setAdapter(girlAdapter);
    }

    private void initData() {
        girlList.add(new Girl("one", R.drawable.image001));
        girlList.add(new Girl("two", R.drawable.image002));
        girlList.add(new Girl("Three", R.drawable.image001));
        girlList.add(new Girl("four", R.drawable.image002));
        girlList.add(new Girl("five", R.drawable.image001));
        girlList.add(new Girl("one", R.drawable.image001));
        girlList.add(new Girl("two", R.drawable.image002));
        girlList.add(new Girl("Three", R.drawable.image001));
        girlList.add(new Girl("four", R.drawable.image002));
        girlList.add(new Girl("five", R.drawable.image001));
    }
}
