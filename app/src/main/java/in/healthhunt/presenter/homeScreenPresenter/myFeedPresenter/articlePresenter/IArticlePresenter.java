package in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.articlePresenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.presenter.homeScreenPresenter.IPostPresenter;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public interface IArticlePresenter extends IPostPresenter {
    Fragment getItem(int position, int type);
    ArticlePostItem getArticle(int pos);
    void loadFragment(String fragmentName, Bundle bundle);
}
