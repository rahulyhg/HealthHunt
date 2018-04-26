package in.healthhunt.view.loginView;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.healthhunt.R;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.presenter.loginPresenter.ILoginPresenter;

/**
 * Created by abhishekkumar on 4/10/18.
 */

public class ForgotPasswordFragment extends Fragment{

    @BindView(R.id.new_password)
    EditText mNewPassword;

    @BindView(R.id.repeat_password)
    EditText mRepeatPassword;

    @BindView(R.id.login)
    Button mLogin;

    private ILoginPresenter IPresenter;
    private Unbinder unbinder;
    private String mEmail;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgotpassword, container, false);
        unbinder = ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if(bundle != null) {
            mEmail = bundle.getString(Constants.EMAIL);
        }
        Log.i("TAGFRAG", "Email " + mEmail);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        IPresenter = ((LoginActivity)context).getPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.login)
    void onLogin(){
        IPresenter.validateNewPassword(mNewPassword.getText().toString(),
                mRepeatPassword.getText().toString(), mEmail, mEmail);
    }
}
