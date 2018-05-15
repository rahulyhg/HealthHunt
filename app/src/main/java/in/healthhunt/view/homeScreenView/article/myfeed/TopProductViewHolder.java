package in.healthhunt.view.homeScreenView.article.myfeed;

import android.content.Context;
import android.content.Intent;
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
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.ITopProductPresenter;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.TopProductPresenterImp;
import in.healthhunt.view.fullView.FullViewActivity;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TopProductViewHolder extends RecyclerView.ViewHolder implements ITopProductView, TopProductAdapter.ClickListener {


    @BindView(R.id.top_product_article_name)
    TextView mTopArticleName;

    @BindView(R.id.top_product_recycler_list)
    RecyclerView mTopProductViewer;


    private ITopProductPresenter ITopProductPresenter;
    private IMyFeedView IMyFeedView;
    private Context mContext;

    public TopProductViewHolder(View articleView, IMyFeedView myFeedView) {
        super(articleView);
        mContext = articleView.getContext();
        IMyFeedView = myFeedView;
        ButterKnife.bind(this, articleView);
        ITopProductPresenter = new TopProductPresenterImp(mContext, this);
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
    public int getProductCount() {
        List<ProductPostItem> list = IMyFeedView.getTopProductArticles();
        int count = 0;
        if(list != null && !list.isEmpty()) {
            count = list.size();
        }
        return count;
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
    public void ItemClicked(View v, int position) {
        ProductPostItem postsItem = ITopProductPresenter.getTopProduct(position);
        if(postsItem != null) {
            Intent intent = new Intent(mContext, FullViewActivity.class);
            intent.putExtra(ArticleParams.ID, postsItem.getId());
            mContext.startActivity(intent);
        }
    }
}