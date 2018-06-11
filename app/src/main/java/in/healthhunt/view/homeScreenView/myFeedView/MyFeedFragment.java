package in.healthhunt.view.homeScreenView.myFeedView;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.IMyFeedPresenter;
import in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.MyFeedPresenterImp;
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

    @BindView(R.id.category_viewer)
    LinearLayout mCategoryViewer;

    @BindView(R.id.horizontalScrollView)
    HorizontalScrollView mHorizontalScrollView;

    private IMyFeedPresenter IMyFeedPresenter;
    private FragmentManager mFragmentManager;
    private MyFeedAdapter mFeedAdapter;
    private IHomeView IHomeView;
    private RecyclerView.RecycledViewPool mPool;

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
        mPool = new RecyclerView.RecycledViewPool();
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
                ((TrendingArticleViewHolder)viewHolder).mTrendingViewer.setRecycledViewPool(mPool);
                break;

            case ArticleParams.SPONSORED_ARTICLES:
                viewHolder = new SponsoredArticleViewHolder(view, this, type);
                ((SponsoredArticleViewHolder)viewHolder).mSponsoredViewer.setRecycledViewPool(mPool);
                break;

            case ArticleParams.TOP_PRODUCTS:
                viewHolder = new TopProductViewHolder(view, this);
                ((TopProductViewHolder)viewHolder).mTopProductViewer.setRecycledViewPool(mPool);
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
        IHomeView.getHomePresenter().loadNonFooterFragment(tag, bundle);
    }

    @Override
    public void onClickCrossView(int pos) {
        IMyFeedPresenter.deleteArticleType(pos);
        Log.i("TAG1111", "pos " + pos);
        Log.i("TAG1111", "size " + IMyFeedPresenter.getArticlesType().size());
        mFeedAdapter.deleteItem(pos);
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

        updateCategoryView();
    }

    private void updateCategoryView() {
        List<String> categoryList = IHomeView.getHomePresenter().getCategoryList();

        Log.i("TAGCategoryList", "List " + categoryList);
        if(categoryList == null || categoryList.isEmpty() || categoryList.contains(Constants.All)){
            removeAllCategory();
            mHorizontalScrollView.setVisibility(View.GONE);
            return;
        }


        removeAllCategory();
        mHorizontalScrollView.setVisibility(View.VISIBLE);
        for(String name: categoryList) {
            addCategoryView(name);
        }
    }

    private void addCategoryView(String name) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.category_my_feed_item_view, null);
        ImageView image = view.findViewById(R.id.category_image);
        TextView textView = view.findViewById(R.id.category_name);


        textView.setText(name);

        if(name.equalsIgnoreCase(Constants.LOVE)){
            LinearLayout imageBack = view.findViewById(R.id.category_image_bg);
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.circle_view);
            drawable.setColorFilter(ContextCompat.getColor(getContext(), R.color.hh_red_light), PorterDuff.Mode.SRC_ATOP);
            imageBack.setBackground(drawable);
        }

        if(name.equalsIgnoreCase(Constants.FITNESS)){
            LinearLayout imageBack = view.findViewById(R.id.category_image_bg);
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.circle_view);
            drawable.setColorFilter(ContextCompat.getColor(getContext(), R.color.hh_blue_light), PorterDuff.Mode.SRC_ATOP);
            imageBack.setBackground(drawable);
        }

        int src = HealthHuntUtility.getCategoryIcon(name);
        image.setImageResource(src);

        view.setTag(name);
        mCategoryViewer.addView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IHomeView.getHomePresenter().removeCategory(view.getTag().toString());
                mCategoryViewer.removeView(view);
                // will update the articles list
                if(mCategoryViewer.getChildCount() == 0){
                    mHorizontalScrollView.setVisibility(View.GONE);
                }
                updateData();
                IHomeView.updateDrawerFragment();
            }
        });
    }

    private void removeAllCategory() {
        mCategoryViewer.removeAllViews();
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
        IHomeView.hideBottomNavigationSelection();
    }

    @Override
    public void loadNonFooterFragment(String fragmentName, Bundle bundle) {
        IHomeView.getHomePresenter().loadNonFooterFragment(fragmentName, bundle);
    }

    @Override
    public void updateArticleSaved(ArticlePostItem postItem) {
        IHomeView.updateArticleSavedData(postItem);
    }

    @Override
    public void updateProductSaved(ProductPostItem productPostItem) {
        IHomeView.updateProductSavedData(productPostItem);
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

    public void updateData(){
        IMyFeedPresenter.fetchData();
    }
}
