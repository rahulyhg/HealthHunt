package in.healthhunt.presenter.homeScreenPresenter.articlePresenter;

import android.content.Context;

import in.healthhunt.view.homeScreenView.article.ITrendingSponsoredView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TrendingSponsoredPresenterImp implements ITrendingSponsoredPresenter {

    private String TAG = TrendingSponsoredPresenterImp.class.getSimpleName();
    private ITrendingSponsoredView ITrendingSponsoredView;
    private Context mContext;

    public TrendingSponsoredPresenterImp(Context context, ITrendingSponsoredView trendingSponsoredView) {
        mContext =  context;
        ITrendingSponsoredView = trendingSponsoredView;
    }

    @Override
    public int getCount() {
        return ITrendingSponsoredView.getCount();
    }

    /*@Override
    public RecyclerView.ViewHolder createArticleHolder(View view) {
        return new TrendingArticleViewHolder(view);
    }*/
}
