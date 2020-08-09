package kr.ac.jbnu.se.doview.java;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import kr.ac.jbnu.se.doview.java.helloar.R;
import kr.ac.jbnu.se.doview.java.model.GlobalStorage;
import kr.ac.jbnu.se.doview.java.view.AssetListAdapter;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ListView mainAssetListView;
    private AssetListAdapter assetListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // splash start
        Intent splashIntent = new Intent(this, SplashActivity.class);
        startActivity(splashIntent);
        // splash end

        // bogun-e

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "-----" + GlobalStorage.arDataHashMap.size());

        mainAssetListView = (ListView)findViewById(R.id.main_list_listview);
        assetListAdapter = new AssetListAdapter(this);
        mainAssetListView.setAdapter(assetListAdapter);

        mainAssetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String assetName = assetListAdapter.getAssetArrayString(position);

                Log.d(TAG, "------" + assetListAdapter.getAssetArrayString(position));

                String[] checkObjString = GlobalStorage.arDataHashMap.get(assetName);
                assert checkObjString != null;

                Log.d(TAG, "------0 "+ checkObjString[0]);

                if (checkObjString[0].contains("obj")) { // obj
                    GlobalStorage.isModelObj = true;

                    if (ARActivity.insertAsset(assetName)) {
                        Intent intent = new Intent(getApplicationContext(), ARActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Asset 로드에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    GlobalStorage.isModelObj = false;

                    if (ARActivity.insertAsset(assetName)) {
                        Intent intent = new Intent(getApplicationContext(), ARActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Asset 로드에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                assetListAdapter.notifyDataSetChanged();
            }
        }, 5000);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("DoView 종료");
        builder.setMessage("정말로 DoView를 종료 하시겠습니까?");
        builder.setCancelable(false);

        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }
}
