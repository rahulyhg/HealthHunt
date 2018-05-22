package in.healthhunt.presenter.homeScreenPresenter.myHuntPresenter.myHuntArticlePresenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.presenter.homeScreenPresenter.IPostPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IMyHuntsArticlePresenter extends IPostPresenter{
     RecyclerView.ViewHolder createViewHolder(View view);
     List<ArticlePostItem> getList();
}
