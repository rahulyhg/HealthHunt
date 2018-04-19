package presenter;


import android.content.Context;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import in.healthhunt.R;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public class LoginInteractorImpl implements ILoginInteractor {
    @Override
    public void login(String userName, String password, OnLoginFinishListener loginFinishListener) {
        // implement the login code here

    }

    @Override
    public String loginWithFacebook(Context context) {
        return null;
    }

    @Override
    public Intent loginWithGmail(Context context) {
        String serverClientId = context.getResources().getString(R.string.server_client_id);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestServerAuthCode(serverClientId)
                .requestEmail()
                .build();
        return GoogleSignIn.getClient(context, gso).getSignInIntent();
    }
}
