package in.healthhunt.presenter.interactor.userInteractor;


import android.content.Context;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.model.login.UserData;
import in.healthhunt.model.response.HHResponse;
import in.healthhunt.model.user.UserRequest;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public class UserInteractorImpl implements IUserInteractor {
    @Override
    public void updateUser(Context context, UserRequest userRequest, final OnUserUpdateFinishListener updateFinishListener) {
        WebServicesWrapper.getInstance(context).updateUser(userRequest, new ResponseResolver<HHResponse<UserData>>() {
            @Override
            public void onSuccess(HHResponse<UserData> userDataHHResponse, Response response) {
                updateFinishListener.onUserUpdateSuccess(userDataHHResponse.getData().getUser());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                updateFinishListener.onError(error);
            }
        });
    }

    @Override
    public void fetchCurrentUser(Context context, final OnUserFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).fetchCurrentUser(new ResponseResolver<HHResponse<UserData>>() {
            @Override
            public void onSuccess(HHResponse<UserData> userDataHHResponse, Response response) {
                finishListener.onUserSuccess(userDataHHResponse.getData().getUser());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
    }
}
