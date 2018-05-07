package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IViewAllPresenter {
    int getCount();
    RecyclerView.ViewHolder createArticleHolder(View view);
}
