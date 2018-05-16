package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.productResponse.ProductPostItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IViewAllPresenter {
    int getCount(int type);
    RecyclerView.ViewHolder createViewHolder(View view);
    List<ArticlePostItem> getAllArticles();
    List<ProductPostItem> getAllProduct();
    ArticlePostItem getArticle(int pos);
    ProductPostItem getProduct(int pos);
    void fetchAll(int type);
    int getView();
}
