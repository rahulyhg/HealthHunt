package in.healthhunt.presenter.homeScreenPresenter.shopPresenter;

import android.os.Bundle;
import android.view.View;

import java.util.List;
import java.util.Map;

import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.model.filter.DataItem;
import in.healthhunt.presenter.homeScreenPresenter.IPostPresenter;
import in.healthhunt.view.homeScreenView.shopView.ShopViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IShopPresenter extends IPostPresenter{
     ShopViewHolder createViewHolder(View view);
     void fetchMarkets();
     void fetchMarketFilters(Map<Integer, List<String>> listMap);
     ProductPostItem getProduct(int pos);
     List<ProductPostItem> getAllProducts();
     void fetchFilters(String type);
     int getFilterProductCount();
     int getFilterBrandCount();
     DataItem getProductItem(int pos);
     DataItem getBrandItem(int pos);
     void loadFragment(String fragmentName, Bundle bundle);
}
