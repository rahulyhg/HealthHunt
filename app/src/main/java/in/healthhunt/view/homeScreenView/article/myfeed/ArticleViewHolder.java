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
import in.healthhunt.view.homeScreenView.article.viewall.ViewAllFragment;

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


    boolean isClicked;

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
        mArticleAdapter = new ArticleAdapter(mFragmentManager,  mArticlePresenter);
        mArticlePager.setAdapter(mArticleAdapter);
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
        List<PostsItem> list = IMyFeedView.getTagArticles();
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
    public PostsItem getTagArticle(int pos) {
        List<PostsItem> list = IMyFeedView.getTagArticles();
        PostsItem postItem = null;
        if(list != null) {
            postItem = list.get(pos);
        }
        return postItem;
    }

    private void openViewAllFragment() {
        IMyFeedView.updateNavigation();
        Bundle bundle = new Bundle();
        bundle.putInt(ArticleParams.ARTICLE_TYPE, ArticleParams.BASED_ON_TAGS);
        IMyFeedView.onClickViewAll(ViewAllFragment.class.getSimpleName(), bundle);
    }

    @OnClick(R.id.view_all)
    void onViewAll(){
        openViewAllFragment();
    }

    public void notifyDataChanged() {
        /*for(int i=0; i<mArticlePager.getChildCount(); i++) {
            Log.i("TAG11234", " view = " + mArticlePager.getAdapter().)
        }*/

        mArticleAdapter.notifyDataSetChanged();
    }

}