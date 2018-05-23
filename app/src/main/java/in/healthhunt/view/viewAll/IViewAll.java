package in.healthhunt.view.viewAll;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public interface IViewAll {
    RecyclerView.ViewHolder onCreateViewHolder(View view);
    void showProgress();
    void hideProgress();
    void updateAdapter();
    int getViewLayout();
    int getType();
}
