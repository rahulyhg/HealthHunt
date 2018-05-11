package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import in.healthhunt.model.articles.articleResponse.PostsItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IViewAllPresenter {
    int getCount();
    RecyclerView.ViewHolder createArticleHolder(View view);
    void fetchTagsArticle();
    List<PostsItem> getAllArticles();
}
