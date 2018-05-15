package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

import android.support.v4.app.Fragment;

import in.healthhunt.model.articles.productResponse.ProductPostItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface ILatestProductPresenter extends IProductPresenter {
    ProductPostItem getLatestProduct(int pos);
    Fragment getItem(int position, int type);
    void openViewAll();
}
