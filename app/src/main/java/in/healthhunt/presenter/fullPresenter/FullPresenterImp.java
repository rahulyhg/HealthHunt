package in.healthhunt.presenter.fullPresenter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.bookmarkResponse.BookMarkData;
import in.healthhunt.model.articles.bookmarkResponse.BookMarkInfo;
import in.healthhunt.model.articles.commonResponse.CurrentUser;
import in.healthhunt.model.articles.postResponse.ArticlePost;
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.presenter.homeScreenPresenter.BookMarkInteractorImpl;
import in.healthhunt.presenter.homeScreenPresenter.IBookMarkInteractor;
import in.healthhunt.view.fullView.IFullView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class FullPresenterImp implements IFullPresenter, IFullInteractor.OnFinishListener, IBookMarkInteractor.OnFinishListener {

    private String TAG = FullPresenterImp.class.getSimpleName();
    private IFullView IFullView;
    private Context mContext;
    private ArticlePost mArticlePost;
    private IFullInteractor IFullInteractor;
    private IBookMarkInteractor IBookMarkInteractor;

    public FullPresenterImp(Context context, IFullView fullView) {
        mContext =  context;
        IFullView = fullView;
        IFullInteractor = new FullInteractorImpl();
        IBookMarkInteractor = new BookMarkInteractorImpl();
    }

    @Override
    public void loadFragment(String tag, Bundle bundle) {

    }

    @Override
    public void fetchArticle(String id) {
        IFullView.showProgress();
        IFullInteractor.fetchFullArticle(mContext, id, this);
    }

    @Override
    public ArticlePost getArticle() {
        return mArticlePost;
    }

    @Override
    public ProductPostItem getProduct() {
        return null;
    }

    @Override
    public void bookmark(String id) {
        IFullView.showProgress();
        IBookMarkInteractor.bookmark(mContext, id, IFullView.getPostType(), this);
    }

    @Override
    public void unBookmark(String id) {
        IFullView.showProgress();
        IBookMarkInteractor.unBookmark(mContext, id, IFullView.getPostType(), this);
    }

    @Override
    public void onArticleSuccess(ArticlePost item) {
        Log.i("TAGITEM", "ITEM " + item);
        mArticlePost = item;
        IFullView.hideProgress();
        IFullView.setContent();
    }

    @Override
    public void onProductSuccess(ProductPostItem item) {
        IFullView.hideProgress();
    }

    @Override
    public void onBookMarkSuccess(BookMarkData markResponse) {
        IFullView.hideProgress();
        BookMarkInfo bookMarkInfo = markResponse.getBookMarkInfo();

        if(bookMarkInfo == null){
            Log.i("TAG", " Book Mark info is null");
            return;
        }

        int type = bookMarkInfo.getType();

        switch (type){
            case ArticleParams.ARTICLE:
                if(bookMarkInfo.getPost_id().equals(mArticlePost.getId())) {
                    CurrentUser currentUser = mArticlePost.getCurrent_user();
                    if (currentUser != null) {
                        currentUser.setBookmarked(bookMarkInfo.isBookMark());
                    }
                }
                break;

            case ArticleParams.PRODUCT:
                break;
        }

        IFullView.updateBookMarkIcon();
    }

    @Override
    public void onError(RestError errorInfo) {
        Log.i("TAGITEM", "Error " + errorInfo.getMessage());
        IFullView.hideProgress();
    }
}
