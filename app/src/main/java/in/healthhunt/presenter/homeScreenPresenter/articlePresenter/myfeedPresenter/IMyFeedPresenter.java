package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import in.healthhunt.model.articles.articleResponse.PostsItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IMyFeedPresenter {
    int getCount();
    RecyclerView.ViewHolder createArticleHolder(View view, int type);
    void fetchTagsArticle(int offset, int limit);
    void fetchTrendingArticle(int offset, int limit);
    void fetchLatestArticle(int offset, int limit);
    List<PostsItem> getTagArticles();
    List<PostsItem> getTrendingArticles();
    List<PostsItem> getLatestArticles();
    void fetchData();
}
