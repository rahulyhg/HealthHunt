package in.healthhunt.view.homeScreenView.article;

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
import in.healthhunt.R;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.ArticlePresenterImp;
import in.healthhunt.view.HealthHuntApplication;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ContinueArticleViewHolder extends RecyclerView.ViewHolder implements IArticleView {


    @BindView(R.id.article_name)
    TextView mArticleName;

    @BindView(R.id.continue_article_pager)
    ViewPager mArticlePager;

    @BindView(R.id.cross_image)
    LinearLayout mCrossButton;


    private ArticlePresenterImp mArticlePresenter;
    private Context mContext;
    private FragmentManager mFragmentManager;

    public ContinueArticleViewHolder(View articleView, FragmentManager fragmentManager) {
        super(articleView);
        mContext = articleView.getContext();
        ButterKnife.bind(this, articleView);
        mFragmentManager = fragmentManager;
        mArticlePresenter = new ArticlePresenterImp(mContext, this);
        setAdapter();

    }

    private void setAdapter() {
        ArticleAdapter articleAdapter = new ArticleAdapter(mFragmentManager,  mArticlePresenter);
        mArticlePager.setAdapter(articleAdapter);
        mArticlePager.setClipToPadding(false);
        mArticlePager.setPadding(0, 0, HealthHuntUtility.dpToPx(100, mContext),0);
        mArticlePager.setPageMargin(HealthHuntUtility.dpToPx(8, mContext));
    }

    @Override
    public Fragment getArticleItem(int position) {
        return new ContinueArticleFragment();
    }

    @Override
    public int getArticleCount() {
        return 5;
    }

    @Override
    public void showFragment(String tag, Bundle bundle) {

    }
}