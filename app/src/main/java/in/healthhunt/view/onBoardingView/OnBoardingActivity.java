package in.healthhunt.view.onBoardingView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.crashlytics.android.Crashlytics;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.presenter.onBoardingPresenter.OnBoardingPresenter;
import in.healthhunt.view.BaseActivity;
import io.fabric.sdk.android.Fabric;

/**
 * Created by abhishekkumar on 4/24/18.
 */

public class OnBoardingActivity extends BaseActivity implements IOnBoardingView {

    @BindView(R.id.on_boarding_pager)
    public ViewPager mViewPager;

    @BindView(R.id.dots)
    public TabLayout mDotIndicator;

    private OnBoardingPresenter mOnBoardingPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        Fabric.with(this, new Crashlytics());
        ButterKnife.bind(this);

        mOnBoardingPresenter = new OnBoardingPresenter(getApplicationContext(), this);
        setAdapter();

        //addScrollIndicator();
    }

//    private void addScrollIndicator() {
//        int height = getResources().getDimensionPixelOffset(R.dimen.indicator_height);
//        int width = getResources().getDimensionPixelOffset(R.dimen.indicator_width);
//        int marginStart =  getResources().getDimensionPixelOffset(R.dimen.indicator_margin_start);
//        int marginEnd =  getResources().getDimensionPixelOffset(R.dimen.indicator_margin_end);
//
//        for(int i=0; i<Constants.ON_BOARDING_SCREEN_COUNT; i++) {
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
//            layoutParams.setMargins(marginStart, 0, marginEnd, 0);
//            ImageView indicator = new ImageView(getApplicationContext());
//            indicator.setLayoutParams(layoutParams);
//            indicator.setBackgroundResource(R.mipmap.ic_oval);
//            mScrollIndicator.addView(indicator, i);
//        }
//        updateIndicator();
//    }

    private void setAdapter() {
        OnBoardingAdapter adapter = new OnBoardingAdapter(getSupportFragmentManager(), mOnBoardingPresenter);
        mViewPager.setAdapter(adapter);

        mDotIndicator = findViewById(R.id.dots);
        mDotIndicator.setupWithViewPager(mViewPager, true);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public Fragment getItem(int position) {
        return new OnBoardingFragment();
    }

    @Override
    public int getCount() {
        return Constants.ON_BOARDING_SCREEN_COUNT;
    }

//    private void updateIndicator() {
//        int pos = mViewPager.getCurrentItem();
//        Log.i("TAG", "Count = " + mScrollIndicator.getChildCount() + " Pos " + pos);
//        for(int i=0; i<mScrollIndicator.getChildCount(); i++) {
//            ImageView indicatorView = (ImageView) mScrollIndicator.getChildAt(pos);
//            if(i == pos) {
//                indicatorView.setBackgroundResource(R.mipmap.ic_oval_dark);
//            }
//            else {
//                indicatorView.setBackgroundResource(R.mipmap.ic_oval);
//            }
//        }
//    }
}
