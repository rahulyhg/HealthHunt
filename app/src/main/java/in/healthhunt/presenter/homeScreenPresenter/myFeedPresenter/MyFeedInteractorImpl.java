package in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter;

import android.content.Context;

import java.util.Map;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.model.articles.articleResponse.ArticleResponse;
import in.healthhunt.model.articles.productResponse.ProductResponse;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 5/10/18.
 */

public class MyFeedInteractorImpl implements IMyFeedInteractor {
    @Override
    public void fetchArticle(Context context, final int type, Map<String, String> queryMap, final OnArticleFinishListener articleFinishListener) {
        WebServicesWrapper.getInstance(context).fetchArticles(queryMap, new ResponseResolver<ArticleResponse>() {
            @Override
            public void onSuccess(ArticleResponse articleResponse, Response response) {
                    articleFinishListener.onArticleSuccess(articleResponse.getData().getPosts(), type);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                    articleFinishListener.onError(error);
            }
        });
    }

    @Override
    public void fetchProduct(Context context, final int type, Map<String, String> queryMap, final OnProductFinishListener productFinishListener) {
        WebServicesWrapper.getInstance(context).fetchProducts(queryMap, new ResponseResolver<ProductResponse>() {
            @Override
            public void onSuccess(ProductResponse productResponse, Response response) {
                productFinishListener.onProductSuccess(productResponse.getData().getPosts(), type);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                productFinishListener.onError(error);
            }
        });
    }

    /*@Override
    public void fetchTrendingArticle(Context context, Map<String, String> queryMap, final OnArticleFinishListener articleFinishListener) {
        WebServicesWrapper.getInstance(context).fetchArticles(queryMap, new ResponseResolver<ArticleResponse>() {
            @Override
            public void onSuccess(ArticleResponse articleResponse, Response response) {
                articleFinishListener.onSuccess(articleResponse.getData().getPosts(), ArticleParams.TRENDING_ARTICLES);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                articleFinishListener.onError(error);
            }
        });
    }

    @Override
    public void fetchLatestArticle(Context context, Map<String, String> queryMap, final OnArticleFinishListener articleFinishListener) {
        WebServicesWrapper.getInstance(context).fetchArticles(queryMap, new ResponseResolver<ArticleResponse>() {
            @Override
            public void onSuccess(ArticleResponse articleResponse, Response response) {
                articleFinishListener.onSuccess(articleResponse.getData().getPosts(), ArticleParams.LATEST_ARTICLES);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                articleFinishListener.onError(error);
            }
        });
    }*/
}
