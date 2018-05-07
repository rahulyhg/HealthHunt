package in.healthhunt.view.homeScreenView.article.myfeed;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.model.beans.SpaceDecoration;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.TrendingSponsoredPresenterImp;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TrendingArticleViewHolder extends RecyclerView.ViewHolder implements ITrendingSponsoredView {


    @BindView(R.id.trending_article_name)
    TextView mArticleName;

    @BindView(R.id.trending_recycler_list)
    RecyclerView mTrendingViewer;

    private TrendingSponsoredPresenterImp mTrendingPresenter;
    private IMyFeedView IMyFeedView;
    private Context mContext;

    public TrendingArticleViewHolder(View articleView, IMyFeedView feedView) {
        super(articleView);
        mContext = articleView.getContext();
        IMyFeedView = feedView;
        ButterKnife.bind(this, articleView);
        mTrendingPresenter = new TrendingSponsoredPresenterImp(mContext, this);
        setAdapter();

    }

    private void setAdapter() {
        TrendingAdapter trendingAdapter = new TrendingAdapter(2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mTrendingViewer.setLayoutManager(layoutManager);
        mTrendingViewer.addItemDecoration(new SpaceDecoration(HealthHuntUtility.dpToPx(8, mContext)));
        mTrendingViewer.setAdapter(trendingAdapter);
    }

    @Override
    public int getCount() {
        return 2;
    }
}