package in.healthhunt.presenter.interactor.notificationInteractor;

import android.content.Context;

import java.util.Map;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.model.notification.NotificationData;
import in.healthhunt.model.response.HHResponse;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 5/16/18.
 */

public class NotificationInteractorImpl implements INotificationInteractor {


    @Override
    public void fetchNotifications(Context context, Map<String, String> queryMap, final OnFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).fetchNotifications(queryMap, new ResponseResolver<HHResponse<NotificationData>>() {
            @Override
            public void onSuccess(HHResponse<NotificationData> notificationData, Response response) {
                finishListener.onNotificationSuccess(notificationData.getData());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
    }
}
