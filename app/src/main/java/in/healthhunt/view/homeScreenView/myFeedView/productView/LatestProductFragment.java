package in.healthhunt.view.homeScreenView.myFeedView.productView;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.commonResponse.CurrentUser;
import in.healthhunt.model.articles.commonResponse.MediaItem;
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.productPresenter.IProductPresenter;
import in.healthhunt.view.fullView.fullViewFragments.FullProductFragment;
import in.healthhunt.view.viewAll.ViewAllFragment;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class LatestProductFragment extends Fragment {

    private Unbinder mUnBinder;

    @BindView(R.id.latest_product_item_view)
    RelativeLayout mTagItemView;

    @BindView(R.id.last_page_view_all)
    TextView mViewAll;

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.product_bookmark)
    ImageView mProductBookMark;

    @BindView(R.id.latest_product_name)
    TextView mProductName;

    @BindView(R.id.latest_product_price)
    TextView mProductPrice;

    @BindView(R.id.latest_product_unit)
    TextView mProductUnit;

    private int mPos;
    private IProductPresenter IProductPresenter;
    private ProductPostItem mProductPostItem;


    public LatestProductFragment(){
    }

    @SuppressLint("ValidFragment")
    public LatestProductFragment(IProductPresenter latestProductPresenter){
        IProductPresenter = latestProductPresenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_products_article_item_view, container, false);
        mUnBinder = ButterKnife.bind(this, view);
        Bundle bundle = getArguments();

        if(bundle != null) {
            boolean isLast = bundle.getBoolean(ArticleParams.IS_LAST_PAGE);
            mPos = bundle.getInt(ArticleParams.POSITION);
            mProductPostItem = IProductPresenter.getProduct(mPos);

            if(!isLast) {
                mTagItemView.setVisibility(View.VISIBLE);
                mViewAll.setVisibility(View.GONE);
                setContent();
            }
            else {
                mTagItemView.setVisibility(View.GONE);
                mViewAll.setVisibility(View.VISIBLE);
            }
        }
        return view;
    }

    private void setContent() {

        String productName = mProductPostItem.getProduct_type_other_name();
        if(productName != null) {
            mProductName.setText(productName);
        }


        String price = mProductPostItem.getPost_price();
        if(price != null) {
            String postQuantity = mProductPostItem.getPost_quantity();
            String rs = getContext().getString(R.string.rs);
            rs = rs + " " + price + "/" + postQuantity;
            mProductPrice.setText(rs);
        }


        String postUnit = mProductPostItem.getPost_unit();
        Log.i("TAGUNIT" ," UNIT " + postUnit);
        if(postUnit != null) {
            mProductUnit.setText(postUnit);
        }

        CurrentUser currentUser = mProductPostItem.getCurrent_user();
        if(currentUser != null) {
            updateBookMark(currentUser.isBookmarked());
        }


        String articleUrl = null;
        List<MediaItem> mediaItems = mProductPostItem.getMedia();
        if (mediaItems != null && !mediaItems.isEmpty()) {
            MediaItem media = mediaItems.get(0);
            if ("image".equals(media.getMedia_type())) {
                articleUrl = media.getUrl();
            }
        }
        if(articleUrl != null) {
            Glide.with(this).load(articleUrl).placeholder(R.drawable.artical).into(mProductImage);
        }
        else {
            mProductImage.setImageResource(R.drawable.artical);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    @OnClick(R.id.latest_product_item_view)
    void onArticleClick(){
        openFullView();
    }

    @OnClick(R.id.last_page_view_all)
    void onViewAll(){
        Bundle bundle = new Bundle();
        bundle.putInt(ArticleParams.ARTICLE_TYPE, ArticleParams.LATEST_PRODUCTS);
        IProductPresenter.updateBottomNavigation();
        IProductPresenter.loadFragment(ViewAllFragment.class.getSimpleName(), bundle);
    }

    private void openFullView() {
        /*Intent intent = new Intent(getContext(), FullViewActivity.class);
        intent.putExtra(ArticleParams.ID, String.valueOf(mProductPostItem.getMedia_id()));
        intent.putExtra(ArticleParams.POST_TYPE, ArticleParams.PRODUCT);
        startActivity(intent);*/

        Bundle bundle = new Bundle();
        bundle.putInt(ArticleParams.POST_TYPE, ArticleParams.PRODUCT);
        bundle.putString(ArticleParams.ID, String.valueOf(mProductPostItem.getProduct_id()));
        IProductPresenter.loadFragment(FullProductFragment.class.getSimpleName(), bundle);
    }

    @OnClick(R.id.product_bookmark)
    void onBookMark(){
        String id = String.valueOf(mProductPostItem.getProduct_id());
        CurrentUser currentUser = mProductPostItem.getCurrent_user();
        if(currentUser != null) {
            if(!currentUser.isBookmarked()){
                IProductPresenter.bookmark(id, ArticleParams.LATEST_PRODUCTS);
            }
            else {
                IProductPresenter.unBookmark(id, ArticleParams.LATEST_PRODUCTS);
            }
        }
    }

    private void updateBookMark(boolean isBookMark) {
        Log.i("TAGBOOKMARK", "ISBOOK " + isBookMark);
        if(!isBookMark){
            mProductBookMark.setColorFilter(null);
            mProductBookMark.setImageResource(R.mipmap.ic_bookmark);
        }
        else {
            mProductBookMark.setColorFilter(ContextCompat.getColor(getContext(), R.color.hh_green_light2), PorterDuff.Mode.SRC_IN);
        }
    }
}
