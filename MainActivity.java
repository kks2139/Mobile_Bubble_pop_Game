package com.example.bbokbbok;

import android.app.Activity;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quit = (Button)findViewById(R.id.quit);

        quit.setOnClickListener(new View.OnClickListener() {// 눌렀을때 앱이 종료되는 버튼
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
                Toast.makeText(MainActivity.this, "Test !", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
