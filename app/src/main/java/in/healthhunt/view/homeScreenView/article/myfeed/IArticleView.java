package in.healthhunt.view.homeScreenView.article.myfeed;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;

/**
 * Created by abhishekkumar on 4/24/18.
 */

public interface IArticleView {

    Fragment getFragmentArticleItem(int position);
    int getArticleCount();
    void showFragment(String tag, Bundle bundle);
    ArticlePostItem getArticle(int pos);
}
