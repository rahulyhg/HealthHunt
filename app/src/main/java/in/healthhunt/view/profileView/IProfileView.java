package in.healthhunt.view.profileView;

import android.os.Bundle;
import android.view.View;

import in.healthhunt.view.tagView.TagViewHolder;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public interface IProfileView {
    void showProgress();
    void hideProgress();
    void updateAdapter();
    void showAlert(String msg);
    TagViewHolder createTagViewHolder(View view);
    void loadFragment(String fragmentName, Bundle bundle);
}
