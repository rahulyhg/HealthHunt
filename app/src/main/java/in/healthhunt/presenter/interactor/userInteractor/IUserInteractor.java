package in.healthhunt.presenter.interactor.userInteractor;

import android.content.Context;

import framework.retrofit.RestError;
import in.healthhunt.model.login.User;
import in.healthhunt.model.user.UserRequest;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public interface IUserInteractor {

    interface OnUserUpdateFinishListener {
        void onError(RestError errorInfo);
        void onUserUpdateSuccess(User user);
    }

    interface OnUserFinishListener {
        void onError(RestError errorInfo);
        void onUserSuccess(User user);
    }

    void updateUser(Context context, UserRequest userRequest, OnUserUpdateFinishListener updateFinishListener);
    void fetchCurrentUser(Context context, OnUserFinishListener finishListener);
}
