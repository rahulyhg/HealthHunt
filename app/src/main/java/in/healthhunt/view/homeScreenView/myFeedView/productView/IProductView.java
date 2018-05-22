package in.healthhunt.view.homeScreenView.myFeedView.productView;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.view.homeScreenView.myFeedView.IView;

/**
 * Created by abhishekkumar on 5/15/18.
 */

public interface IProductView extends IView{
    ProductPostItem getProduct(int pos);
    Fragment getFragmentArticleItem(int position);
    void loadFragment(String fragmentName, Bundle bundle);
    void updateAdapter();
}
