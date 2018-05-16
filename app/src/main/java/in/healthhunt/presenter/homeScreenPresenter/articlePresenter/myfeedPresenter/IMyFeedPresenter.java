package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;
import java.util.Map;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.productResponse.ProductPostItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IMyFeedPresenter {
    int getCount();
    RecyclerView.ViewHolder createArticleHolder(View view, int type);
    void fetchTagsArticle(int offset, int limit);
    void fetchTrendingArticle(int offset, int limit);
    void fetchLatestArticle(int offset, int limit);
    void fetchSponsoredArticle(String section, int offset, int limit);
    void fetchTopProduct(int offset, int limit);
    void fetchLatestProduct(int offset, int limit);
    List<ArticlePostItem> getTagArticles();
    List<ArticlePostItem> getTrendingArticles();
    List<ArticlePostItem> getLatestArticles();
    List<ArticlePostItem> getSponsoredArticles();
    List<ProductPostItem> getTopProductArticles();
    List<ProductPostItem> getLatestProductArticles();
    Map<Integer, Integer> getArticlesType();
    void fetchData();
    int getView(int type);
    void deleteArticleType(int pos);
    void addContinueArticles();
}
