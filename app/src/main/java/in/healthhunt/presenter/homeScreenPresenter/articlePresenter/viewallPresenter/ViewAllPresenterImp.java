package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import in.healthhunt.view.homeScreenView.article.viewall.IViewAll;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ViewAllPresenterImp implements IViewAllPresenter, IViewAllInteractor.OnArticleFinishListener {

    private String TAG = ViewAllPresenterImp.class.getSimpleName();
    private IViewAll IViewAll;
    private Context mContext;
    private IViewAllInteractor IViewAllInteractor;
    private List<PostsItem> mPosts;

    public ViewAllPresenterImp(Context context, IViewAll viewAll) {
        mContext =  context;
        IViewAll = viewAll;
        IViewAllInteractor = new ViewAllInteractorImpl();
    }

    @Override
    public int getCount() {
        int count = 0;
        if(mPosts != null) {
            count = mPosts.size();
        }
        return count;
    }

    @Override
    public RecyclerView.ViewHolder createArticleHolder(View view) {
        return IViewAll.onCreateViewHolder(view);
    }

    @Override
    public void fetchTagsArticle() {

        IViewAll.showProgress();
        Set<String> tagIds = HealthHuntPreference.getSet(mContext, Constants.SELECTED_TAGS_KEY);
        String tags = "";
        Iterator iterator = tagIds.iterator();
        while (iterator.hasNext()) {
            tags = tags + iterator.next();
            if(iterator.hasNext()){
                tags = tags + ",";
            }
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.TAGS, tags);
        map.put(ArticleParams.OFFSET, String.valueOf(0));
        map.put(ArticleParams.LIMIT, String.valueOf(30));

        IViewAllInteractor.fetchAllArticle(mContext, map, this);
    }

    @Override
    public List<PostsItem> getAllArticles() {
        return mPosts;
    }

    @Override
    public void onSuccess(List<PostsItem> items, int type) {
        IViewAll.hideProgress();
        switch (type) {
            case ArticleParams.BASED_ON_TAGS:
                mPosts = items;
                break;
        }

        IViewAll.updateAdapter(type);

    }

    @Override
    public void onError(RestError errorInfo) {
        IViewAll.hideProgress();
    }
}
