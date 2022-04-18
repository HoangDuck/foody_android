package hcmute.edu.vn.foody_08;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TemAdapter extends BaseAdapter {
    private Activity context;
    private int layout;
    private List listhucan;

    public TemAdapter(Activity context, int layout, List listhucan) {
        this.context = context;
        this.layout = layout;
        this.listhucan = listhucan;
    }

    public TemAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return listhucan.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            view.setTag(holder);
        }
        else {
            holder =(ViewHolder) view.getTag();
        }
        return view;
    }

    private  class ViewHolder{
        TextView textTen;
        ImageView imgEdit,imgDelete;

    }
}
