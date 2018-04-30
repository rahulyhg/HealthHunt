package in.healthhunt.presenter.homeScreenPresenter;

import android.support.v4.app.Fragment;

/**
 * Created by abhishekkumar on 4/24/18.
 */

public interface IArticleItemPresenter {

    int getCount();
    Fragment getItem(int position);
}
