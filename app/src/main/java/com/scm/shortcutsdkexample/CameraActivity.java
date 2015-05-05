package com.scm.shortcutsdkexample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.scm.reader.livescanner.sdk.KEvent;
import com.scm.reader.livescanner.search.Search;
import com.scm.reader.livescanner.ui.CameraView;
import com.scm.reader.livescanner.ui.ShortcutSearchView;
import com.scm.reader.resultPage.ui.ItemViewActivity;

    public class CameraActivity extends Activity
            implements ShortcutSearchView.RecognitionCallbacks {

        private CameraView mCameraView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mCameraView = new CameraView(this);
            mCameraView.setChangeCameraModeCallback(new ShortcutSearchView.ChangeCameraModeCallback() {
                @Override
                public void onChangeCameraMode() {
                    Intent i = new Intent(CameraActivity.this, ScannerActivity.class);
                    startActivity(i);
                    finish();
                }
            });
            mCameraView.onCreate(savedInstanceState);
        }

    @Override
    protected void onStart() {
        super.onStart();
        mCameraView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCameraView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCameraView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCameraView.onDestroy();
    }

    @Override
    public void onImageRecognized(KEvent event) {
        Search result = event.getSearch();
        Log.d("ScanActivity", "Image recognized. Result title = " +
                result.getTitle());

        Intent i = new Intent(this, ItemViewActivity.class);
        i.setData(Uri.parse(result.getUrl()));
        startActivity(i);
    }

    @Override
    public void onImageNotRecognized(KEvent event) {
    }

}
