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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.bookmarkResponse.BookMarkData;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.articlePresenter.ArticlePresenterImp;
import in.healthhunt.view.homeScreenView.myFeedView.IMyFeedView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ContinueArticleViewHolder extends RecyclerView.ViewHolder implements IArticleView {


    @BindView(R.id.article_name)
    TextView mArticleName;

    @BindView(R.id.continue_article_pager)
    ViewPager mArticlePager;

    @BindView(R.id.continue_cross_image)
    LinearLayout mCrossButton;

    @BindView(R.id.continue_view)
    LinearLayout mContinueView;


    private ArticlePresenterImp mArticlePresenter;
    private in.healthhunt.view.homeScreenView.myFeedView.IMyFeedView IMyFeedView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private View mView;
    private int mIndex;

    public ContinueArticleViewHolder(View articleView, FragmentManager fragmentManager, IMyFeedView feedView, int index) {
        super(articleView);
        mContext = articleView.getContext();
        ButterKnife.bind(this, articleView);
        mFragmentManager = fragmentManager;
        IMyFeedView = feedView;
        mView = articleView;
        mIndex = index;
        mArticlePresenter = new ArticlePresenterImp(mContext, this);
        setAdapter();

    }

    private void setAdapter() {
        ArticleAdapter articleAdapter = new ArticleAdapter(mFragmentManager,  mArticlePresenter, ArticleParams.CONTINUE_ARTICLES);
        mArticlePager.setAdapter(articleAdapter);
        mArticlePager.setClipToPadding(false);
        mArticlePager.setPadding(0, 0, HealthHuntUtility.dpToPx(100, mContext),0);
        mArticlePager.setPageMargin(HealthHuntUtility.dpToPx(8, mContext));
    }

    @Override
    public Fragment getFragmentArticleItem(int position) {
        return new ContinueArticleFragment();
    }

    @Override
    public int getCount() {
        return 5;
    }


    @Override
    public ArticlePostItem getArticle(int pos) {
        return null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void updateBottomNavigation() {
        IMyFeedView.updateBottomNavigation();
    }

    @Override
    public void loadFragment(String fragmentName, Bundle bundle) {

    }

    @Override
    public void updateBookMark(BookMarkData markResponse) {

    }

    @OnClick(R.id.continue_cross_image)
    void onCrossClick() {
        IMyFeedView.onClickCrossView(mIndex);
    }

    public void hideContinueView(){
        mContinueView.setVisibility(View.GONE);
    }

    public void showContinueView(){
        mContinueView.setVisibility(View.VISIBLE);
    }
 }