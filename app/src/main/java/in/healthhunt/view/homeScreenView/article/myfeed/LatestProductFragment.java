package in.healthhunt.view.homeScreenView.article.myfeed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.view.fullView.FullViewActivity;
import in.healthhunt.view.homeScreenView.HomeActivity;
import in.healthhunt.view.homeScreenView.article.viewall.ViewAllFragment;

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

    private int mType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_products_article_item_view, container, false);
        mUnBinder = ButterKnife.bind(this, view);
        Bundle bundle = getArguments();

        if(bundle != null) {
            boolean isLast = bundle.getBoolean(ArticleParams.IS_LAST_PAGE);
            mType = bundle.getInt(ArticleParams.ARTICLE_TYPE);

            if(!isLast) {
                mTagItemView.setVisibility(View.VISIBLE);
                mViewAll.setVisibility(View.GONE);
                setContent(bundle);
            }
            else {
                mTagItemView.setVisibility(View.GONE);
                mViewAll.setVisibility(View.VISIBLE);
            }
        }
        return view;
    }

    private void setContent(Bundle bundle) {

        String productName = bundle.getString(ArticleParams.PRODUCT_NAME);
        if(productName != null) {
            mProductName.setText(productName);
        }


        String price = bundle.getString(ArticleParams.PRODUCT_PRICE);
        if(price != null) {
            String postQuantity = bundle.getString(ArticleParams.PRODUCT_QUANTITY);
            String rs = getContext().getString(R.string.rs);
            rs = rs + " " + price + "/" + postQuantity;
            mProductPrice.setText(rs);
        }


        String postUnit = bundle.getString(ArticleParams.PRODUCT_UNIT);
        Log.i("TAGUNIT" ," UNIT " + postUnit);
        if(postUnit != null) {
            mProductUnit.setText(postUnit);
        }


        String articleUrl = bundle.getString(ArticleParams.ARTICLE_URL);
        if(articleUrl != null) {
            Log.i("TAG11", "articleUrl " + articleUrl);
            Glide.with(this).load(articleUrl).placeholder(R.drawable.artical).into(mProductImage);
        }
        else {
            mProductImage.setBackgroundResource(R.drawable.artical);
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
        bundle.putInt(ArticleParams.ARTICLE_TYPE, mType);
        ((HomeActivity)getActivity()).updateNavigation();
        ((HomeActivity)getActivity()).getHomePresenter().loadFragment(ViewAllFragment.class.getSimpleName(), bundle);
    }

    private void openFullView() {
        Bundle bundle = getArguments();

        Intent intent = new Intent(getContext(), FullViewActivity.class);
        if(bundle != null) {
            intent.putExtra(ArticleParams.ID, bundle.getString(ArticleParams.ID));
        }
        startActivity(intent);
    }

}
