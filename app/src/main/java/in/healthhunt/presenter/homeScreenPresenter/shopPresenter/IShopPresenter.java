package in.healthhunt.presenter.homeScreenPresenter.shopPresenter;

import android.view.View;

import java.util.List;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.presenter.homeScreenPresenter.IPostPresenter;
import in.healthhunt.view.homeScreenView.shopView.ShopViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IShopPresenter extends IPostPresenter{
     ShopViewHolder createViewHolder(View view);
     void fetchMarkets();
     ArticlePostItem getArticle(int pos);
     List<ArticlePostItem> getAllArticles();

}
