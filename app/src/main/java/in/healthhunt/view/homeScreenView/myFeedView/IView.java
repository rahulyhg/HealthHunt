package in.healthhunt.view.homeScreenView.myFeedView;

import in.healthhunt.model.articles.bookmarkResponse.BookMarkResponse;

/**
 * Created by abhishekkumar on 5/19/18.
 */

public interface IView {
    int getCount();
    void updateBookMark(BookMarkResponse markResponse);
    void showProgress();
    void hideProgress();
    void updateBottomNavigation();

}
