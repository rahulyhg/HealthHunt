package in.healthhunt.view.homeScreenView;

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
import in.healthhunt.presenter.homeScreenPresenter.ArticlePresenterImp;
import in.healthhunt.presenter.homeScreenPresenter.IArticlePresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder implements IArticleView{

    @BindView(R.id.view_all)
    LinearLayout mViewAll;

    @BindView(R.id.article_name)
    TextView mArticleName;

    @BindView(R.id.article_pager)
    ViewPager mArticlePager;


    boolean isClicked;

    private ArticlePresenterImp IArticlePresenter;
    private Context mContext;
    private FragmentManager mFragmentManager;

    public ArticleViewHolder(View articleView, FragmentManager fragmentManager) {
        super(articleView);
        mContext = articleView.getContext();
        ButterKnife.bind(this, articleView);
        mFragmentManager = fragmentManager;
        IArticlePresenter = new ArticlePresenterImp(mContext, this);
        setAdapter();

    }

    private void setAdapter() {
        ArticleAdapter articleAdapter = new ArticleAdapter(mFragmentManager,  IArticlePresenter);
        mArticlePager.setAdapter(articleAdapter);
        mArticlePager.setClipToPadding(false);
        mArticlePager.setPadding(0, 0, 300 ,0);
        mArticlePager.setPageMargin(30);
    }

    @Override
    public Fragment getArticleItem(int position) {
        return new ArticleFragment();
    }

    @Override
    public int getArticleCount() {
        return 5;
    }

    @Override
    public void showFragment(String tag, Bundle bundle) {

    }
}