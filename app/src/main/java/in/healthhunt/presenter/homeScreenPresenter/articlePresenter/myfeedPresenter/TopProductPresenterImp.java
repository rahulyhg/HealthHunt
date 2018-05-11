package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import in.healthhunt.view.homeScreenView.article.myfeed.ITopProductView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TopProductPresenterImp implements ITopProductPresenter {

    private String TAG = TopProductPresenterImp.class.getSimpleName();
    private ITopProductView ITopProductView;
    private Context mContext;

    public TopProductPresenterImp(Context context, ITopProductView topProductView) {
        mContext =  context;
        ITopProductView = topProductView;
    }

    @Override
    public int getCount() {
        return ITopProductView.getCount();
    }

    @Override
    public RecyclerView.ViewHolder createArticleHolder(View view) {
        return ITopProductView.createArticleHolder(view);
    }
}
