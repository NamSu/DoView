package kr.ac.jbnu.se.doview.java.view;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import kr.ac.jbnu.se.doview.java.helloar.R;
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
        Log.d(TAG, "-----" + GlobalStorage.arDataHashMap.size());
        assetArray.clear();
        assetArray = new ArrayList<String>(GlobalStorage.arDataHashMap.keySet());
        super.notifyDataSetChanged();
    }

    public AssetListAdapter(Context context) {
        Log.d(TAG, "-----" + GlobalStorage.arDataHashMap.size());
        this.mContext = context;
    }

    @Override
    public int getCount() {
        //return 100;
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
        TextView textView = new TextView(mContext);
        textView.setText(assetArray.get(position));
        //textView.setText(String.valueOf(position));
        textView.setTextColor(Color.parseColor("#000000"));

        return textView;
    }
}
