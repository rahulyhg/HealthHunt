package in.healthhunt.view.homeScreenView.myFeedView.productView;

import android.support.v4.app.Fragment;

import in.healthhunt.model.articles.productResponse.ProductPostItem;

/**
 * Created by abhishekkumar on 5/15/18.
 */

public interface IProductView {
    int getProductCount();
    ProductPostItem getProduct(int pos);
    Fragment getFragmentArticleItem(int position);
}
