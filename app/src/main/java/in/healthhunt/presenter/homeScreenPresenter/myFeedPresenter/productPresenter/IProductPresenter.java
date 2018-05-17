package in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.productPresenter;

import android.support.v4.app.Fragment;

import in.healthhunt.model.articles.productResponse.ProductPostItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IProductPresenter{
    ProductPostItem getProduct(int pos);
    int getProductCount();
    Fragment getItem(int position, int type);
}
