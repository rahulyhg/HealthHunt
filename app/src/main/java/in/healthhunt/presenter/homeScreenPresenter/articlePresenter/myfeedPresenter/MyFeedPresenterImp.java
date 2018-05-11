package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.PostsItem;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.presenter.preference.HealthHuntPreference;
import in.healthhunt.view.homeScreenView.article.myfeed.IMyFeedView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class MyFeedPresenterImp implements IMyFeedPresenter, IMyFeedInteractor.OnArticleFinishListener{

    private String TAG = MyFeedPresenterImp.class.getSimpleName();
    private IMyFeedView IMyFeedView;
    private Context mContext;
    private IMyFeedInteractor IMyFeedInteractor;
    private List<PostsItem> mTagArticles;
    private List<PostsItem> mTrendingArticles;

    public MyFeedPresenterImp(Context context, IMyFeedView feedView) {
        mContext =  context;
        IMyFeedView = feedView;
        IMyFeedInteractor = new MyFeedInteractorImpl();
    }

    @Override
    public int getCount() {
        return IMyFeedView.getCount();
    }

    @Override
    public RecyclerView.ViewHolder createArticleHolder(View view, int type) {
        return IMyFeedView.createArticleHolder(view, type);
    }

    @Override
    public void fetchTagsArticle(int offset, int limit) {

        Set<String> tagIds = HealthHuntPreference.getSet(mContext, Constants.SELECTED_TAGS_KEY);
        String tags = "";
        Iterator iterator = tagIds.iterator();
        while (iterator.hasNext()) {
            tags = tags + iterator.next();
            if(iterator.hasNext()){
                tags = tags + ",";
            }
        }
        Log.i("TAG1111", "ids = " + tags);

        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.TAGS, tags);
        map.put(ArticleParams.OFFSET, String.valueOf(offset));
        map.put(ArticleParams.LIMIT, String.valueOf(limit));

        IMyFeedInteractor.fetchArticle(mContext, map, this);
    }

    @Override
    public void fetchTrendingArticle(int offset, int limit) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.TRENDING, String.valueOf(1));
        map.put(ArticleParams.OFFSET, String.valueOf(offset));
        map.put(ArticleParams.LIMIT, String.valueOf(limit));
        IMyFeedInteractor.fetchTrendingArticle(mContext, map, this);
    }

    @Override
    public List<PostsItem> getTagArticles() {
        return mTagArticles;
    }

    @Override
    public List<PostsItem> getTrendingArticles() {
        return mTrendingArticles;
    }

    @Override
    public void fetchData() {
        fetchTagsArticle(0, 5);
        fetchTrendingArticle(0, 2);
    }

    @Override
    public void onSuccess(List<PostsItem> items, int type) {

        switch (type) {
            case 0:
                mTagArticles = items;
                break;

            case 1:
                mTrendingArticles = items;
                break;
        }

        if(type == 1) {
            IMyFeedView.onLoadComplete();
        }
        IMyFeedView.updateAdapter();
    }

    @Override
    public void onError(RestError errorInfo) {
        String msg = "Server error";
        if(errorInfo != null && errorInfo.getMessage() != null) {
            msg = errorInfo.getMessage();
        }
        IMyFeedView.showAlert(msg);
    }
}
