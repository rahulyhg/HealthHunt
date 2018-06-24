package in.healthhunt.presenter.homeScreenPresenter.myHuntPresenter.myHuntsShopPresenter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.presenter.homeScreenPresenter.IPostPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IMyHuntsProductsPresenter extends IPostPresenter {
     RecyclerView.ViewHolder createViewHolder(View view);
     List<ProductPostItem> getProductList();
     void updateDownloadList(List<ProductPostItem> productList);
     ProductPostItem getProduct(int pos);
     void loadFragment(String fragmentName, Bundle bundle);
     void fetchProducts(String userId);
     void updateProductSaved(ProductPostItem postItem);
     void deleteProduct(String id);
}
