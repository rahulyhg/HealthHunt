package in.healthhunt.view.homeScreenView.article.myfeed;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.model.beans.VerticalSpaceDecoration;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.ITopProductPresenter;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.TopProductPresenterImp;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TopProductViewHolder extends RecyclerView.ViewHolder implements ITopProductView {


    @BindView(R.id.top_product_article_name)
    TextView mTopArticleName;

    @BindView(R.id.top_product_recycler_list)
    RecyclerView mTopProductViewer;


    private ITopProductPresenter ITopProductPresenter;
    private Context mContext;

    public TopProductViewHolder(View articleView) {
        super(articleView);
        mContext = articleView.getContext();
        ButterKnife.bind(this, articleView);
        ITopProductPresenter = new TopProductPresenterImp(mContext, this);
        setAdapter();

    }

    private void setAdapter() {
        TopProductAdapter topProductAdapter = new TopProductAdapter(2, ITopProductPresenter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mTopProductViewer.setLayoutManager(layoutManager);
        mTopProductViewer.addItemDecoration(new VerticalSpaceDecoration
                (HealthHuntUtility.dpToPx(8, mContext)));
        mTopProductViewer.setAdapter(topProductAdapter);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public RecyclerView.ViewHolder createArticleHolder(View view) {
        return new TopProductAdapter.TopProductItemViewHolder(view);
    }
}