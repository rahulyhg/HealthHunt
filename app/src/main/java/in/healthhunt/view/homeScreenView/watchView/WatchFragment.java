package in.healthhunt.view.homeScreenView.watchView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.beans.SpaceDecoration;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.watchPresenter.IWatchPresenter;
import in.healthhunt.presenter.homeScreenPresenter.watchPresenter.WatchPresenterImp;
import in.healthhunt.view.fullView.FullVideoActivity;
import in.healthhunt.view.homeScreenView.IHomeView;

/**
 * Created by abhishekkumar on 5/19/18.
 */

public class WatchFragment extends Fragment implements IWatchView, WatchAdapter.ClickListener{

    private IWatchPresenter IWatchPresenter;
    private Unbinder mUnbinder;

    @BindView(R.id.watch_recycler_list)
    RecyclerView mWatchViewer;

    private IHomeView IHomeView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IWatchPresenter = new WatchPresenterImp(getContext(), this);
        IHomeView = (IHomeView) getActivity();
        IWatchPresenter.fetchVideoArticles();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watch, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setAdapter();
        return view;
    }

    @Override
    public int getCount() {
        return IWatchPresenter.getCount();
    }
    
    @Override
    public void showProgress() {
        IHomeView.showProgress();
    }

    @Override
    public void hideProgress() {
        IHomeView.hideProgress();
    }

    @Override
    public void updateBottomNavigation() {
        IHomeView.hideBottomNavigationSelection();
    }

    @Override
    public WatchViewHolder createViewHolder(View view) {
        return new WatchViewHolder(view, IWatchPresenter, this);
    }

    @Override
    public void updateAdapter() {
        mWatchViewer.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showAlert(String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    private void setAdapter() {
        WatchAdapter watchAdapter = new WatchAdapter(getContext(), IWatchPresenter);
        watchAdapter.setClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mWatchViewer.setLayoutManager(layoutManager);
        mWatchViewer.addItemDecoration(new SpaceDecoration(HealthHuntUtility.dpToPx(8, getContext()), SpaceDecoration.VERTICAL));
        mWatchViewer.setAdapter(watchAdapter);
    }



    @Override
    public void ItemClicked(View v, int position) {
        ArticlePostItem postsItem = IWatchPresenter.getArticle(position);
        if(postsItem != null) {
            Intent intent = new Intent(getContext(), FullVideoActivity.class);
            intent.putExtra(ArticleParams.ID, String.valueOf(postsItem.getId()));
            intent.putExtra(ArticleParams.POST_TYPE, ArticleParams.VIDEO);
            getContext().startActivity(intent);
        }
    }
}
