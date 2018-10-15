package com.wtf.identitycardscan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Setting;

import java.util.List;

import com.wtf.identitycardscan.R;

public class ScanMainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int TAKE_PIC = 1;

    private Button btnFront;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_main);

        btnFront = findViewById(R.id.btn_front);
        btnBack = findViewById(R.id.btn_back);

        btnFront.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_front:
                tryTakePic(true);
                break;
            case R.id.btn_back:
                tryTakePic(false);
                break;
        }
    }

    private void tryTakePic(boolean isFront) {
        checkPermission(isFront, false);
    }

    /** http://www.yanzhenjie.com/AndPermission/cn/runtime/ **/
    private void checkPermission(final boolean isFront, final boolean isBackFromSetting) {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.CAMERA, Permission.Group.STORAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        //
                        gotoTakePicPage(isFront);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (isBackFromSetting) {
                            Toast.makeText(ScanMainActivity.this, "请开启摄像头权限和储存权限", Toast.LENGTH_SHORT).show();
                        } else {
                            gotoSettingPage(isFront);
                        }
                    }
                })
                .start();
    }

    private void gotoTakePicPage(boolean isFront) {
        Intent intent = new Intent(this, ScanActivity.class);
        intent.putExtra(ScanActivity.IS_TAKE_FRONT_PIC, isFront);
        startActivityForResult(intent, TAKE_PIC);
    }

    private void gotoSettingPage(final boolean isFront) {
        AndPermission.with(this)
                .runtime()
                .setting()
                .onComeback(new Setting.Action() {
                    @Override
                    public void onAction() {
                        // 用户从设置回来了。
                        checkPermission(isFront, true);
                    }
                })
                .start();
    }
}
