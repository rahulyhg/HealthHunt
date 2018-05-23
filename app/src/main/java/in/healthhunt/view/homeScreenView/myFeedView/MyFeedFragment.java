package in.healthhunt.view.homeScreenView.myFeedView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.IMyFeedPresenter;
import in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.MyFeedPresenterImp;
import in.healthhunt.view.homeScreenView.HomeActivity;
import in.healthhunt.view.homeScreenView.IHomeView;
import in.healthhunt.view.homeScreenView.myFeedView.articleView.ArticleViewHolder;
import in.healthhunt.view.homeScreenView.myFeedView.articleView.ContinueArticleViewHolder;
import in.healthhunt.view.homeScreenView.myFeedView.articleView.LatestArticleViewHolder;
import in.healthhunt.view.homeScreenView.myFeedView.articleView.SponsoredAdapter;
import in.healthhunt.view.homeScreenView.myFeedView.articleView.SponsoredArticleViewHolder;
import in.healthhunt.view.homeScreenView.myFeedView.articleView.TrendingAdapter;
import in.healthhunt.view.homeScreenView.myFeedView.articleView.TrendingArticleViewHolder;
import in.healthhunt.view.homeScreenView.myFeedView.productView.LatestProductViewHolder;
import in.healthhunt.view.homeScreenView.myFeedView.productView.TopProductAdapter;
import in.healthhunt.view.homeScreenView.myFeedView.productView.TopProductViewHolder;

/**
 * Created by abhishekkumar on 5/3/18.
 */

public class MyFeedFragment extends Fragment implements IMyFeedView {

    @BindView(R.id.my_feed_recycler_list)
    RecyclerView mFeedViewer;

