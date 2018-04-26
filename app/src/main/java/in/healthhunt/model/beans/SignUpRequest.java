package in.healthhunt.model.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishekkumar on 4/26/18.
 */

public class SignUpRequest {

    @SerializedName("email")
    private String mEmail;

    @SerializedName("password")
    private String mPassword;

    @SerializedName("username")
    private String mUserName = "charlene777";

    @SerializedName("name")
    private String mName ="charlene";

    @SerializedName("gender")
    private String mMale ="male";

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
}