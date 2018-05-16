package in.healthhunt.view.homeScreenView.article.myfeed;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.productResponse.ProductPostItem;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public interface IMyFeedView {
    int getCount();
    RecyclerView.ViewHolder createArticleHolder(View view, int type);
    void onClickViewAll(String tag, Bundle bundle);
    void onClickCrossView(int index);
    void onLoadComplete();
    List<ArticlePostItem> getTagArticles();
    List<ArticlePostItem> getTrendingArticles();
    List<ArticlePostItem> getLatestArticles();
    List<ArticlePostItem> getSponsoredArticles();
    List<ProductPostItem> getTopProductArticles();
    List<ProductPostItem> getLatestProductArticles();
    void updateAdapter();
    void updateNavigation();
    void setNavigation();
    void showAlert(String msg);
    int getView(int type);
}
