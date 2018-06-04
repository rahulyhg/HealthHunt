package in.healthhunt.view.homeScreenView.shopView;

import android.os.Bundle;
import android.view.View;

import java.util.List;
import java.util.Map;

import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.view.homeScreenView.myFeedView.IView;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public interface IShopView extends IView {
    ShopViewHolder createViewHolder(View view);
    void updateAdapter();
    void showAlert(String msg);
    void handleFilterData(Map<Integer, List<String>> map);
    Map<Integer, List<String>> getFilterData();
    void loadFragment(String fragmentName, Bundle bundle);
    void updateProductSaved(ProductPostItem productPostItem);
}
