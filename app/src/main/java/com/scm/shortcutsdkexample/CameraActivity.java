package com.scm.shortcutsdkexample;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.scm.reader.livescanner.sdk.KEvent;
import com.scm.reader.livescanner.search.Search;
import com.scm.reader.livescanner.ui.CameraView;
import com.scm.reader.livescanner.ui.ShortcutSearchView;
import com.scm.reader.resultPage.ui.ItemViewActivity;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;
import shortcutsdkexample.scm.com.shortcutsdkdemo.R;

public class CameraActivity extends AppCompatActivity implements ShortcutSearchView.RecognitionCallbacks {
    private final int PERMISSION_REQUEST_CODE = 992;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            init();
        } else {
            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, PERMISSION_REQUEST_CODE, Manifest.permission.CAMERA)
                            .setRationale(R.string.camera_rationale)
                            .setPositiveButtonText(R.string.rationale_ask_ok)
                            .setNegativeButtonText(R.string.rationale_ask_cancel)
                            .build());
        }
    }

    @AfterPermissionGranted(PERMISSION_REQUEST_CODE)
    private void permissionsGiven() {
        init();
    }

    private void init() {
        CameraView cameraView = new CameraView(this);
        cameraView.setRecognitionCallbacks(this);
        cameraView.setChangeCameraModeCallback(new ShortcutSearchView.ChangeCameraModeCallback() {
            @Override
            public void onChangeCameraMode() {
                startActivity(new Intent(CameraActivity.this, ScannerActivity.class));
            }
        });
        getLifecycle().addObserver(cameraView);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onImageRecognized(KEvent event) {
        Search result = event.getSearch();
        Intent i = new Intent(this, ItemViewActivity.class);
        i.setData(Uri.parse(result.getUrl()));
        startActivity(i);
    }

    @Override
    public void onImageNotRecognized(KEvent event) {
        Toast.makeText(this, R.string.no_match, Toast.LENGTH_SHORT).show();
    }
}
