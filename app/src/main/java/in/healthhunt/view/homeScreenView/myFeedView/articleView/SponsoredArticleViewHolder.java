package in.healthhunt.view.homeScreenView.myFeedView.articleView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.articlePresenter.ArticlePresenterImp;
import in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.articlePresenter.IArticlePresenter;
import in.healthhunt.view.fullView.FullViewActivity;
import in.healthhunt.view.homeScreenView.myFeedView.IMyFeedView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class SponsoredArticleViewHolder extends RecyclerView.ViewHolder implements IArticleView, SponsoredAdapter.ClickListener{


    @BindView(R.id.sponsored_article_name)
    TextView mSponsoredArticleName;

    @BindView(R.id.sponsored_recycler_list)
    RecyclerView mSponsoredViewer;

    private IArticlePresenter IArticlePresenter;
    private in.healthhunt.view.homeScreenView.myFeedView.IMyFeedView IMyFeedView;
    private Context mContext;

    public SponsoredArticleViewHolder(View articleView, IMyFeedView feedView, int index) {
        super(articleView);
        mContext = articleView.getContext();
        IMyFeedView = feedView;
        ButterKnife.bind(this, articleView);
        IArticlePresenter = new ArticlePresenterImp(mContext, this);
        setAdapter();
    }

    private void setAdapter() {
        SponsoredAdapter sponsoredAdapter = new SponsoredAdapter(mContext, IArticlePresenter);
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
    public Fragment getFragmentArticleItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        List<ArticlePostItem> list = IMyFeedView.getSponsoredArticles();
        int count = 0;
        if(list != null && !list.isEmpty()) {
            count = list.size();
        }
        return count;
    }

    @Override
    public ArticlePostItem getArticle(int pos) {
        List<ArticlePostItem> list = IMyFeedView.getSponsoredArticles();
        ArticlePostItem postsItem = null;
        if(list != null && !list.isEmpty()){
            postsItem = list.get(pos);
        }
        return postsItem;
    }

    @Override
    public void showProgress() {
        IMyFeedView.showProgress();
    }

    @Override
    public void hideProgress() {
        IMyFeedView.hideProgress();
    }

    @Override
    public void updateBottomNavigation() {
        IMyFeedView.updateBottomNavigation();
    }

    @Override
    public void loadFragment(String fragmentName, Bundle bundle) {
        IMyFeedView.loadFragment(fragmentName, bundle);
    }

    @Override
    public void updateAdapter() {
        mSponsoredViewer.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void ItemClicked(View v, int position) {
        ArticlePostItem postsItem = IArticlePresenter.getArticle(position);
        if(postsItem != null) {
            Intent intent = new Intent(mContext, FullViewActivity.class);
            intent.putExtra(ArticleParams.ID, String.valueOf(postsItem.getId()));
            intent.putExtra(ArticleParams.POST_TYPE, ArticleParams.ARTICLE);
            mContext.startActivity(intent);
        }
    }
}