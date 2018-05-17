package in.healthhunt.presenter.fullPresenter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.postResponse.ArticlePost;
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.view.fullView.IFullView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class FullPresenterImp implements IFullPresenter, in.healthhunt.presenter.fullPresenter.IFullInteractor.OnFinishListener {

    private String TAG = FullPresenterImp.class.getSimpleName();
    private IFullView IFullView;
    private Context mContext;
    private ArticlePost mArticlePost;
    private IFullInteractor IFullInteractor;

    public FullPresenterImp(Context context, IFullView fullView) {
        mContext =  context;
        IFullView = fullView;
        IFullInteractor = new FullInteractorImpl();
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
    public void onError(RestError errorInfo) {
        Log.i("TAGITEM", "Error " + errorInfo.getMessage());
        IFullView.hideProgress();
    }
}
