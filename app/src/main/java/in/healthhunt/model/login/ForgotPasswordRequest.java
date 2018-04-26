package in.healthhunt.model.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishekkumar on 4/26/18.
 */

public class ForgotPasswordRequest {

    @SerializedName("email")
    private String mEmail;

    @SerializedName("username")
    private String mUsername;

    @SerializedName("newpassword")
    private String mNewPassword;

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmNewPassword() {
        return mNewPassword;
    }

    public void setmNewPassword(String mNewPassword) {
        this.mNewPassword = mNewPassword;
    }
}
