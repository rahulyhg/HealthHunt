package in.healthhunt.view.homeScreenView.myFeedView.articleView;

import android.support.v4.app.Fragment;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;

/**
 * Created by abhishekkumar on 4/24/18.
 */

public interface IArticleView {
    Fragment getFragmentArticleItem(int position);
    int getArticleCount();
    ArticlePostItem getArticle(int pos);
    void updateBookMark(String id, int type, boolean isBookMark);
    void showProgress();
    void hideProgress();
}
