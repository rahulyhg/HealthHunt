package view;

import android.content.Context;
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
import android.widget.Toast;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.healthhunt.R;
import presenter.ILoginPresenter;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public class LoginFragment extends Fragment{

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
        IPresenter.validateCredentialsLogIn(mEmail.getText().toString(), mPassword.getText().toString());
    }

    @OnClick(R.id.sign_up)
    void onSignUp() {
        IPresenter.loadFragment(SignUpFragment.class.getSimpleName());
    }

    @OnClick(R.id.forgot_password)
    void onForgotPassword(){
        IPresenter.loadFragment(ForgotPasswordFragment.class.getSimpleName());
    }

    @OnClick(R.id.facebook)
    void onFacebook() {

    }

    @OnClick(R.id.gmail)
    void onGmail() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
