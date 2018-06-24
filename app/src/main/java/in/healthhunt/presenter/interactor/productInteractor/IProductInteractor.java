package in.healthhunt.presenter.interactor.productInteractor;

import android.content.Context;

import java.util.List;
import java.util.Map;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.productResponse.ProductPostItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IProductInteractor {

    interface OnViewAllFinishListener {
        void onProductSuccess(List<ProductPostItem> items);
        void onError(RestError errorInfo);
    }

    interface OnFullViewFinishListener {
        void onProductSuccess(ProductPostItem item);
        void onError(RestError errorInfo);
    }

    interface OnProductFinishListener {
        void onProductSuccess(List<ProductPostItem> items, int type);
        void onError(RestError errorInfo);
    }

    interface OnRelatedProductFinishListener {
        void onRelatedProductSuccess(List<ProductPostItem> items, int type);
        void onError(RestError errorInfo);
    }

    interface OnCollectionProductFinishListener {
        void onCollectionProductSuccess(List<ProductPostItem> items, int type);
        void onError(RestError errorInfo);
    }

    interface OnDeleteFinishListener {
        void onProductDeleteSuccess(ProductPostItem item);
        void onError(RestError errorInfo);
    }


    void fetchAllProduct(Context context, Map<String, String> queryMap, OnViewAllFinishListener finishListener);
    void fetchFullProduct(Context context, String id, OnFullViewFinishListener finishListener);
    void fetchProduct(Context context, int type, Map<String, String> queryMap, OnProductFinishListener productFinishListener);
    void fetchRelatedProduct(Context context, int type, Map<String, String> queryMap, OnRelatedProductFinishListener finishListener);
    void fetchCollectionProduct(Context context, int type, Map<String, String> queryMap, OnCollectionProductFinishListener collectionProductFinishListener);
    void deleteProduct(Context context, String id, OnDeleteFinishListener deleteFinishListener);
}
