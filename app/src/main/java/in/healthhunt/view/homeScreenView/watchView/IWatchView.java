package in.healthhunt.view.homeScreenView.watchView;

import android.view.View;

import in.healthhunt.view.homeScreenView.myFeedView.IView;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public interface IWatchView extends IView {
    WatchViewHolder createViewHolder(View view);
    void updateAdapter();
    void showAlert(String msg);
}
