package in.healthhunt.view.homeScreenView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.presenter.homeScreenPresenter.HomePresenterImp;
import in.healthhunt.presenter.homeScreenPresenter.IHomePresenter;
import in.healthhunt.view.BaseActivity;
import in.healthhunt.view.homeScreenView.drawerView.DrawerFragment;
import in.healthhunt.view.homeScreenView.filterView.FilterFragment;
import in.healthhunt.view.homeScreenView.myFeedView.MyFeedFragment;
import in.healthhunt.view.homeScreenView.myHuntsView.MyHuntFragment;
import in.healthhunt.view.homeScreenView.shopView.IShopView;
import in.healthhunt.view.homeScreenView.shopView.ShopFragment;
import in.healthhunt.view.homeScreenView.watchView.WatchFragment;
import in.healthhunt.view.viewAll.ViewAllFragment;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class HomeActivity extends BaseActivity implements IHomeView{


    private IHomePresenter IHomePresenter;
    private Map<String,Fragment> fragmentMap = new HashMap<String, Fragment>();

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView mNavigation;

    //@BindView(R.id.my_feed_frame)
    //FrameLayout mFeedLayout;

    //@BindView(R.id.shop_frame)
    //FrameLayout mShopLayout;


    private FragmentManager mFragmentManager;
    private Toolbar mToolbar;

    private ActionBarDrawerToggle mDrawerToggle;
    private Dialog mAlertDialog;
    private DrawerFragment mDrawerFragment;

    private Fragment[] mFragment = new Fragment[4];

    private int mCurrentItem;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        ButterKnife.bind(this);

        mAlertDialog = new Dialog(this);
        mAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mAlertDialog.setContentView(R.layout.alertdialog_view);

        mFragmentManager = getSupportFragmentManager();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        addDrawer();
        removeShiftMode(mNavigation);


        IHomePresenter = new HomePresenterImp(getApplicationContext(), this);
        IHomePresenter.loadFooterFragment(MyFeedFragment.class.getSimpleName(), null);
        updateTitle(getString(R.string.my_feed));

        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setCheckable(true);
                popTopBackStack();

                if(item.getItemId() == R.id.navigation_my_feed) {
                    mCurrentItem = 0;
                    //updateTitle(getString(R.string.my_feed));
                    Fragment fragment = mFragment[mCurrentItem];
                    if(fragment == null){
                        IHomePresenter.loadFooterFragment(MyFeedFragment.class.getSimpleName(), null);
                    }
                }
                else if(item.getItemId() == R.id.navigation_my_hunts) {
                    mCurrentItem = 1;
                    //updateTitle(getString(R.string.my_hunts));
                    Fragment fragment = mFragment[mCurrentItem];
                    if(fragment == null){
                        IHomePresenter.loadFooterFragment(MyHuntFragment.class.getSimpleName(), null);
                    }
                }
                else if(item.getItemId() == R.id.navigation_watch) {
                    mCurrentItem = 2;
                    //updateTitle(getString(R.string.watch));
                    Fragment fragment = mFragment[mCurrentItem];
                    if(fragment == null){
                        IHomePresenter.loadFooterFragment(WatchFragment.class.getSimpleName(), null);
                    }
                }
                else if(item.getItemId() == R.id.navigation_shop) {
                    mCurrentItem = 3;
                    //updateTitle(getString(R.string.shop));
                    Fragment fragment = mFragment[mCurrentItem];
                    if(fragment == null){
                        IHomePresenter.loadFooterFragment(ShopFragment.class.getSimpleName(), null);
                    }
                }
                updateTitleWithFooter(mCurrentItem);
                updateFragVisible(mCurrentItem);
                return true;
            }
        });
    }

    private void addDrawer() {
        mDrawerToggle = new android.support.v7.app.
                ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mDrawerFragment = new DrawerFragment();
        fragmentTransaction.replace(R.id.drawer_frame, mDrawerFragment);
        fragmentTransaction.commit();

    }

    private void updateFragVisible(int pos){
        for(int i=0; i<mFragment.length; i++){
            Fragment fragment = mFragment[i];
            if(fragment != null){
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                if(i == pos){
                    fragmentTransaction.show(fragment);
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                }
                else {
                    fragmentTransaction.hide(fragment);
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                }

                fragmentTransaction.commit();
            }
        }
    }

    private void hideAllFooterFragment(){
        for(int i=0; i<mFragment.length; i++){
            Fragment fragment = mFragment[i];
            if(fragment != null){
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.hide(fragment);
                fragmentTransaction.commit();
            }
        }
    }

    @Override
    public void loadNonFooterFragment(String tag, Bundle bundle) {
        if(tag != null) {
            int layout = R.id.main_frame;

            Fragment fragment = null;
            if (tag.equals(ViewAllFragment.class.getSimpleName())) {
                fragment = new ViewAllFragment();
            } else if (tag.equals(FilterFragment.class.getSimpleName())) {
                fragment = new FilterFragment();
            }

            if (bundle != null && fragment != null) {
                fragment.setArguments(bundle);
            }

            if(fragment != null) {
                showNonFooterFragment(fragment, tag, layout);
            }

            hideAllFooterFragment(); // to hide all the previous fragments
        }
    }

    @Override
    public void popTopBackStack() {
        mFragmentManager.popBackStack();
    }

    @Override
    public void sendFilterData(Map<Integer, List<String>> map) {
        updateFragVisible(mCurrentItem);
        setBottomNavigation();
        Fragment fragment = mFragment[mCurrentItem];
        if(mFragment != null && fragment instanceof ShopFragment){
            ((IShopView)fragment).handleFilterData(map);
        }
    }

    @Override
    public Map<Integer, List<String>> getFilterData() {
        Fragment fragment = mFragment[mCurrentItem];
        if(mFragment != null && fragment instanceof ShopFragment){
            return ((IShopView)fragment).getFilterData();
        }
        return null;
    }

    @Override
    public void closeDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void updateMyFeedArticles() {
        Fragment fragment = mFragment[mCurrentItem];
        if(fragment != null && fragment instanceof MyFeedFragment){
            ((MyFeedFragment)fragment).updateArticlesList();
        }
    }

    @Override
    public void updateDrawerFragment() {
        if(mDrawerFragment != null){
            mDrawerFragment.updateAdapter();
        }
    }

    private void showNonFooterFragment(Fragment fragment, String tag, int layout) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(layout, fragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
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

    @Override
    public IHomePresenter getHomePresenter() {
        return IHomePresenter;
    }

    @Override
    public void hideBottomFooter() {
        mNavigation.setVisibility(View.GONE);
    }

    @Override
    public void showBottomFooter() {
        mNavigation.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadFooterFragment(String tag, Bundle bundle) {
        int layout = R.id.my_feed_frame;
        Fragment fragment = null;
        if (tag.equals(MyFeedFragment.class.getSimpleName())) {
            layout = R.id.my_feed_frame;
            if (fragment == null) {
                fragment = new MyFeedFragment();
            }
        }  else if (tag.equals(MyHuntFragment.class.getSimpleName())) {
            layout = R.id.my_hunt_frame;
            if (fragment == null) {
                fragment = new MyHuntFragment();
            }
        }
        else if (tag.equals(WatchFragment.class.getSimpleName())) {
            layout = R.id.watch_frame;
            if (fragment == null) {
                fragment = new WatchFragment();
            }
        }
        else if (tag.equals(ShopFragment.class.getSimpleName())) {
            layout = R.id.shop_frame;
            if (fragment == null) {
                fragment = new ShopFragment();
            }
        }

        mFragment[mCurrentItem] = fragment;
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        showFooterFragment(fragment, layout);
    }

    private void showFooterFragment(Fragment fragment, int layout) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(layout, fragment);
        fragmentTransaction.commit();
    }




    /*private void setAdapter() {
        HomeScreenAdapter homeScreenAdapter = new HomeScreenAdapter(mHomePresenterImp, getSupportFragmentManager());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mHomeScreenViewer.setLayoutManager(layoutManager);
        mHomeScreenViewer.setAdapter(homeScreenAdapter);
    }*/

    /*@Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }*/

    @Override
    public void showHomeAlert(String msg) {

        if (!((Activity) this).isFinishing()) {

            TextView message = mAlertDialog.findViewById(R.id.alert_message);
            message.setText(msg);

            TextView title = mAlertDialog.findViewById(R.id.alert_title);
            title.setVisibility(View.GONE);


            Button okButton = mAlertDialog.findViewById(R.id.action_button);
            okButton.setText(android.R.string.ok);
            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mAlertDialog.dismiss();
                    finishActivity();
                }
            });
            mAlertDialog.show();
        }
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            int count = getSupportFragmentManager().getBackStackEntryCount();
            if(count > 0){
                updateFragVisible(mCurrentItem);
                setBottomNavigation();
                updateTitleWithFooter(mCurrentItem);
            }
            super.onBackPressed();
        }
    }

    @Override
    public void hideBottomNavigationSelection() {
        int count = mNavigation.getMenu().size();
        Log.i("TAGCOUNT ", "count" + count);
        for(int i=0; i<count; i++) {
            mNavigation.getMenu().getItem(i).setCheckable(false);
        }
    }

    @Override
    public void setBottomNavigation() {
        mNavigation.getMenu().getItem(mCurrentItem).setCheckable(true);
    }

    @Override
    public void updateTitle(String msg) {
        //SpannableString s = new SpannableString(msg);
        //.setSpan(new ForegroundColorSpan(Color.WHITE), 0, msg.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mToolbar.setTitle(msg);
        //mToolbar.setT
    }

    @Override
    public void showProgress(){
        mProgress.show();;
    }

    @Override
    public void hideProgress(){
        mProgress.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProgress.dismiss();
        mAlertDialog.dismiss();
    }

    private void updateTitleWithFooter(int pos){
        String str = "";
       switch (pos){
           case 0:
            str = getString(R.string.my_feed);
            break;

           case 1:
               str = getString(R.string.my_hunts);
               break;

           case 2:
               str = getString(R.string.watch);
               break;

           case 3:
               str = getString(R.string.shop);
               break;
        }
        updateTitle(str);
    }
}
