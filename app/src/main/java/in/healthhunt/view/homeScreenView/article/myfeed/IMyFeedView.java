package in.healthhunt.view.homeScreenView.article.myfeed;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import in.healthhunt.model.articles.articleResponse.PostsItem;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public interface IMyFeedView {
    int getCount();
    RecyclerView.ViewHolder createArticleHolder(View view, int type);
    void onClickViewAll(String tag, Bundle bundle);
    void onClickCrossView(int index);
    void onLoadComplete();
    List<PostsItem> getTagArticles();
    List<PostsItem> getTrendingArticles();
    void updateAdapter();
    void updateNavigation();
    void setNavigation();
    void showAlert(String msg);
}
