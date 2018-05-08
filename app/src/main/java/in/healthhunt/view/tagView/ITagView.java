package in.healthhunt.view.tagView;

/**
 * Created by abhishekkumar on 5/6/18.
 */

public interface ITagView {
    void onShowProgress();
    void onHideProgress();
    void updateAdapter();
    void showAlert(String msg);

}
