package in.healthhunt.view.homeScreenView;

import android.annotation.SuppressLint;
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
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.presenter.homeScreenPresenter.HomePresenterImp;
import in.healthhunt.presenter.homeScreenPresenter.IHomePresenter;
import in.healthhunt.view.BaseActivity;
import in.healthhunt.view.homeScreenView.article.DrawerFragment;
import in.healthhunt.view.homeScreenView.article.myfeed.MyFeedFragment;
import in.healthhunt.view.homeScreenView.article.viewall.ViewAllFragment;

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

    private FragmentManager mFragmentManager;
    private Toolbar mToolbar;

    private ActionBarDrawerToggle mDrawerToggle;
    private Dialog mAlertDialog;

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
        IHomePresenter.loadFragment(MyFeedFragment.class.getSimpleName(), null);
        updateTitle(getString(R.string.my_feed));

        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setCheckable(true);
                mFragmentManager.popBackStack();

                if(item.getItemId() == R.id.navigation_my_feed) {
                    mCurrentItem = 0;
                    updateTitle(getString(R.string.my_feed));
                    IHomePresenter.loadFragment(MyFeedFragment.class.getSimpleName(), null);
                }
                else if(item.getItemId() == R.id.navigation_my_hunts) {
                    mCurrentItem = 1;
                    updateTitle(getString(R.string.my_hunts));
                    //IHomePresenter.loadFragment(MyFeedFragment.class.getSimpleName(), null);
                }
                else if(item.getItemId() == R.id.navigation_watch) {
                    mCurrentItem = 2;
                    updateTitle(getString(R.string.watch));
                    //IHomePresenter.loadFragment(MyFeedFragment.class.getSimpleName(), null);
                }
                else if(item.getItemId() == R.id.navigation_shop) {
                    mCurrentItem = 3;
                    updateTitle(getString(R.string.shop));
                    //IHomePresenter.loadFragment(MyFeedFragment.class.getSimpleName(), null);
                }

                return true;
            }
        });

        mProgress.show();
    }

    private void addDrawer() {
        mDrawerToggle = new android.support.v7.app.
                ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        DrawerFragment drawerFragment = new DrawerFragment();
        fragmentTransaction.replace(R.id.drawer_frame, drawerFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void showFragment(String tag, Bundle bundle) {
        Fragment fragment = fragmentMap.get(tag);

        if(fragment == null) {
            if (tag != null && tag.equals(MyFeedFragment.class.getSimpleName())) {
                fragment = new MyFeedFragment();
            }
            else if (tag != null && tag.equals(ViewAllFragment.class.getSimpleName())) {
                fragment = new ViewAllFragment();
            }
            fragmentMap.put(tag, fragment);
        }
        fragment.setArguments(bundle);
        loadFragment(fragment, tag);
    }

    private void loadFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        if(tag.equals(ViewAllFragment.class.getSimpleName())) {
            fragmentTransaction.addToBackStack(tag);
        }
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

    public IHomePresenter getHomePresenter() {
        return IHomePresenter;
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

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void updateNavigation() {
        int count = mNavigation.getChildCount();
        for(int i=0; i<count; i++) {
            mNavigation.getMenu().getItem(i).setCheckable(false);
        }
    }

    public void setNavigation() {
        mNavigation.getMenu().getItem(mCurrentItem).setCheckable(true);
    }

    @Override
    public void updateTitle(String msg) {
        //SpannableString s = new SpannableString(msg);
        //.setSpan(new ForegroundColorSpan(Color.WHITE), 0, msg.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mToolbar.setTitle(msg);
        //mToolbar.setT
    }

    public void onComplete() {
        mProgress.dismiss();
    }
}
