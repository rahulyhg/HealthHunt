package in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.articlePresenter;

import android.support.v4.app.Fragment;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public interface IArticlePresenter {
    int getCount();
    Fragment getItem(int position, int type);
    void bookmark(String id, int type);
    void unBookmark(String id);
    ArticlePostItem getArticle(int pos);
}
