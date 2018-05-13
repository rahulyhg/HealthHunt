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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.PostsItem;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.ArticlePresenterImp;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.IArticlePresenter;
import in.healthhunt.view.homeScreenView.article.viewall.ViewAllFragment;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class LatestArticleViewHolder extends RecyclerView.ViewHolder implements IArticleView {

    @BindView(R.id.latest_view_all)
    LinearLayout mViewAll;

    @BindView(R.id.latest_article_name)
    TextView mArticleName;

    @BindView(R.id.latest_article_pager)
    ViewPager mArticlePager;


    boolean isClicked;

    private IArticlePresenter IArticlePresenter;
    private IMyFeedView IMyFeedView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    public LatestArticleViewHolder(View articleView, FragmentManager fragmentManager, IMyFeedView feedView) {
        super(articleView);
        mContext = articleView.getContext();
        ButterKnife.bind(this, articleView);
        mFragmentManager = fragmentManager;
        IMyFeedView = feedView;
        IArticlePresenter = new ArticlePresenterImp(mContext, this);
        setAdapter();
    }

    private void setAdapter() {
        ArticleAdapter articleAdapter = new ArticleAdapter(mFragmentManager,  IArticlePresenter, ArticleParams.LATEST_ARTICLES);
        mArticlePager.setAdapter(articleAdapter);
        mArticlePager.setClipToPadding(false);
        mArticlePager.setPadding(0, 0, HealthHuntUtility.dpToPx(100, mContext),0);
        mArticlePager.setPageMargin(HealthHuntUtility.dpToPx(6, mContext));
    }

    @Override
    public Fragment getFragmentArticleItem(int position) {
        return new ArticleFragment();
    }

    @Override
    public int getArticleCount() {
        List<PostsItem> list = IMyFeedView.getLatestArticles();
        int count = 0;
        if(list != null) {
            count = list.size();
        }
        return count;
    }

    @Override
    public void showFragment(String tag, Bundle bundle) {

    }

    @Override
    public PostsItem getArticle(int pos) {
        List<PostsItem> list = IMyFeedView.getLatestArticles();
        PostsItem postItem = null;
        if(list != null) {
            postItem = list.get(pos);
        }
        return postItem;
    }

    @OnClick(R.id.latest_view_all)
    void onViewAll(){
        openViewAllFragment();
    }

    private void openViewAllFragment() {
        IMyFeedView.updateNavigation();
        Bundle bundle = new Bundle();
        bundle.putInt(ArticleParams.ARTICLE_TYPE, ArticleParams.LATEST_ARTICLES);
        IMyFeedView.onClickViewAll(ViewAllFragment.class.getSimpleName(), bundle);
    }

    public void notifyDataChanged() {
        mArticlePager.getAdapter().notifyDataSetChanged();
    }
}