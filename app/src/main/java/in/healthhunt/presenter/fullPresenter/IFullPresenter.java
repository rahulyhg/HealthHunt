package in.healthhunt.presenter.fullPresenter;

import android.os.Bundle;

import in.healthhunt.model.articles.postResponse.ArticlePost;
import in.healthhunt.model.articles.productResponse.ProductPostItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IFullPresenter {
    void loadFragment(String tag, Bundle bundle);
    void fetchArticle(String id);
    ArticlePost getArticle();
    ProductPostItem getProduct();
    void bookmark(String id);
    void unBookmark(String id);
}

