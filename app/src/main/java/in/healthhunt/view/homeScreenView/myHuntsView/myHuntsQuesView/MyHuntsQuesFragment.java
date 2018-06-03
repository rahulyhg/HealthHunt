package in.healthhunt.view.homeScreenView.myHuntsView.myHuntsQuesView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.beans.SpaceDecoration;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.myHuntPresenter.myHuntArticlesPresenter.IMyHuntsArticlesPresenter;
import in.healthhunt.presenter.homeScreenPresenter.myHuntPresenter.myHuntArticlesPresenter.MyHuntsArticlesPresenterImp;
import in.healthhunt.view.homeScreenView.myHuntsView.IMyHuntsView;
import in.healthhunt.view.homeScreenView.myHuntsView.myHuntsArticleView.MyHuntsArticleAdapter;
import in.healthhunt.view.homeScreenView.myHuntsView.myHuntsArticleView.MyHuntsArticleHolder;

public class MyHuntsQuesFragment extends Fragment implements IMyHuntsView, MyHuntsArticleAdapter.ClickListener{


    private IMyHuntsArticlesPresenter IMyHuntsArticlePresenter;
    private Unbinder mUnbinder;

    @BindView(R.id.recycler_view)
    RecyclerView mArticleViewer;

    @BindView(R.id.top_navigation)
    BottomNavigationView mNavigation;

    private int mNavigationType;



    public MyHuntsQuesFragment() {
        IMyHuntsArticlePresenter = new MyHuntsArticlesPresenterImp(getContext(), this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_hunts_article_view, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        removeShiftMode(mNavigation);
        mNavigationType = ArticleParams.SAVED;

        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                switch (id){
                    case R.id.navigation_saved:
                        mNavigationType = ArticleParams.SAVED;
                        break;

                    case R.id.navigation_approved:
                        mNavigationType = ArticleParams.APPROVED;
                        break;

                    case R.id.navigation_received:
                        mNavigationType = ArticleParams.RECEIVED;
                        break;

                    case R.id.navigation_downloaded:
                        mNavigationType = ArticleParams.DOWNLOADED;
                        break;
                }

                updateAdapter();
                return true;
            }
        });

        setAdapter();
        return view;
    }

    @Override
    public MyHuntsArticleHolder createViewHolder(View view) {
        return new MyHuntsArticleHolder(view, IMyHuntsArticlePresenter, this);
    }

    @Override
    public void updateAdapter() {
        mArticleViewer.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showAlert(String msg) {

    }

    @Override
    public int getType() {
        return mNavigationType;
    }

    @Override
    public void loadFragment(String fragmentName, Bundle bundle) {

    }

    @Override
    public int getCount() {
        int count = 8;
        switch (mNavigationType){
            case ArticleParams.SAVED:
                count = 1;
                break;

            case ArticleParams.APPROVED:
                count = 1;
                break;

            case ArticleParams.RECEIVED:
                count = 2;
                break;

            case ArticleParams.DOWNLOADED:
                count = 3;
                break;
        }
        return count;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void updateBottomNavigation() {

    }

    @Override
    public void ItemClicked(View v, int position) {
// ArticlePostItem postsItem = IShopPresenter.getVideo(position);
        //if(postsItem != null) {
        /*Intent intent = new Intent(getContext(), FullViewActivity.class);
        intent.putExtra(ArticleParams.ID, "");
        intent.putExtra(ArticleParams.POST_TYPE, ArticleParams.ARTICLE);
        getContext().startActivity(intent);*/
        // }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    private void setAdapter() {
        MyHuntsArticleAdapter adapter = new MyHuntsArticleAdapter(getContext(), IMyHuntsArticlePresenter);
        adapter.setClickListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mArticleViewer.setLayoutManager(layoutManager);
        mArticleViewer.addItemDecoration(new SpaceDecoration(HealthHuntUtility.dpToPx(8, getContext()), SpaceDecoration.GRID));
        mArticleViewer.setAdapter(adapter);
    }

    @SuppressLint("RestrictedApi")
    public void removeShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BottomNav", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BottomNav", "Unable to change value of shift mode", e);
        }
    }
}