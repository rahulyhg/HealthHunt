package in.healthhunt.view.homeScreenView.myFeedView.articleView;

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
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.articlePresenter.ArticlePresenterImp;
import in.healthhunt.view.homeScreenView.myFeedView.IMyFeedView;
import in.healthhunt.view.viewAll.ViewAllFragment;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder implements IArticleView {

    @BindView(R.id.view_all)
    LinearLayout mViewAll;

    @BindView(R.id.article_name)
    TextView mArticleName;

    @BindView(R.id.article_pager)
    ViewPager mArticlePager;


    private ArticlePresenterImp mArticlePresenter;
    private IMyFeedView IMyFeedView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private ArticleAdapter mArticleAdapter;

    public ArticleViewHolder(View articleView, FragmentManager fragmentManager, IMyFeedView feedView) {
        super(articleView);
        mContext = articleView.getContext();
        ButterKnife.bind(this, articleView);
        mFragmentManager = fragmentManager;
        IMyFeedView = feedView;
        mArticlePresenter = new ArticlePresenterImp(mContext, this);
        setAdapter();

    }

    private void setAdapter() {
        mArticleAdapter = new ArticleAdapter(mFragmentManager,  mArticlePresenter, ArticleParams.BASED_ON_TAGS);
        mArticlePager.setAdapter(mArticleAdapter);
        mArticlePager.setClipToPadding(false);
        mArticlePager.setPadding(0, 0, HealthHuntUtility.dpToPx(100, mContext),0);
        mArticlePager.setPageMargin(HealthHuntUtility.dpToPx(6, mContext));
    }

    @Override
    public Fragment getFragmentArticleItem(int position) {
        return new ArticleFragment(mArticlePresenter);
    }

    @Override
    public int getCount() {
        List<ArticlePostItem> list = IMyFeedView.getTagArticles();
        int count = 0;
        if(list != null) {
            count = list.size();
        }
        return count;
    }

    @Override
    public ArticlePostItem getArticle(int pos) {
        List<ArticlePostItem> list = IMyFeedView.getTagArticles();
        ArticlePostItem postItem = null;
        if(list != null) {
            postItem = list.get(pos);
        }
        return postItem;
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
        IMyFeedView.loadNonFooterFragment(fragmentName, bundle);
    }

    @Override
    public void updateAdapter() {
        mArticlePager.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void updateSavedData(ArticlePostItem articlePostItem) {
        IMyFeedView.updateArticleSaved(articlePostItem);
    }

    private void openViewAllFragment() {
        IMyFeedView.updateBottomNavigation();
        Bundle bundle = new Bundle();
        bundle.putInt(ArticleParams.ARTICLE_TYPE, ArticleParams.BASED_ON_TAGS);
        IMyFeedView.onClickViewAll(ViewAllFragment.class.getSimpleName(), bundle);
    }

    @OnClick(R.id.view_all)
    void onViewAll(){
        openViewAllFragment();
    }

    public void notifyDataChanged() {
        mArticleAdapter.notifyDataSetChanged();
    }
}