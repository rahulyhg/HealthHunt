package in.healthhunt.presenter.searchPresenter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface ISearchPresenter {
    int getCount();
    RecyclerView.ViewHolder createViewHolder(View view);
    List<ArticlePostItem> getAllArticles();
    ArticlePostItem getArticle(int pos);
    void searchArticles(String searchStr);
    void bookmark(String id);
    void unBookmark(String id);
    void loadFragment(String fragmentName, Bundle bundle);
}
