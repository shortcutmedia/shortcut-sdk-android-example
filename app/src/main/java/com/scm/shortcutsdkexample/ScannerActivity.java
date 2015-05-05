package com.scm.shortcutsdkexample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.scm.reader.livescanner.sdk.KEvent;
import com.scm.reader.livescanner.search.Search;
import com.scm.reader.livescanner.ui.ScannerView;
import com.scm.reader.livescanner.ui.ShortcutSearchView;
import com.scm.reader.resultPage.ui.ItemViewActivity;


public class ScannerActivity extends Activity
    implements ShortcutSearchView.RecognitionCallbacks {

    private ScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScannerView = new ScannerView(this);
        mScannerView.setChangeCameraModeCallback(new ShortcutSearchView.ChangeCameraModeCallback() {
            @Override
            public void onChangeCameraMode() {
                Intent i = new Intent(ScannerActivity.this, CameraActivity.class);
                startActivity(i);
                finish();
            }
        });
        mScannerView.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mScannerView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mScannerView.onStop();
    }

    public void onImageRecognized(KEvent event) {
        Search result = event.getSearch();
        Log.d("ScanActivity", "Image recognized. Result title = " + result.getTitle());

        Intent i = new Intent(this, ItemViewActivity.class);
        i.setData(Uri.parse(result.getUrl()));
        startActivity(i);
    }

    public void onImageNotRecognized(KEvent event) {
        Log.d("ScanActivity", "image not recognized");
    }
}
