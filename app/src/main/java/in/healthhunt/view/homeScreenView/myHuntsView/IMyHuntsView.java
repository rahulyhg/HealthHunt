package in.healthhunt.view.homeScreenView.myHuntsView;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import in.healthhunt.view.homeScreenView.myFeedView.IView;

/**
 * Created by abhishekkumar on 5/21/18.
 */

public interface IMyHuntsView extends IView {
    RecyclerView.ViewHolder createViewHolder(View view);
    void updateAdapter();
    void showAlert(String msg);
    int getType();
    void loadFragment(String fragmentName, Bundle bundle);
}
