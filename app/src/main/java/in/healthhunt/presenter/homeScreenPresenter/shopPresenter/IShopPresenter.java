package in.healthhunt.presenter.homeScreenPresenter.shopPresenter;

import android.view.View;

import in.healthhunt.presenter.homeScreenPresenter.IPostPresenter;
import in.healthhunt.view.homeScreenView.shopView.ShopViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IShopPresenter extends IPostPresenter{
     ShopViewHolder createViewHolder(View view);
}
