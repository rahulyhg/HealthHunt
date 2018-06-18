package in.healthhunt.presenter.homeScreenPresenter.myHuntPresenter.myHuntsVideoPresenter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.presenter.homeScreenPresenter.IPostPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IMyHuntsVideoPresenter extends IPostPresenter{
     RecyclerView.ViewHolder createViewHolder(View view);
     List<ArticlePostItem> getVideoList();
     void updateDownloadList(List<ArticlePostItem> videoPosts);
     ArticlePostItem getVideo(int pos);
     void loadFragment(String fragmentName, Bundle bundle);
     void fetchVideos(String userId);
     void updateVideoSaved(ArticlePostItem postItem);
     void deleteArticle(String id);
}
