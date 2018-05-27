package in.healthhunt.presenter.homeScreenPresenter.watchPresenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.bookmarkResponse.BookMarkData;
import in.healthhunt.model.articles.bookmarkResponse.BookMarkInfo;
import in.healthhunt.model.articles.commonResponse.CurrentUser;
import in.healthhunt.presenter.interactor.articleInteractor.ArticleInteractorImpl;
import in.healthhunt.presenter.interactor.articleInteractor.IArticleInteractor;
import in.healthhunt.presenter.interactor.bookMarkInteractor.BookMarkInteractorImpl;
import in.healthhunt.presenter.interactor.bookMarkInteractor.IBookMarkInteractor;
import in.healthhunt.view.homeScreenView.watchView.IWatchView;
import in.healthhunt.view.homeScreenView.watchView.WatchViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class WatchPresenterImp implements IWatchPresenter, IArticleInteractor.OnViewAllFinishListener, IBookMarkInteractor.OnFinishListener{

    private String TAG = WatchPresenterImp.class.getSimpleName();
    private IArticleInteractor IArticleInteractor;
    private Context mContext;
    private IWatchView IWatchView;
    private List<ArticlePostItem> mVideoArticles;
    private IBookMarkInteractor IBookMarkInteractor;


    public WatchPresenterImp(Context context, IWatchView watchView) {
        mContext =  context;
        IArticleInteractor = new ArticleInteractorImpl();
        IBookMarkInteractor = new BookMarkInteractorImpl();
        IWatchView = watchView;
    }

    @Override
    public void onArticleSuccess(List<ArticlePostItem> items) {
        IWatchView.hideProgress();
        mVideoArticles = items;
        Log.i("TAGITEMS", "ITEMS " + mVideoArticles.size());
        IWatchView.updateAdapter();
    }

    @Override
    public void onBookMarkSuccess(BookMarkData markResponse) {
        IWatchView.hideProgress();
        for(int i = 0; i< mVideoArticles.size(); i++) {
            ArticlePostItem postItem = mVideoArticles.get(i);
            BookMarkInfo bookMarkInfo = markResponse.getBookMarkInfo();
            if(bookMarkInfo.getPost_id().equals(String.valueOf(postItem.getId()))) {
                CurrentUser currentUser = postItem.getCurrent_user();
                if (currentUser != null) {
                    currentUser.setBookmarked(bookMarkInfo.isBookMark());
                }
                break;
            }
        }
        IWatchView.updateAdapter();
    }

    @Override
    public void onError(RestError errorInfo) {
        IWatchView.hideProgress();
        if(errorInfo != null) {
            IWatchView.showAlert(errorInfo.getMessage());
        }
    }

    @Override
    public int getCount() {
        int count = 0;
        if(mVideoArticles != null){
            count = mVideoArticles.size();
        }
        return count;
    }

    @Override
    public void bookmark(String id, int type) {
        IWatchView.showProgress();
        IBookMarkInteractor.bookmark(mContext, id, type, this);
    }

    @Override
    public void unBookmark(String id, int type) {
        IWatchView.showProgress();
        IBookMarkInteractor.unBookmark(mContext, id, type, this);
    }

    @Override
    public void updateBottomNavigation() {

    }

    @Override
    public WatchViewHolder createViewHolder(View view) {
        return IWatchView.createViewHolder(view);
    }

    @Override
    public void fetchVideoArticles() {
        IWatchView.showProgress();
        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.OFFSET, String.valueOf(0));
        map.put(ArticleParams.LIMIT, String.valueOf(2));
        map.put(ArticleParams.FORMAT, ArticleParams.POST_FORMAT_VIDEO);
        IArticleInteractor.fetchAllArticle(mContext, map, this);
    }

    @Override
    public ArticlePostItem getArticle(int pos) {
        ArticlePostItem postItem = null;
        if(mVideoArticles != null){
         postItem = mVideoArticles.get(pos);
        }
        return postItem;
    }
}
