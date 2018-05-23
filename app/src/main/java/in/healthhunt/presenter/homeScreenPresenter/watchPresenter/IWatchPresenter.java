package in.healthhunt.presenter.homeScreenPresenter.watchPresenter;

import android.view.View;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.presenter.homeScreenPresenter.IPostPresenter;
import in.healthhunt.view.homeScreenView.watchView.WatchViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IWatchPresenter extends IPostPresenter{
     WatchViewHolder createViewHolder(View view);
     void fetchVideoArticles();
     ArticlePostItem getArticle(int pos);
}
