package in.healthhunt.view.homeScreenView.shopView;

import android.view.View;

import in.healthhunt.view.homeScreenView.myFeedView.IView;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public interface IShopView extends IView {
    ShopViewHolder createViewHolder(View view);
    void updateAdapter();
    void showAlert(String msg);
}
