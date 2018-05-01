package in.healthhunt.presenter.homeScreenPresenter;

import android.support.v4.app.FragmentManager;
import android.view.View;

import in.healthhunt.view.homeScreenView.article.ArticleViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IHomePresenter {
    int getCount();
    ArticleViewHolder createArticleHolder(View view, FragmentManager fragmentManager);
}
