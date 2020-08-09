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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import kr.ac.jbnu.se.doview.java.helloar.R;
import kr.ac.jbnu.se.doview.java.model.GlobalStorage;
import kr.ac.jbnu.se.doview.java.view.AssetListAdapter;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ListView mainAssetListView;
    private AssetListAdapter assetListAdapter;

    Button btnNickName, btnLogOut;
    View dialogView;
    TextView textNickName;
    EditText editNickName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // splash start
        Intent splashIntent = new Intent(this, SplashActivity.class);
        startActivity(splashIntent);
        // splash end

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

                String[] checkObjString = GlobalStorage.arDataHashMap.get(assetName);
                assert checkObjString != null;

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

        btnLogOut = (Button) findViewById(R.id.main_drawer_logout_btn);
        btnNickName = (Button) findViewById(R.id.main_drawer_name_btn);

        textNickName = (TextView) findViewById(R.id.main_drawer_user_name);

        btnNickName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogView = (View) View.inflate(MainActivity.this,
                        R.layout.dialog_nickname, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(
                        MainActivity.this);
                dlg.setTitle("닉네임 설정");
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                editNickName = (EditText) dialogView
                                        .findViewById(R.id.editNickName);

                                textNickName.setText(editNickName.getText().toString());

                            }
                        });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dlg.show();
            }});

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent I = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(I);
                finish();
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