package in.healthhunt.model.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishekkumar on 4/21/18.
 */

public class LoginRequest {

    @SerializedName("email")
    private String mEmail = "charlene@test.com";

    @SerializedName("password")
    private String mPassword = "123456";

    @SerializedName("social_network")
    private String mSocialNetwork;

    @SerializedName("social_token")
    private String mSocialToken;

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmSocialNetwork() {
        return mSocialNetwork;
    }

    public void setmSocialNetwork(String mSocialNetwork) {
        this.mSocialNetwork = mSocialNetwork;
    }

    public String getmSocialToken() {
        return mSocialToken;
    }

    public void setmSocialToken(String mSocialToken) {
        this.mSocialToken = mSocialToken;
    }
}
