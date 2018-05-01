package in.healthhunt.presenter.homeScreenPresenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import in.healthhunt.view.homeScreenView.article.ArticleViewHolder;
import in.healthhunt.view.homeScreenView.IHomeView;
import in.healthhunt.view.homeScreenView.article.ContinueArticleViewHolder;
import in.healthhunt.view.homeScreenView.article.TrendingArticleViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class HomePresenterImp implements IHomePresenter {

    private String TAG = HomePresenterImp.class.getSimpleName();
    private IHomeView IHomeView;
    private Context mContext;

    public HomePresenterImp(Context context, IHomeView homeView) {
        mContext =  context;
        IHomeView = homeView;
    }

    @Override
    public int getCount() {
        return IHomeView.getCount();
    }

    @Override
    public RecyclerView.ViewHolder createArticleHolder(View view, FragmentManager fragmentManager, int type) {

        RecyclerView.ViewHolder viewHolder = null;
        switch (type) {
            case 0:
                viewHolder = new ArticleViewHolder(view, fragmentManager);
                break;
            case 1:
                viewHolder = new ContinueArticleViewHolder(view, fragmentManager);
                break;
            case 2:
                viewHolder = new TrendingArticleViewHolder(view);
                break;
        }
        return viewHolder;
    }
}
