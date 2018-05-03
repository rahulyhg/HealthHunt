package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by abhishekkumar on 4/24/18.
 */

public interface IContinueArticlePresenter {
    void loadFragment(String tag, Bundle bundle);
    int getCount();
    Fragment getItem(int position);
}
