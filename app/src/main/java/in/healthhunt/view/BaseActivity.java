package in.healthhunt.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import in.healthhunt.R;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class BaseActivity extends AppCompatActivity {

    public ProgressDialog mProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgress = new ProgressDialog(this);
        mProgress.setIndeterminate(true);
        mProgress.setCancelable(false);
        mProgress.setMessage(getResources().getString(R.string.please_wait));

    }
}
