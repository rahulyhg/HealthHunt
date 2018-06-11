package in.healthhunt.presenter.interactor.notificationInteractor;

import android.content.Context;

import java.util.Map;

import framework.retrofit.RestError;
import in.healthhunt.model.notification.NotificationData;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface INotificationInteractor {

    interface OnFinishListener {
        void onNotificationSuccess(NotificationData notificationData);
        void onError(RestError errorInfo);
    }

    void fetchNotifications(Context context, Map<String, String> queryMap, OnFinishListener finishListener);
}
