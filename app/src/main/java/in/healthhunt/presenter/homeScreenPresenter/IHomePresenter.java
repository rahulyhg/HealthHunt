package in.healthhunt.presenter.homeScreenPresenter;

import android.support.v4.app.FragmentManager;
import android.view.View;

import java.util.List;

import in.healthhunt.model.beans.Tag;
import in.healthhunt.view.homeScreenView.ArticleViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IHomePresenter {
    int getCount();
    ArticleViewHolder createArticleHolder(View view, FragmentManager fragmentManager);
}
