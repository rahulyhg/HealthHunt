package in.healthhunt.model.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishekkumar on 4/21/18.
 */

public class LoginRequest {

    @SerializedName("email")
    private String mEmail = "charlene@test.com";

    @SerializedName("password")
    private String mPassword = "123456";

    @SerializedName("username")
    private String mUserName = "charlene777";

    @SerializedName("name")
    private String mName = "charlene";

   // @SerializedName("authToken")
    //private String mAuthCode;

    //private String mPrivateKey = "Bd6723sXcVBg12Fe";


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

    public String getmUserName() {
        return mUserName;
    }

    public String getmName() {
        return mName;
    }




//    public void setAuthCode(String requestUrl, String timeStamp) {
//       mAuthCode = requestUrl + mPrivateKey + timeStamp;
//    }
//
//    public String getAuthCode() {
//         return mAuthCode;
//    }
}
