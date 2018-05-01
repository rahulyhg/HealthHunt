package in.healthhunt.view.loginView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.healthhunt.R;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.presenter.loginPresenter.ILoginPresenter;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public class SignUpFragment extends Fragment {
    private final String TAG = SignUpFragment.class.getSimpleName();
    @BindView(R.id.sign_up)
    Button mSignUp;

    @BindView(R.id.sign_in)
    TextView mSignIn;

    @BindView(R.id.facebook)
    ImageButton mFacebook;

    @BindView(R.id.gmail)
    ImageButton mGmail;

    @BindView(R.id.email)
    EditText mEmail;

    @BindView(R.id.password)
    EditText mPassword;

    @BindView(R.id.skip)
    TextView mSkipView;

    @BindView(R.id.term_and_conditions)
    TextView mTermAndConditions;

    private ILoginPresenter IPresenter;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container,  false);
        unbinder = ButterKnife.bind(this, view);
        mSkipView.setVisibility(View.GONE);
        addLink();
        return view;
    }

    private void addLink() {
        String part1 = getString(R.string.terms_and_conditions_part1);
        String part2 = getString(R.string.terms_and_conditions_part2);
        String str = part1 + " " + part2;

        Spannable spannable = new SpannableString(str);
        spannable.setSpan(new ForegroundColorSpan(ActivityCompat.getColor(getContext(), R.color.hh_text_color)),
                part1.length() + 1 , str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTermAndConditions.setText(spannable, TextView.BufferType.SPANNABLE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        IPresenter = ((LoginActivity) context).getPresenter();
    }

    @OnClick(R.id.sign_up)
    void onSignUp() {
        IPresenter.validateCredentialsSignUp(mEmail.getText().toString(), mPassword.getText().toString());
    }

    @OnClick(R.id.sign_in)
    void OnSignIn(){
        IPresenter.loadFragment(LoginFragment.class.getSimpleName(), null);
    }

    @OnClick(R.id.facebook)
    void onFacebook() {
        IPresenter.loginFacebook(getContext());
    }

    @OnClick(R.id.gmail)
    void onGmail() {
        Intent intent = IPresenter.loginGoogle(getContext());
        if(intent != null) {
            startActivityForResult(intent, Constants.GMAIL_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("TAG", "requestCode " + requestCode);

        if (requestCode == Constants.GMAIL_REQUEST_CODE) {
            // [START get_auth_code]
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                String authCode = account.getIdToken();
                IPresenter.loginGoogle(authCode);

                Log.d(TAG, "AuthCode = " + authCode);
                // TODO(developer): send code to server and exchange for access/refresh/ID tokens
            } catch (ApiException e) {
                ((LoginActivity)getActivity()).onHideProgress();
                e.printStackTrace();
                Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            }
        }
        // [END get_auth_code]
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}