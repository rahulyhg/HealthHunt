package in.healthhunt.view.viewAll;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.beans.SpaceDecoration;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.viewAllPresenter.IViewAllPresenter;
import in.healthhunt.presenter.viewAllPresenter.ViewAllPresenterImp;
import in.healthhunt.view.fullView.fullViewFragments.FullArticleFragment;
import in.healthhunt.view.fullView.fullViewFragments.FullProductFragment;
import in.healthhunt.view.homeScreenView.HomeActivity;
import in.healthhunt.view.homeScreenView.IHomeView;

/**
 * Created by abhishekkumar on 5/3/18.
 */

public class ViewAllFragment extends Fragment implements IViewAll, ViewAllAdapter.ClickListener{


    @BindView(R.id.view_all_recycler_list)
    RecyclerView mViewAllViewer;

    private IViewAllPresenter IViewAllPresenter;
    private ProgressDialog mProgress;
    private int mType;
    private IHomeView IHomeView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgress = new ProgressDialog(getContext());
        mProgress.setIndeterminate(true);
        mProgress.setCancelable(false);
        mProgress.setMessage(getResources().getString(R.string.please_wait));
        IViewAllPresenter = new ViewAllPresenterImp(getContext(), this);
        IHomeView  =(HomeActivity) getActivity();

        Bundle bundle = getArguments();
        String id = null;
        if(bundle != null) {
            mType = bundle.getInt(ArticleParams.ARTICLE_TYPE);
            id = bundle.getString(ArticleParams.ID);
        }

        Log.i("TAGTYPE " , "TYPE " + mType);
        IViewAllPresenter.fetchAll(mType, id);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_all, container, false);
        ButterKnife.bind(this, view);
        IHomeView.updateTitle(getArticleName());
        IHomeView.setStatusBarTranslucent(false);
        IHomeView.hideBottomNavigationSelection();
        IHomeView.showBottomFooter();
        IHomeView.showActionBar();
        setAdapter();
        return view;
    }

    private void setAdapter() {
        ViewAllAdapter viewAllAdapter = new ViewAllAdapter(getContext(), IViewAllPresenter, mType);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mViewAllViewer.setLayoutManager(layoutManager);
        mViewAllViewer.addItemDecoration(new SpaceDecoration(HealthHuntUtility.dpToPx(8, getContext()), SpaceDecoration.VERTICAL));
        mViewAllViewer.setAdapter(viewAllAdapter);
    }

    public int getCount() {
        return IViewAllPresenter.getCount(mType);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(View view) {

        RecyclerView.ViewHolder  viewHolder = null;
        switch (mType){
            case ArticleParams.BASED_ON_TAGS:
            case ArticleParams.LATEST_ARTICLES:
            case ArticleParams.RELATED_ARTICLES:
                viewHolder = new ViewAllArticleHolder(view, this, IViewAllPresenter);
                break;
            case ArticleParams.LATEST_PRODUCTS:
            case ArticleParams.RELATED_PRODUCTS:
                viewHolder = new ViewAllProductHolder(view, this, IViewAllPresenter);
                break;
        }
        return viewHolder;
    }

    @Override
    public void showProgress() {
        mProgress.show();;
    }

    @Override
    public void hideProgress() {
        mProgress.dismiss();
    }

    @Override
    public void updateAdapter() {
        mViewAllViewer.getAdapter().notifyDataSetChanged();;
    }

    @Override
    public int getViewLayout() {
        int layout = 0;
        switch (mType) {
            case ArticleParams.BASED_ON_TAGS:
            case ArticleParams.LATEST_ARTICLES:
            case ArticleParams.RELATED_ARTICLES:
                layout = R.layout.view_all_article_item_view;
                break;


            case ArticleParams.LATEST_PRODUCTS:
            case ArticleParams.RELATED_PRODUCTS:
                layout = R.layout.view_all_product_item_view;
                break;
        }

        return layout;
    }

    @Override
    public int getType() {
        return mType;
    }

    @Override
    public void loadFragment(String fragmentName, Bundle bundle) {
        IHomeView.loadNonFooterFragment(fragmentName, bundle);
    }


    private String getArticleName() {
        String name = "";
        switch (mType) {
            case ArticleParams.BASED_ON_TAGS:
                name = ArticleParams.TEXT_BASED_ON_TAGS;
                break;
            case ArticleParams.LATEST_ARTICLES:
                name = ArticleParams.TEXT_LATEST_ARTICLES;
                break;
            case ArticleParams.LATEST_PRODUCTS:
                name = ArticleParams.TEXT_LATEST_PRODUCTS_ARTICLES;
                break;
            case ArticleParams.RELATED_ARTICLES:
                name = ArticleParams.TEXT_RELATED_ARTICLES;
                break;
            case ArticleParams.RELATED_PRODUCTS:
                name = ArticleParams.TEXT_RELATED_PRODUCTS;
                break;

        }

        return name;
    }

    @Override
    public void ItemClicked(View v, int position) {
        String id = null;
        String fragmentTag = FullArticleFragment.class.getSimpleName();
        int postType = ArticleParams.ARTICLE;
        switch (mType){
            case ArticleParams.BASED_ON_TAGS:
            case ArticleParams.LATEST_ARTICLES:
            case ArticleParams.RELATED_ARTICLES:
                id = String.valueOf(IViewAllPresenter.getArticle(position).getArticle_Id());
                postType = ArticleParams.ARTICLE;
                fragmentTag = FullArticleFragment.class.getSimpleName();
                break;

            case ArticleParams.LATEST_PRODUCTS:
            case ArticleParams.RELATED_PRODUCTS:
                id = String.valueOf(IViewAllPresenter.getProduct(position).getProduct_id());
                postType = ArticleParams.PRODUCT;
                fragmentTag = FullProductFragment.class.getSimpleName();
                break;
        }

        /*Intent intent = new Intent(getContext(), FullViewActivity.class);
        intent.putExtra(ArticleParams.ID, id);
        intent.putExtra(ArticleParams.POST_TYPE, postType);
        startActivity(intent);*/

        Bundle bundle = new Bundle();
        bundle.putInt(ArticleParams.POST_TYPE, postType);
        bundle.putString(ArticleParams.ID, id);
        IViewAllPresenter.loadFragment(fragmentTag, bundle);
    }
}
