package in.healthhunt.view.onBoardingView;

import android.support.v4.app.Fragment;

/**
 * Created by abhishekkumar on 4/24/18.
 */

public interface IOnBoardingView {

    Fragment getItem(int position);
    int getCount();
}
