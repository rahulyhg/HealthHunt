package in.healthhunt.view.homeScreenView.article.myfeed;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.beans.SpaceDecoration;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.ITrendingSponsoredPresenter;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.TrendingSponsoredPresenterImp;
import in.healthhunt.view.fullView.FullViewActivity;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class SponsoredArticleViewHolder extends RecyclerView.ViewHolder implements ITrendingSponsoredView, SponsoredAdapter.ClickListener{


    @BindView(R.id.sponsored_article_name)
    TextView mSponsoredArticleName;

    @BindView(R.id.sponsored_recycler_list)
    RecyclerView mSponsoredViewer;

    private ITrendingSponsoredPresenter ISponsoredPresenter;
    private IMyFeedView IMyFeedView;
    private Context mContext;
    private int mIndex;
    private View mView;

    public SponsoredArticleViewHolder(View articleView, IMyFeedView feedView, int index) {
        super(articleView);
        mContext = articleView.getContext();
        mView = articleView;
        IMyFeedView = feedView;
        mIndex = index;
        ButterKnife.bind(this, articleView);
        ISponsoredPresenter = new TrendingSponsoredPresenterImp(mContext, this);
        setAdapter();

    }

    private void setAdapter() {
        Log.i("TAG122223", " data " + ISponsoredPresenter.getCount() );
        SponsoredAdapter sponsoredAdapter = new SponsoredAdapter(mContext, ISponsoredPresenter);
        sponsoredAdapter.setClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mSponsoredViewer.setLayoutManager(layoutManager);
        mSponsoredViewer.addItemDecoration(new SpaceDecoration(HealthHuntUtility.dpToPx(8, mContext), SpaceDecoration.VERTICAL));
        mSponsoredViewer.setAdapter(sponsoredAdapter);
    }

    @OnClick(R.id.cross_image)
    void onCrossClick() {
        IMyFeedView.onClickCrossView(getAdapterPosition());
    }

    @Override
    public int getArticleCount() {
        List<ArticlePostItem> list = IMyFeedView.getSponsoredArticles();
        int count = 0;
        if(list != null && !list.isEmpty()) {
            count = list.size();
        }
        return count;
    }

    @Override
    public ArticlePostItem getTrendingArticle(int pos) {
        return null;
    }

    @Override
    public ArticlePostItem getSponsoredArticle(int pos) {
        List<ArticlePostItem> list = IMyFeedView.getSponsoredArticles();
        ArticlePostItem postsItem = null;
        if(list != null && !list.isEmpty()){
            postsItem = list.get(pos);
        }
        return postsItem;
    }

    @Override
    public void ItemClicked(View v, int position) {
        ArticlePostItem postsItem = ISponsoredPresenter.getSponsoredArticles(position);
        if(postsItem != null) {
            Intent intent = new Intent(mContext, FullViewActivity.class);
            intent.putExtra(ArticleParams.ID, postsItem.getId());
            mContext.startActivity(intent);
        }
    }
}