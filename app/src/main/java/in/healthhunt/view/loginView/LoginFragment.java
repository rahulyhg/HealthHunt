package in.healthhunt.view.loginView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.healthhunt.R;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.presenter.loginPresenter.ILoginPresenter;
import in.healthhunt.presenter.preference.HealthHuntPreference;
import in.healthhunt.view.socialLogin.GoogleLoginActivity;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public class LoginFragment extends Fragment{
    private final String TAG = LoginFragment.class.getSimpleName();
    @BindView(R.id.login)
    Button mLogin;

    @BindView(R.id.sign_up)
    TextView mSignUp;

    @BindView(R.id.forgot_password)
    TextView mForgot_Password;

    @BindView(R.id.facebook)
    ImageButton mFacebook;

    @BindView(R.id.gmail)
    ImageButton mGmail;

    @BindView(R.id.email)
    EditText mEmail;

    @BindView(R.id.password)
    EditText mPassword;

    private ILoginPresenter IPresenter;
    private Unbinder unbinder;
    private int isLoginType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container,  false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        IPresenter = ((LoginActivity) context).getPresenter();
    }

    @OnClick(R.id.login)
    void onLogin() {
        isLoginType = LoginActivity.LOGIN_TYPE_NORMAL;
        IPresenter.validateCredentialsLogIn(mEmail.getText().toString(), mPassword.getText().toString());
        //startActivity(new Intent(getActivity(), HomeActivity.class));
    }

    @OnClick(R.id.sign_up)
    void onSignUp() {
        IPresenter.loadFragment(SignUpFragment.class.getSimpleName(), null);
    }

    @OnClick(R.id.forgot_password)
    void onForgotPassword(){
        IPresenter.forgotPassword(mEmail.getText().toString(), mEmail.getText().toString());
        /*Bundle bundle = new Bundle();
        bundle.putString(Constants.EMAIL, mEmail.getText().toString());
        IPresenter.loadFragment(ForgotPasswordFragment.class.getSimpleName(), bundle);*/
    }

    @OnClick(R.id.facebook)
    void onFacebook() {
        isLoginType = LoginActivity.LOGIN_TYPE_FACEBOOK;
        IPresenter.loginFacebook(getContext());
    }

    @OnClick(R.id.gmail)
    void onGmail() {
        isLoginType = LoginActivity.LOGIN_TYPE_GMAIL;
        Intent intent = new Intent(getContext(), GoogleLoginActivity.class);
        if(intent != null) {
            getActivity().startActivityForResult(intent, Constants.GMAIL_REQUEST_CODE);
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("TAG", "requestCode " + requestCode);

        /*if (requestCode == Constants.GMAIL_REQUEST_CODE) {
            // [START get_auth_code]
            if(resultCode == GOOGLE_LOGIN_RESPONSE_OK) {
                String authID = data.getStringExtra("authCode");
                IPresenter.loginGoogle(authID);
                Log.i("TAG", "authCode = " + authID);
            }
        }*/
        // [END get_auth_code]
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void tryAgain() {
        switch (isLoginType){
            case LoginActivity.LOGIN_TYPE_NORMAL:
                String email = HealthHuntPreference.getString(getContext(), Constants.EMAIL);
                String password = HealthHuntPreference.getString(getContext(), Constants.PASSWORD);
                IPresenter.validateCredentialsLogIn(email, password);
                break;
            case LoginActivity.LOGIN_TYPE_FACEBOOK:
                IPresenter.loginFacebook(getContext());
                break;
            case LoginActivity.LOGIN_TYPE_GMAIL:
                break;


        }
    }
}
