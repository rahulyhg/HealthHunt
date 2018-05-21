package in.healthhunt.view.homeScreenView.myFeedView;

import in.healthhunt.model.articles.bookmarkResponse.BookMarkData;

/**
 * Created by abhishekkumar on 5/19/18.
 */

public interface IView {
    int getCount();
    void updateBookMark(BookMarkData markResponse);
    void showProgress();
    void hideProgress();
    void updateBottomNavigation();

}
