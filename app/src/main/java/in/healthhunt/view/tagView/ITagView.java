package in.healthhunt.view.tagView;

import android.os.Bundle;
import android.view.View;

import in.healthhunt.presenter.tagPresenter.ITagPresenter;

/**
 * Created by abhishekkumar on 5/6/18.
 */

public interface ITagView {
    void onShowProgress();
    void onHideProgress();
    void updateAdapter();
    void showAlert(String msg);
    TagViewHolder createTagViewHolder(View view);
    void loadFragment(String fragmentName, Bundle bundle);
    ITagPresenter getPresenter();
    void updateSearchAdapter();
}
