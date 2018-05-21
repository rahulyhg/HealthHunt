package in.healthhunt.presenter.homeScreenPresenter.myHuntPresenter.myHuntsArticlePresenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.presenter.homeScreenPresenter.myHuntPresenter.IMyHuntsInteractor;
import in.healthhunt.presenter.homeScreenPresenter.myHuntPresenter.MyHuntsInteractorImpl;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class MyHuntsArticlePresenterImp implements IMyHuntsArticlePresenter, IMyHuntsInteractor.OnFinishListener {

    private String TAG = MyHuntsArticlePresenterImp.class.getSimpleName();
    private IMyHuntsInteractor IMyHuntsInteractor;
    private Context mContext;
    private in.healthhunt.view.homeScreenView.myHuntsView.IMyHuntsView IMyHuntsView;

    public MyHuntsArticlePresenterImp(Context context, in.healthhunt.view.homeScreenView.myHuntsView.IMyHuntsView myHuntsView) {
        mContext =  context;
        IMyHuntsInteractor = new MyHuntsInteractorImpl();
        IMyHuntsView = myHuntsView;
    }

    @Override
    public void onSuccess() {
        IMyHuntsView.hideProgress();
    }

    @Override
    public void onError(RestError errorInfo) {
        IMyHuntsView.hideProgress();
        if(errorInfo != null) {
            IMyHuntsView.showAlert(errorInfo.getMessage());
        }
    }

    @Override
    public int getCount() {
        return IMyHuntsView.getCount();
    }

    @Override
    public void bookmark(String id, int type) {

    }

    @Override
    public void unBookmark(String id, int type) {

    }

    @Override
    public void updateBottomNavigation() {

    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(View view) {
        return IMyHuntsView.createViewHolder(view);
    }

    @Override
    public List<ArticlePostItem> getList() {

        int type = IMyHuntsView.getType();
        switch (type){
            case ArticleParams.SAVED:
                break;

            case ArticleParams.APPROVED:
                break;

            case ArticleParams.RECEIVED:
                break;

            case ArticleParams.DOWNLOADED:
                break;
        }
        return null;
    }
}
