package in.healthhunt.presenter.homeScreenPresenter;

import android.os.Bundle;

import java.util.List;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IHomePresenter{
    void loadFooterFragment(String tag, Bundle bundle);
    void loadNonFooterFragment(String tag, Bundle bundle);
    void addCategory(String str);
    void removeCategory(String str);
    boolean isCategoryContain(String str);
    List<String> getCategoryList();
}
