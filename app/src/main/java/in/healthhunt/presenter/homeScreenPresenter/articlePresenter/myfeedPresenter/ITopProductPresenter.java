package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

import in.healthhunt.model.articles.productResponse.ProductPostItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface ITopProductPresenter extends IProductPresenter{
    ProductPostItem getTopProduct(int pos);
}
