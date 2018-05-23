package in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.productPresenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.presenter.homeScreenPresenter.IPostPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IProductPresenter extends IPostPresenter {
    ProductPostItem getProduct(int pos);
    Fragment getItem(int position, int type);
    void loadFragment(String fragmentName, Bundle bundle);
}
