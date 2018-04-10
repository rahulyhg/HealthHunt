package presenter;

import model.ErrorInfo;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public interface ILoginInteractor {

    interface OnLoginFinishListener {
        void onSuccess();
        void onError(ErrorInfo errorInfo);
    }

    void login(String userName, String password, OnLoginFinishListener loginFinishListener);
}
