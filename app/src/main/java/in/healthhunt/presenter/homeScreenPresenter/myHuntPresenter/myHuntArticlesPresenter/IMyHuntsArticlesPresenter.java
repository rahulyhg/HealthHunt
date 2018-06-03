package in.healthhunt.presenter.homeScreenPresenter.myHuntPresenter.myHuntArticlesPresenter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.presenter.homeScreenPresenter.IPostPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IMyHuntsArticlesPresenter extends IPostPresenter{
     RecyclerView.ViewHolder createViewHolder(View view);
     List<ArticlePostItem> getArticleList();
     void updateDownloadList(List<ArticlePostItem> articlePosts);
     ArticlePostItem getArticle(int pos);
     void loadFragment(String fragmentName, Bundle bundle);
     void fetchArticles(String userId);
}
