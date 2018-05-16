package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.List;

import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.commonResponse.MediaItem;
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.view.homeScreenView.article.myfeed.ILatestProductView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class LatestProductPresenterImp implements ILatestProductPresenter {

    private String TAG = LatestProductPresenterImp.class.getSimpleName();
    private ILatestProductView ILatestProductView;
    private Context mContext;

    public LatestProductPresenterImp(Context context, ILatestProductView latestProductView) {
        mContext = context;
        ILatestProductView = latestProductView;
    }

    @Override
    public int getProductCount() {
        return ILatestProductView.getProductCount();
    }


    @Override
    public ProductPostItem getLatestProduct(int pos) {
        return ILatestProductView.getProduct(pos);
    }

    @Override
    public Fragment getItem(int position, int type) {
        Fragment fragment = ILatestProductView.getFragmentArticleItem(position);
        Bundle bundle = createBundle(position);
        bundle.putInt(ArticleParams.ARTICLE_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void openViewAll() {

    }

    private Bundle createBundle(int pos) {
        ProductPostItem postItem = ILatestProductView.getProduct(pos);

        Bundle bundle = new Bundle();

        if (postItem != null) {

            bundle.putString(ArticleParams.ID, String.valueOf(postItem.getId()));

            String url = null;
            List<MediaItem> mediaItems = postItem.getMedia();
            if (mediaItems != null && !mediaItems.isEmpty()) {
                MediaItem media = mediaItems.get(0);
                Log.i("TAGMedia", "media " + media);
                if ("image".equals(media.getMedia_type())) {
                    url = media.getUrl();
                    Log.i("TAGMedia", "URL " + url);

                }
            }
            bundle.putString(ArticleParams.ARTICLE_URL, url);

            String productName = postItem.getProduct_type_other_name();
            if (productName != null) {
                bundle.putString(ArticleParams.PRODUCT_NAME, productName);
            }

            String brandName = postItem.getBrand_other_name();
            if (brandName != null) {
                bundle.putString(ArticleParams.BRAND_NAME, brandName);
            }

            String price = postItem.getPost_price();
            if (price != null) {
                bundle.putString(ArticleParams.PRODUCT_PRICE, price);
            }

            String quantity = postItem.getPost_quantity();
            if (quantity != null) {
                bundle.putString(ArticleParams.PRODUCT_QUANTITY, quantity);
            }

            String unit = postItem.getPost_unit();
            if (unit != null) {
                bundle.putString(ArticleParams.PRODUCT_UNIT, unit);
            }


            int count = ILatestProductView.getProductCount();
            if (pos == count - 1) {
                bundle.putBoolean(ArticleParams.IS_LAST_PAGE, true);
            } else {
                bundle.putBoolean(ArticleParams.IS_LAST_PAGE, false);
            }
        }
        return bundle;
    }
}
