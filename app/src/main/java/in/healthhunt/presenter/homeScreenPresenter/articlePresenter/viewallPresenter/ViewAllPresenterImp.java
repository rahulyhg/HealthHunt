package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import in.healthhunt.view.homeScreenView.article.viewall.IViewAll;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ViewAllPresenterImp implements IViewAllPresenter {

    private String TAG = ViewAllPresenterImp.class.getSimpleName();
    private IViewAll IViewAll;
    private Context mContext;

    public ViewAllPresenterImp(Context context, IViewAll viewAll) {
        mContext =  context;
        IViewAll = viewAll;
    }

    @Override
    public int getCount() {
        return IViewAll.getCount();
    }

    @Override
    public RecyclerView.ViewHolder createArticleHolder(View view) {
        return IViewAll.onCreateViewHolder(view);
    }
}
