package in.healthhunt.view.homeScreenView.myFeedView.productView;

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
import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.model.beans.SpaceDecoration;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.productPresenter.IProductPresenter;
import in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.productPresenter.ProductPresenterImp;
import in.healthhunt.view.fullView.FullViewActivity;
import in.healthhunt.view.homeScreenView.myFeedView.IMyFeedView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TopProductViewHolder extends RecyclerView.ViewHolder implements TopProductAdapter.ClickListener, IProductView {


    @BindView(R.id.top_product_article_name)
    TextView mTopArticleName;

    @BindView(R.id.top_product_recycler_list)
    RecyclerView mTopProductViewer;


    private IProductPresenter ITopProductPresenter;
    private in.healthhunt.view.homeScreenView.myFeedView.IMyFeedView IMyFeedView;
    private Context mContext;

    public TopProductViewHolder(View articleView, IMyFeedView myFeedView) {
        super(articleView);
        mContext = articleView.getContext();
        IMyFeedView = myFeedView;
        ButterKnife.bind(this, articleView);
        ITopProductPresenter = new ProductPresenterImp(mContext, this);
        setAdapter();

    }

    private void setAdapter() {
        TopProductAdapter topProductAdapter = new TopProductAdapter(mContext, ITopProductPresenter);
        topProductAdapter.setClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mTopProductViewer.setLayoutManager(layoutManager);
        mTopProductViewer.addItemDecoration(new SpaceDecoration(HealthHuntUtility.dpToPx(8, mContext), SpaceDecoration.VERTICAL));
        mTopProductViewer.setAdapter(topProductAdapter);
    }

    @Override
    public int getCount() {
        List<ProductPostItem> list = IMyFeedView.getTopProductArticles();
        int count = 0;
        if(list != null && !list.isEmpty()) {
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
        List<ProductPostItem> list = IMyFeedView.getTopProductArticles();
        ProductPostItem postsItem = null;
        if(list != null && !list.isEmpty()){
            postsItem = list.get(pos);
        }
        return postsItem;
    }

    @Override
    public Fragment getFragmentArticleItem(int position) {
        return null;
    }

    @Override
    public void loadFragment(String fragmentName, Bundle bundle) {
        IMyFeedView.loadFragment(fragmentName, bundle);
    }

    @Override
    public void updateAdapter() {
        mTopProductViewer.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void ItemClicked(View v, int position) {
        ProductPostItem postsItem = ITopProductPresenter.getProduct(position);
        if(postsItem != null) {
            Intent intent = new Intent(mContext, FullViewActivity.class);
            intent.putExtra(ArticleParams.ID, String.valueOf(postsItem.getId()));
            intent.putExtra(ArticleParams.POST_TYPE, ArticleParams.PRODUCT);
            mContext.startActivity(intent);
        }
    }
}