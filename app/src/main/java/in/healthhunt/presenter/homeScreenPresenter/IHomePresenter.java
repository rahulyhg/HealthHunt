package in.healthhunt.presenter.homeScreenPresenter;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import in.healthhunt.view.homeScreenView.article.ArticleViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IHomePresenter {
    int getCount();
    RecyclerView.ViewHolder createArticleHolder(View view, FragmentManager fragmentManager, int type);
}
