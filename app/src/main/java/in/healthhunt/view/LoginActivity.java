package in.healthhunt.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.model.Constants;
import in.healthhunt.presenter.ILoginPresenter;
import in.healthhunt.presenter.ILoginView;
import in.healthhunt.presenter.LoginInteractorImpl;
import in.healthhunt.presenter.LoginPresenterImpl;
import in.healthhunt.presenter.facebook.Facebook;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView{

    private final String TAG = LoginActivity.class.getSimpleName();
    private ILoginPresenter IPresenter;
    private ProgressDialog mProgress;


    private Map<String,Fragment> fragmentMap = new HashMap<String, Fragment>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mProgress = new ProgressDialog(this);
        mProgress.setIndeterminate(true);
        mProgress.setMessage(getResources().getString(R.string.please_wait));

        ButterKnife.bind(this);

        IPresenter = new LoginPresenterImpl(this, new LoginInteractorImpl());
        IPresenter.loadFragment(LoginFragment.class.getSimpleName());

    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    public ILoginPresenter getPresenter() {
        return IPresenter;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onShowProgress() {
        if(mProgress != null) {
            mProgress.show();
        }
    }

    @Override
    public void onHideProgress() {
        if(mProgress != null) {
            mProgress.hide();
        }
    }

    @Override
    public void onEmailError() {

    }

    @Override
    public void onPasswordError() {

    }

    @Override
    public void showFragment(String tag) {
        Fragment fragment = fragmentMap.get(tag);

        if(fragment == null) {
            if (tag != null && tag.equals(LoginFragment.class.getSimpleName())) {
                fragment = new LoginFragment();
            } else if (tag != null && tag.equals(SignUpFragment.class.getSimpleName())) {
                fragment = new SignUpFragment();
            } else if (tag != null && tag.equals(ForgotPasswordFragment.class.getSimpleName())) {
                fragment = new ForgotPasswordFragment();
            }
            fragmentMap.put(tag, fragment);
        }
        loadFragment(fragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.GMAIL_REQUEST_CODE) {
            // [START get_auth_code]
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                String authCode = account.getServerAuthCode();

                Log.d(TAG, "AuthCode = " + authCode);
                // TODO(developer): send code to server and exchange for access/refresh/ID tokens
            } catch (ApiException e) {
                Log.d(TAG, "Sign-in failed", e);
            }
        }
            else {
                Facebook.getInstance(getApplicationContext()).getCallbackManager().onActivityResult(requestCode, resultCode, data);
            }
            // [END get_auth_code]
    }
}
