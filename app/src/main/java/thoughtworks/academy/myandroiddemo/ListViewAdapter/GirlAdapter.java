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

    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

    private int resourceId;

    public GirlAdapter(Context context, int textViewResourceId,
                       List<Girl> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Girl girl = getItem(position);

        View view;
        ViewHolder viewHolder;

        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.girl_image);
            viewHolder.textView = (TextView) view.findViewById(R.id.girl_text);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.imageView.setImageResource(girl.getImageId());
        viewHolder.textView.setText(girl.getName());
        System.out.println(girl.getName());
        return view;
    }
}
