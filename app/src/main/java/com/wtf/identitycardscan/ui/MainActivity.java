package com.wtf.identitycardscan.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wtf.identitycardscan.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_scan).setOnClickListener(this);
        findViewById(R.id.btn_history).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scan:
                gotoTakePic();
                break;
            case R.id.btn_history:

                break;
        }
    }

    private void gotoTakePic() {
        Intent intent = new Intent(this, ScanMainActivity.class);
        startActivity(intent);
    }
}
