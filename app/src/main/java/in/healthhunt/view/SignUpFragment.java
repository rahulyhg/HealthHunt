package in.healthhunt.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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
import in.healthhunt.presenter.ILoginPresenter;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public class SignUpFragment extends Fragment {
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
        IPresenter.loadFragment(LoginFragment.class.getSimpleName());
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
