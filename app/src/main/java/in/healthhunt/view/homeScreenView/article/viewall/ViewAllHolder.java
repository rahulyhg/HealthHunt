package in.healthhunt.view.homeScreenView.article.viewall;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.model.beans.VerticalSpaceDecoration;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.TrendingSponsoredPresenterImp;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter.IViewAllPresenter;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter.ViewAllPresenterImp;
import in.healthhunt.view.homeScreenView.article.myfeed.ITrendingSponsoredView;
import in.healthhunt.view.homeScreenView.article.myfeed.TrendingAdapter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ViewAllHolder extends RecyclerView.ViewHolder {

    private Context mContext;

    public ViewAllHolder(View articleView) {
        super(articleView);
        mContext = articleView.getContext();
    }
}