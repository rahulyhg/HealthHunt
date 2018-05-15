package in.healthhunt.view.homeScreenView.article.myfeed;

import android.support.v4.app.Fragment;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public interface ILatestProductView extends IProduct{
    Fragment getFragmentArticleItem(int position);
}
