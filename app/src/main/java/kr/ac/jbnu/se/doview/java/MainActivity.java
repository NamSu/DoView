package kr.ac.jbnu.se.doview.java;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import kr.ac.jbnu.se.doview.java.helloar.R;
import kr.ac.jbnu.se.doview.java.model.GlobalStorage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.test_button);

        String[] testString = new String[2];
        testString[0] = "models/anchor.obj";
        testString[1] = "models/anchor.png";

        GlobalStorage.arDataHashMap.put("test", testString);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ARActivity.class);
                startActivity(intent);
            }
        });
    }
}
