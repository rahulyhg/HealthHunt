package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IMyFeedPresenter {
    int getCount();
    RecyclerView.ViewHolder createArticleHolder(View view, int type);
}
