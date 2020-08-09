package kr.ac.jbnu.se.doview.java.view;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import kr.ac.jbnu.se.doview.java.R;
import kr.ac.jbnu.se.doview.java.model.GlobalStorage;

public class AssetListAdapter extends BaseAdapter {
    private static final String TAG = "AssetListAdapter";

    private Context mContext;

    private ArrayList<String> assetArray = new ArrayList<String>(GlobalStorage.arDataHashMap.keySet());


    public String getAssetArrayString(int position) {
        /*ArrayList<String> reverseArray = assetArray;
        Collections.reverse(reverseArray);
        return reverseArray.get(position);*/
        return assetArray.get(position);
    }

    @Override
    public void notifyDataSetChanged() {
        assetArray.clear();
        assetArray = new ArrayList<String>(GlobalStorage.arDataHashMap.keySet());
        super.notifyDataSetChanged();
    }

    public AssetListAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return assetArray.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.item_asset_list, null);

            ViewHolder assetHolder = new ViewHolder();
            assetHolder.assetItemName = (TextView)view.findViewById(R.id.asset_item_name);
            assetHolder.assetItemCreateDate = (TextView)view.findViewById(R.id.asset_item_create_date);
            assetHolder.assetItemObjectType = (TextView)view.findViewById(R.id.asset_item_object_style);

            view.setTag(assetHolder);
        }

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        // name, time, obj searcher
        ViewHolder viewHolder = (ViewHolder)view.getTag();
        viewHolder.assetItemName.setText(assetArray.get(position));
        viewHolder.assetItemCreateDate.setText(timeStamp);

        try {
            String[] assetObjCheck = GlobalStorage.arDataHashMap.get(assetArray.get(position));

            assert assetObjCheck != null;
            if (assetObjCheck[1].contains("png")) { // check to obj
                viewHolder.assetItemObjectType.setText("Obj File");
            } else {
                viewHolder.assetItemObjectType.setText("GLTF File");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return view;
    }

    private static class ViewHolder {
        TextView assetItemName;
        TextView assetItemCreateDate;
        TextView assetItemObjectType;
    }
}
