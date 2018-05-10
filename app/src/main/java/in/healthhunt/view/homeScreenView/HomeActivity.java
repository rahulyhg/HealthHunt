package in.healthhunt.view.homeScreenView;

import android.annotation.SuppressLint;
import android.graphics.Color;
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.presenter.homeScreenPresenter.HomePresenterImp;
import in.healthhunt.presenter.homeScreenPresenter.IHomePresenter;
import in.healthhunt.presenter.preference.HealthHuntPreference;
import in.healthhunt.view.homeScreenView.article.DrawerFragment;
import in.healthhunt.view.homeScreenView.article.myfeed.MyFeedFragment;
import in.healthhunt.view.homeScreenView.article.viewall.ViewAllFragment;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class HomeActivity extends AppCompatActivity implements IHomeView{


    private IHomePresenter IHomePresenter;
    private Map<String,Fragment> fragmentMap = new HashMap<String, Fragment>();


    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView mNavigation;

    private FragmentManager mFragmentManager;
    private Toolbar mToolbar;

    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        ButterKnife.bind(this);

        mFragmentManager = getSupportFragmentManager();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        addDrawer();
        removeShiftMode(mNavigation);

        String tile = "My feed";
        SpannableString s = new SpannableString(tile);
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, tile.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mToolbar.setTitle(s);

        Set<String> set = HealthHuntPreference.getSet(this, Constants.SELECTED_TAGS_KEY);
        for (String str: set){
            Log.i("TAGID" , "ID = " + str);
        }

        IHomePresenter = new HomePresenterImp(getApplicationContext(), this);
        IHomePresenter.loadFragment(MyFeedFragment.class.getSimpleName(), null);

        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //item.setChecked(true);
                mFragmentManager.getPrimaryNavigationFragment();
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

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
