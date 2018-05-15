package in.healthhunt.view.homeScreenView.article.myfeed;

import in.healthhunt.model.articles.productResponse.ProductPostItem;

/**
 * Created by abhishekkumar on 5/15/18.
 */

public interface IProduct {
    int getProductCount();
    ProductPostItem getProduct(int pos);
}
