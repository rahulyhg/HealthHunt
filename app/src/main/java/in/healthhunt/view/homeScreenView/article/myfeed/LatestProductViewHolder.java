package in.healthhunt.view.homeScreenView.article.myfeed;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.model.articles.articleResponse.PostsItem;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.ArticlePresenterImp;
import in.healthhunt.view.homeScreenView.article.viewall.ViewAllFragment;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class LatestProductViewHolder extends RecyclerView.ViewHolder implements IArticleView {

    @BindView(R.id.view_all)
    LinearLayout mViewAll;

    @BindView(R.id.latest_article_name)
    TextView mLatestArticleName;

    @BindView(R.id.latest_product_article_pager)
    ViewPager mLatestArticlePager;


    private ArticlePresenterImp mArticlePresenter;
    private IMyFeedView IMyFeedView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    public LatestProductViewHolder(View articleView, FragmentManager fragmentManager, IMyFeedView feedView) {
        super(articleView);
        mContext = articleView.getContext();
        IMyFeedView = feedView;
        ButterKnife.bind(this, articleView);
        mFragmentManager = fragmentManager;
        mArticlePresenter = new ArticlePresenterImp(mContext, this);
        setAdapter();

    }

    private void setAdapter() {
        ArticleAdapter articleAdapter = new ArticleAdapter(mFragmentManager,  mArticlePresenter);
        mLatestArticlePager.setAdapter(articleAdapter);
        mLatestArticlePager.setClipToPadding(false);
        mLatestArticlePager.setPadding(0, 0, HealthHuntUtility.dpToPx(100, mContext),0);
        mLatestArticlePager.setPageMargin(HealthHuntUtility.dpToPx(8, mContext));
    }

    @Override
    public Fragment getFragmentArticleItem(int position) {
        return new LatestProductFragment();
    }

    @Override
    public int getArticleCount() {
        return 5;
    }

    @Override
    public void showFragment(String tag, Bundle bundle) {

    }

    @Override
    public PostsItem getTagArticle(int pos) {
        return null;
    }

    @OnClick(R.id.view_all)
    void onViewAll(){
        Bundle bundle = new Bundle();
        bundle.putString("article_name", "Latest products");
        IMyFeedView.onClickViewAll(ViewAllFragment.class.getSimpleName(), bundle);
    }
}