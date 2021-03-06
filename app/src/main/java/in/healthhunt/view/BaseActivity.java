package in.healthhunt.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import framework.permisisons.PermissionActivity;
import in.healthhunt.R;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class BaseActivity extends PermissionActivity {

    public ProgressDialog mProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgress = new ProgressDialog(this);
        mProgress.setIndeterminate(true);
        mProgress.setCancelable(false);
        mProgress.setMessage(getResources().getString(R.string.please_wait));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mProgress != null) {
            mProgress.dismiss();
        }
    }

    @Override
    public void setPermission(int requestCode, boolean isGranted) {
    }
}
