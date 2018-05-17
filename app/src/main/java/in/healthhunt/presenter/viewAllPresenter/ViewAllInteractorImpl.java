package in.healthhunt.presenter.viewAllPresenter;

import android.content.Context;

import java.util.Map;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.model.articles.articleResponse.ArticleResponse;
import in.healthhunt.model.articles.productResponse.ProductResponse;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 5/11/18.
 */

public class ViewAllInteractorImpl implements IViewAllInteractor {
    @Override
    public void fetchAllArticle(Context context, Map<String, String> queryMap, final OnFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).fetchArticles(queryMap, new ResponseResolver<ArticleResponse>() {
            @Override
            public void onSuccess(ArticleResponse response, Response response2) {
                finishListener.onArticleSuccess(response.getData().getPosts());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
    }

    @Override
    public void fetchAllProduct(Context context, Map<String, String> queryMap, final OnFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).fetchProducts(queryMap, new ResponseResolver<ProductResponse>() {
            @Override
            public void onSuccess(ProductResponse response, Response response2) {
                finishListener.onProductSuccess(response.getData().getPosts());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
    }
}
