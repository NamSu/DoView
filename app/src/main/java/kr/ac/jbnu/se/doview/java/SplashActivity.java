package kr.ac.jbnu.se.doview.java;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import kr.ac.jbnu.se.doview.java.R;
import kr.ac.jbnu.se.doview.java.model.GlobalStorage;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String[] test1 = new String[2];
        test1[0] = "models/andy.obj";
        test1[1] = "models/andy.png";

        String[] test2 = new String[2];
        test2[0] = "models/anchor.obj";
        test2[1] = "models/anchor.png";

        String[] test3 = new String[2];
        test3[0] = "helloworld.gltf";
        test3[1] = "";

        GlobalStorage.arDataHashMap.put("andy", test1);
        GlobalStorage.arDataHashMap.put("anchor", test2);
        GlobalStorage.arDataHashMap.put("helloworld", test3);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }
}
