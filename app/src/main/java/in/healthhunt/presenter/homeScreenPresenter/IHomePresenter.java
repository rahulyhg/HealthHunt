package in.healthhunt.presenter.homeScreenPresenter;

import android.os.Bundle;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IHomePresenter extends IPostPresenter{
    void loadFragment(String tag, Bundle bundle);
}
