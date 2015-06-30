package thoughtworks.academy.myandroiddemo.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import thoughtworks.academy.myandroiddemo.ListViewModel.Girl;
import thoughtworks.academy.myandroiddemo.R;

public class GirlAdapter extends ArrayAdapter<Girl> {

    private int resourceId;

    public GirlAdapter(Context context, int textViewResourceId,
                       List<Girl> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Girl girl = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView girlImage = (ImageView) view.findViewById(R.id.girl_image);
        TextView girlText = (TextView) view.findViewById(R.id.girl_text);
        System.out.println(girl.getName());
        girlImage.setImageResource(girl.getImageId());
        girlText.setText(girl.getName());
        return view;
    }
}
