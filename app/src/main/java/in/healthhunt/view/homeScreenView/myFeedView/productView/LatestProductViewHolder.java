package in.healthhunt.view.homeScreenView.myFeedView.productView;

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
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.productPresenter.IProductPresenter;
import in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.productPresenter.ProductPresenterImp;
import in.healthhunt.view.homeScreenView.myFeedView.IMyFeedView;
import in.healthhunt.view.viewAll.ViewAllFragment;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class LatestProductViewHolder extends RecyclerView.ViewHolder implements IProductView {

    @BindView(R.id.view_all)
    LinearLayout mViewAll;

    @BindView(R.id.latest_article_name)
    TextView mLatestArticleName;

    @BindView(R.id.latest_product_article_pager)
    ViewPager mLatestArticlePager;


    private IProductPresenter IProductPresenter;
    private in.healthhunt.view.homeScreenView.myFeedView.IMyFeedView IMyFeedView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    public LatestProductViewHolder(View articleView, FragmentManager fragmentManager, IMyFeedView feedView) {
        super(articleView);
        mContext = articleView.getContext();
        IMyFeedView = feedView;
        ButterKnife.bind(this, articleView);
        mFragmentManager = fragmentManager;
        IProductPresenter = new ProductPresenterImp(mContext, this);
        setAdapter();

    }

    private void setAdapter() {
        LatestProductAdapter productAdapter = new LatestProductAdapter(mFragmentManager,  IProductPresenter, ArticleParams.LATEST_PRODUCTS);
        mLatestArticlePager.setAdapter(productAdapter);
        mLatestArticlePager.setClipToPadding(false);
        mLatestArticlePager.setPadding(0, 0, HealthHuntUtility.dpToPx(100, mContext),0);
        mLatestArticlePager.setPageMargin(HealthHuntUtility.dpToPx(8, mContext));
    }

    @Override
    public Fragment getFragmentArticleItem(int position) {
        return new LatestProductFragment(IProductPresenter);
    }

    @Override
    public void loadFragment(String fragmentName, Bundle bundle) {
            IMyFeedView.loadFragment(fragmentName, bundle);
    }

    @Override
    public void updateAdapter() {
        mLatestArticlePager.getAdapter().notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        List<ProductPostItem> list = IMyFeedView.getLatestProductArticles();
        int count = 0;
        if(list != null) {
            count = list.size();
        }
        return count;
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
    public ProductPostItem getProduct(int pos) {
        List<ProductPostItem> list = IMyFeedView.getLatestProductArticles();
        ProductPostItem postItem = null;
        if(list != null) {
            postItem = list.get(pos);
        }
        return postItem;
    }

    @OnClick(R.id.view_all)
    void onViewAll(){
        Bundle bundle = new Bundle();
        bundle.putString("article_name", "Latest products");
        IMyFeedView.onClickViewAll(ViewAllFragment.class.getSimpleName(), bundle);
    }

    private void openViewAllFragment() {
        IMyFeedView.updateBottomNavigation();
        Bundle bundle = new Bundle();
        bundle.putInt(ArticleParams.ARTICLE_TYPE, ArticleParams.LATEST_PRODUCTS);
        IMyFeedView.onClickViewAll(ViewAllFragment.class.getSimpleName(), bundle);
    }

    public void notifyDataChanged() {
        mLatestArticlePager.getAdapter().notifyDataSetChanged();
    }
}