package in.healthhunt.view.homeScreenView.myHuntsView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.view.homeScreenView.myHuntsView.myHuntsArticleView.MyHuntsArticleFragment;
import in.healthhunt.view.homeScreenView.myHuntsView.myHuntsQuesView.MyHuntsQuesFragment;
import in.healthhunt.view.homeScreenView.myHuntsView.myHuntsShopView.MyHuntsShopFragment;
import in.healthhunt.view.homeScreenView.myHuntsView.myHuntsVideoView.MyHuntsVideoFragment;

/**
 * Created by abhishekkumar on 5/21/18.
 */

public class MyHuntFragment extends Fragment {


    @BindView(R.id.my_hunts_tabs)
    TabLayout mTabLayout;

    @BindView(R.id.my_hunts_viewpager)
    ViewPager mHuntPager;

    @BindView(R.id.suggestion_view)
    LinearLayout mSuggestionView;

    @BindView(R.id.suggestion_header)
    TextView mSuggestionHeader;

    @BindView(R.id.suggestion_cross)
    ImageView mSuggestionCross;

    @BindView(R.id.suggestion_content)
    TextView mSuggestionContent;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_hunts,  container, false);
        ButterKnife.bind(this, view);
        setupViewPager();
        mTabLayout.setupWithViewPager(mHuntPager);
        return view;
    }

    @OnClick(R.id.suggestion_cross)
    void onCrossClick(){
        mSuggestionView.setVisibility(View.GONE);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTabLayout.getLayoutParams();
        params.setMargins(params.getMarginStart(),0, params.getMarginEnd(), 0);
        mTabLayout.setLayoutParams(params);
        mTabLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.hh_green_light2));
        mTabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        mTabLayout.setTabTextColors(ContextCompat.getColor(getContext(), R.color.hh_text_color1), Color.WHITE);
        //mShopViewer.setPadding(mShopViewer.getPaddingLeft(), HealthHuntUtility.dpToPx(16, getContext()), mShopViewer.getPaddingRight(), mShopViewer.getPaddingBottom());
    }

    private void setupViewPager() {
        MyHuntsPagerAdapter adapter = new MyHuntsPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new MyHuntsArticleFragment(), getString(R.string.articles));
        adapter.addFragment(new MyHuntsVideoFragment(), getString(R.string.videos));
        adapter.addFragment(new MyHuntsShopFragment(), getString(R.string.shop));
        adapter.addFragment(new MyHuntsQuesFragment(), getString(R.string.questions));
        mHuntPager.setAdapter(adapter);
    }
}
