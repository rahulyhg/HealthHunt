package in.healthhunt.presenter.interactor.shopInteractor;

import android.content.Context;

import java.util.Map;

import in.healthhunt.presenter.interactor.bookMarkInteractor.IBookMarkInteractor;

/**
 * Created by abhishekkumar on 5/6/18.
 */

public class ShopInteractorImpl implements IShopInteractor {

    @Override
    public void bookmark(Context context, String id, int type, IBookMarkInteractor.OnFinishListener finishListener) {

    }

    @Override
    public void unBookmark(Context context, String id, int type, IBookMarkInteractor.OnFinishListener finishListener) {

    }

    @Override
    public void fetchShop(Context context, Map<String, String> queryMap, OnFinishListener tagLoadFinishListener) {

    }
}
