package in.healthhunt.view.homeScreenView.article;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by abhishekkumar on 4/24/18.
 */

public interface IArticleView {

    Fragment getArticleItem(int position);
    int getArticleCount();
    void showFragment(String tag, Bundle bundle);;
}
