package in.healthhunt.view.homeScreenView.shopView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.beans.SpaceDecoration;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.shopPresenter.IShopPresenter;
import in.healthhunt.presenter.homeScreenPresenter.shopPresenter.ShopPresenterImp;
import in.healthhunt.view.fullView.FullViewActivity;
import in.healthhunt.view.homeScreenView.IHomeView;

/**
 * Created by abhishekkumar on 5/19/18.
 */

public class ShopFragment extends Fragment implements IShopView, ShopAdapter.ClickListener{

    private IShopPresenter IShopPresenter;
    private Unbinder mUnbinder;

    @BindView(R.id.recycler_view)
    RecyclerView mShopViewer;

    @BindView(R.id.suggestion_header)
    TextView mSuggestionHeader;

    @BindView(R.id.suggestion_cross)
    ImageView mSuggestionCross;

    @BindView(R.id.suggestion_content)
    TextView mSuggestionContent;

    @BindView(R.id.filter_button)
    Button mFilterButton;

    @BindView(R.id.filter_view)
    LinearLayout mFilterView;

    @BindView(R.id.suggestion_view)
    LinearLayout mSuggestionView;

    private IHomeView IHomeView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IShopPresenter = new ShopPresenterImp(getContext(), this);
        IHomeView = (IHomeView) getActivity();
        IShopPresenter.fetchMarkets();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mSuggestionContent.setText(R.string.healthhunt_shop_content);
        Log.i("TAGSHOWPR", "IHomeView ONCREATE " + IHomeView);
        setAdapter();
        return view;
    }

    @Override
    public int getCount() {
        return IShopPresenter.getCount();
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
        IHomeView.updateBottomNavigation();
    }

    @Override
    public ShopViewHolder createViewHolder(View view) {
        return new ShopViewHolder(view, IShopPresenter, this);
    }

    @Override
    public void updateAdapter() {
        mShopViewer.getAdapter().notifyDataSetChanged();
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

    @OnClick(R.id.suggestion_cross)
    void onCrossClick(){
        mSuggestionView.setVisibility(View.GONE);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mFilterButton.getLayoutParams();
        int margin = HealthHuntUtility.dpToPx(4,getContext());
        params.setMargins(params.getMarginStart(),margin, params.getMarginEnd(), params.bottomMargin - margin);
        mFilterButton.setLayoutParams(params);
        mFilterView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.hh_green_light2));
    }

    @OnClick(R.id.filter_view)
    void onFilterClick(){

        Map<String,String> map = new HashMap<String, String>();
        List<String> products = new ArrayList<String>();
        List<String> brands = new ArrayList<String>();
        List<String> city = new ArrayList<String>();

        List<ArticlePostItem> itemList = IShopPresenter.getAllArticles();
        if(itemList != null) {
            for(ArticlePostItem item : itemList){
                String type = item.getType();
            }
        }
    }

    private void setAdapter() {
        ShopAdapter shopAdapter = new ShopAdapter(getContext(), IShopPresenter);
        shopAdapter.setClickListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mShopViewer.setLayoutManager(layoutManager);
        mShopViewer.addItemDecoration(new SpaceDecoration(HealthHuntUtility.dpToPx(8, getContext()), SpaceDecoration.GRID));
        mShopViewer.setAdapter(shopAdapter);
    }

    @Override
    public void ItemClicked(View v, int position) {
        ArticlePostItem postsItem = IShopPresenter.getArticle(position);
        if(postsItem != null) {
            Intent intent = new Intent(getContext(), FullViewActivity.class);
            intent.putExtra(ArticleParams.ID, String.valueOf(postsItem.getId()));
            intent.putExtra(ArticleParams.POST_TYPE, ArticleParams.ARTICLE);
            getContext().startActivity(intent);
        }
    }
}
