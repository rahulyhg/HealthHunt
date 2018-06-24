package in.healthhunt.presenter.interactor.productInteractor;

import android.content.Context;

import java.util.Map;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.model.articles.fullProductResponse.FullProductResponse;
import in.healthhunt.model.articles.productResponse.ProductData;
import in.healthhunt.model.deletePost.DeleteProductData;
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
    public void fetchFullProduct(Context context, String id, final OnFullViewFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).fetchFullProduct(id, new ResponseResolver<FullProductResponse>() {
            @Override
            public void onSuccess(FullProductResponse postResponse, Response response) {
                finishListener.onProductSuccess(postResponse.getData().getPost());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
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

    @Override
    public void fetchRelatedProduct(Context context, final int type, Map<String, String> queryMap, final OnRelatedProductFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).fetchProducts(queryMap, new ResponseResolver<HHResponse<ProductData>>() {
            @Override
            public void onSuccess(HHResponse<ProductData> productData, Response response2) {
                finishListener.onRelatedProductSuccess(productData.getData().getPosts(), type);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
    }

    @Override
    public void fetchCollectionProduct(Context context, int type, Map<String, String> queryMap, final OnCollectionProductFinishListener collectionProductFinishListener) {
        WebServicesWrapper.getInstance(context).fetchProducts(queryMap, new ResponseResolver<HHResponse<ProductData>>() {
            @Override
            public void onSuccess(HHResponse<ProductData> productData, Response response2) {
                // collectionProductFinishListener.onCollectionProductSuccess(productData.getData().getPosts());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                collectionProductFinishListener.onError(error);
            }
        });
    }

    @Override
    public void deleteProduct(Context context, String id, final OnDeleteFinishListener deleteFinishListener) {
        WebServicesWrapper.getInstance(context).deleteProduct(id, new ResponseResolver<HHResponse<DeleteProductData>>() {
            @Override
            public void onSuccess(HHResponse<DeleteProductData> deleteProductDataHHResponse, Response response) {
                deleteFinishListener.onProductDeleteSuccess(deleteProductDataHHResponse.getData().getPost());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                deleteFinishListener.onError(error);
            }
        });
    }
}
