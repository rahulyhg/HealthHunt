package in.healthhunt.presenter.homeScreenPresenter.shopPresenter;

import android.content.Context;
import android.view.View;

import framework.retrofit.RestError;
import in.healthhunt.presenter.interactor.shopInteractor.IShopInteractor;
import in.healthhunt.presenter.interactor.shopInteractor.ShopInteractorImpl;
import in.healthhunt.view.homeScreenView.shopView.IShopView;
import in.healthhunt.view.homeScreenView.shopView.ShopViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ShopPresenterImp implements IShopPresenter, IShopInteractor.OnFinishListener{

    private String TAG = ShopPresenterImp.class.getSimpleName();
    private IShopInteractor IShopInteractor;
    private Context mContext;
    private IShopView IShopView;

    public ShopPresenterImp(Context context, IShopView shopView) {
        mContext =  context;
        IShopInteractor = new ShopInteractorImpl();
        IShopView = shopView;
    }

    @Override
    public void onSuccess() {
        IShopView.hideProgress();
    }

    @Override
    public void onError(RestError errorInfo) {
        IShopView.hideProgress();
        if(errorInfo != null) {
            IShopView.showAlert(errorInfo.getMessage());
        }
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public void bookmark(String id, int type) {

    }

    @Override
    public void unBookmark(String id, int type) {

    }

    @Override
    public void updateBottomNavigation() {

    }

    @Override
    public ShopViewHolder createViewHolder(View view) {
        return IShopView.createViewHolder(view);
    }
}
