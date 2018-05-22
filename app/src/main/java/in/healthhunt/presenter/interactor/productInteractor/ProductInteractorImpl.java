package in.healthhunt.presenter.interactor.productInteractor;

import android.content.Context;

import java.util.Map;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.model.articles.productResponse.ProductData;
import in.healthhunt.model.response.HHResponse;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 5/11/18.
 */

public class ProductInteractorImpl implements IProductInteractor {
    @Override
    public void fetchAllProduct(Context context, Map<String, String> queryMap, final OnViewAllFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).fetchProducts(queryMap, new ResponseResolver<HHResponse<ProductData>>() {
            @Override
            public void onSuccess(HHResponse<ProductData> productData, Response response2) {
                finishListener.onProductSuccess(productData.getData().getPosts());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
    }

    @Override
    public void fetchFullProduct(Context context, String id, OnFullViewFinishListener finishListener) {

    }

    @Override
    public void fetchProduct(Context context, final int type, Map<String, String> queryMap, final OnProductFinishListener productFinishListener) {
        WebServicesWrapper.getInstance(context).fetchProducts(queryMap, new ResponseResolver<HHResponse<ProductData>>() {
            @Override
            public void onSuccess(HHResponse<ProductData> productData, Response response) {
                productFinishListener.onProductSuccess(productData.getData().getPosts(), type);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                productFinishListener.onError(error);
            }
        });
    }
}
