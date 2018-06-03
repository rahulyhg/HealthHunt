package in.healthhunt.view.notificationView;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import in.healthhunt.R;
import in.healthhunt.model.notification.NotificationsItem;
import in.healthhunt.presenter.notificationPresenter.INotificationPresenter;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private INotificationPresenter INotificationPresenter;
    private Context mContext;
    private ClickListener mClickListener;

    public NotificationAdapter(Context context, INotificationPresenter notificationPresenter) {
        mContext = context;
        INotificationPresenter = notificationPresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return INotificationPresenter.createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setContent(((NotificationHolder)holder), position);
    }

    private void setContent(NotificationHolder holder, int position) {
        NotificationsItem notificationsItem = INotificationPresenter.getNotification(position);

        if(notificationsItem != null) {

            String authorUrl = notificationsItem.getUser_url();
            if(authorUrl != null) {
                authorUrl = authorUrl.replace("\n", "");
                Glide.with(mContext)
                        .load(authorUrl)
                        .bitmapTransform(new CropCircleTransformation(mContext)).placeholder(R.mipmap.avatar)
                        .into(holder.mPic);
            }

            String name = notificationsItem.getUser_name();
            if(name != null) {
                holder.mName.setText(name);
            }
            else {
                holder.mName.setText("");
            }

            String category = notificationsItem.getCategory();
            if(category != null){
                holder.mCategory.setText(category);
            }
            else {
                holder.mCategory.setText("");
            }

            String date = notificationsItem.getEvent_time();
            if(date != null) {
                holder.mHour.setText(date);
            }
            else {
                holder.mHour.setText("");
            }

            boolean isViewed = notificationsItem.IsViewed();
            int color = Color.WHITE;
            if(!isViewed){
                color = ContextCompat.getColor(mContext, R.color.hh_green_light4);
            }
            holder.mNotificationView.setBackgroundColor(color);

        }
    }

    @Override
    public int getItemCount() {
        return INotificationPresenter.getCount();
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public interface ClickListener {
        void ItemClicked(View v, int position);
    }
}