    private IMyFeedPresenter IMyFeedPresenter;
    private FragmentManager mFragmentManager;
    private MyFeedAdapter mFeedAdapter;
    private IHomeView IHomeView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IMyFeedPresenter = new MyFeedPresenterImp(getContext(), this);
        IHomeView = (IHomeView) getActivity();
        IMyFeedPresenter.fetchData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_feed, container, false);
        ButterKnife.bind(this, view);

        Log.i("TagFrag", "MyFeed Fragment");
        mFragmentManager = getFragmentManager();
        IHomeView.updateTitle(getString(R.string.my_feed));
        setBottomNavigation();
        setAdapter();
        return view;
    }

    private void setAdapter() {
        mFeedAdapter = new MyFeedAdapter(IMyFeedPresenter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mFeedViewer.setLayoutManager(layoutManager);
        mFeedViewer.setNestedScrollingEnabled(false);
        mFeedViewer.setAdapter(mFeedAdapter);
    }

    @Override
    public int getCount() {
        return IMyFeedPresenter.getArticlesType().size();
    }

    @Override
    public RecyclerView.ViewHolder createArticleHolder(View view, int type) {

        RecyclerView.ViewHolder viewHolder = null;
        switch (type) {
            case ArticleParams.BASED_ON_TAGS:
                viewHolder = new ArticleViewHolder(view, mFragmentManager, this);
                break;

            case ArticleParams.CONTINUE_ARTICLES:
                viewHolder = new ContinueArticleViewHolder(view, mFragmentManager, this, type);
                break;

            case ArticleParams.TRENDING_ARTICLES:
                viewHolder = new TrendingArticleViewHolder(view, this);
                break;

            case ArticleParams.SPONSORED_ARTICLES:
                viewHolder = new SponsoredArticleViewHolder(view, this, type);
                break;

            case ArticleParams.TOP_PRODUCTS:
                viewHolder = new TopProductViewHolder(view, this);
                break;

            case ArticleParams.LATEST_ARTICLES:
                viewHolder = new LatestArticleViewHolder(view, mFragmentManager, this);
                break;

            case ArticleParams.LATEST_PRODUCTS:
                viewHolder = new LatestProductViewHolder(view, mFragmentManager, this);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onClickViewAll(String tag, Bundle bundle) {
        ((HomeActivity)getActivity()).getHomePresenter().loadFragment(tag, bundle);
    }

    @Override
    public void onClickCrossView(int pos) {
        IMyFeedPresenter.deleteArticleType(pos);
        Log.i("TAG1111", "pos " + pos);
        Log.i("TAG1111", "size " + IMyFeedPresenter.getArticlesType().size());
        mFeedAdapter.deleteItem(pos);
    }

    @Override
    public void onLoadComplete() {
        IHomeView.hideProgress();
    }

    @Override
    public List<ArticlePostItem> getTagArticles() {
        return IMyFeedPresenter.getTagArticles();
    }

    @Override
    public List<ArticlePostItem> getTrendingArticles() {
        return IMyFeedPresenter.getTrendingArticles();
    }

    @Override
    public List<ArticlePostItem> getLatestArticles() {
        return IMyFeedPresenter.getLatestArticles();
    }

    @Override
    public List<ArticlePostItem> getSponsoredArticles() {
        return IMyFeedPresenter.getSponsoredArticles();
    }

    @Override
    public List<ProductPostItem> getTopProductArticles() {
        return IMyFeedPresenter.getTopProductArticles();
    }

    @Override
    public List<ProductPostItem> getLatestProductArticles() {
        return IMyFeedPresenter.getLatestProductArticles();
    }

    @Override
    public void updateAdapter() {
        if(mFeedAdapter != null) {
            mFeedAdapter.notifyDataSetChanged();
            int count = mFeedViewer.getChildCount();
            for(int i=0; i<count; i++) {
                RecyclerView.ViewHolder viewHolder = mFeedViewer.getChildViewHolder(mFeedViewer.getChildAt(i));
                if(viewHolder instanceof ArticleViewHolder) {
                    ((ArticleViewHolder) viewHolder).notifyDataChanged();
                }
                else if(viewHolder instanceof TrendingAdapter.TrendingItemViewHolder) {
                    ((TrendingAdapter.TrendingItemViewHolder) viewHolder).notifyDataChanged();
                }
                else if(viewHolder instanceof LatestArticleViewHolder) {
                    ((LatestArticleViewHolder) viewHolder).notifyDataChanged();
                }
                else if(viewHolder instanceof SponsoredAdapter.SponsoredItemViewHolder) {
                    ((SponsoredAdapter.SponsoredItemViewHolder) viewHolder).notifyDataChanged();
                }
                else if(viewHolder instanceof TopProductAdapter.TopProductItemViewHolder) {
                    ((TopProductAdapter.TopProductItemViewHolder) viewHolder).notifyDataChanged();
                }
                else if(viewHolder instanceof LatestProductViewHolder) {
                    ((LatestProductViewHolder) viewHolder).notifyDataChanged();
                }
            }
        }
    }

    @Override
    public void setBottomNavigation() {
        IHomeView.setBottomNavigation();
    }

    @Override
    public void showAlert(String msg) {
        IHomeView.showHomeAlert(msg);
    }

    @Override
    public int getView(int type) {
        int layout = 0;
        switch (type){
            case ArticleParams.BASED_ON_TAGS:
                layout = R.layout.article_view;
                break;

            case ArticleParams.CONTINUE_ARTICLES:
                layout = R.layout.continue_article_view;
                break;

            case ArticleParams.TRENDING_ARTICLES:
                layout = R.layout.trending_article_view;
                break;

            case ArticleParams.SPONSORED_ARTICLES:
                layout = R.layout.sponsored_article_view;
                break;

            case ArticleParams.TOP_PRODUCTS:
                layout = R.layout.top_products_article_view;
                break;

            case ArticleParams.LATEST_ARTICLES:
                layout = R.layout.latest_article_view;
                break;

            case ArticleParams.LATEST_PRODUCTS:
                layout = R.layout.latest_products_article_view;
                break;
        }
        return layout;
    }

    @Override
    public void showProgress() {
        IHomeView.showProgress();
    }

    @Override
    public void hideProgress() {
        IHomeView.hideProgress();
    }

    @Override
    public void updateBottomNavigation() {
        IHomeView.updateBottomNavigation();
    }

    @Override
    public void loadFragment(String fragmentName, Bundle bundle) {
        IHomeView.getHomePresenter().loadFragment(fragmentName, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
