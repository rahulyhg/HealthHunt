package in.healthhunt.presenter.notificationPresenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import in.healthhunt.model.notification.NotificationsItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface INotificationPresenter {
    void fetchNotifications();
    int getCount();
    List<NotificationsItem> getNotificationList();
    NotificationsItem getNotification(int pos);
    RecyclerView.ViewHolder createViewHolder(View view);
}
