package in.healthhunt.view.homeScreenView.myFeedView.articleView;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.view.homeScreenView.myFeedView.IView;

/**
 * Created by abhishekkumar on 4/24/18.
 */

public interface IArticleView extends IView {
    Fragment getFragmentArticleItem(int position);
    ArticlePostItem getArticle(int pos);
    void loadFragment(String fragmentName, Bundle bundle);
    void updateAdapter();
    void updateSavedData(ArticlePostItem articlePostItem);
}
