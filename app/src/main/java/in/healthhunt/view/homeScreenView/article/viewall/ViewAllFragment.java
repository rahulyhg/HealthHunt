package in.healthhunt.view.homeScreenView.article.viewall;

import android.app.ProgressDialog;
import android.content.Intent;
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
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter.IViewAllPresenter;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter.ViewAllPresenterImp;
import in.healthhunt.view.fullView.FullViewActivity;
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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgress = new ProgressDialog(getContext());
        mProgress.setIndeterminate(true);
        mProgress.setCancelable(false);
        mProgress.setMessage(getResources().getString(R.string.please_wait));
        IViewAllPresenter = new ViewAllPresenterImp(getContext(), this);

        Bundle bundle = getArguments();
        if(bundle != null) {
            mType = bundle.getInt(ArticleParams.ARTICLE_TYPE);
        }

        Log.i("TAGTYPE " , "TYPE " + mType);
        IViewAllPresenter.fetchAll(mType);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_all, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if(bundle != null) {
            mType = bundle.getInt(ArticleParams.ARTICLE_TYPE);
            IHomeView homeView = (IHomeView) getActivity();
            homeView.updateTitle(getArticleName());
        }

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
                viewHolder = new ViewAllArticleHolder(view, this);
                break;
            case ArticleParams.LATEST_PRODUCTS_ARTICLES:
                viewHolder = new ViewAllProductHolder(view, this);
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
                layout = R.layout.view_all_article_item_view;
                break;


            case ArticleParams.LATEST_PRODUCTS_ARTICLES:
                layout = R.layout.view_all_product_item_view;
                break;
        }

        return layout;
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
            case ArticleParams.LATEST_PRODUCTS_ARTICLES:
                name = ArticleParams.TEXT_LATEST_PRODUCTS_ARTICLES;
                break;
        }

        return name;
    }

    @Override
    public void ItemClicked(View v, int position) {
        String id = null;
        switch (mType){
            case ArticleParams.BASED_ON_TAGS:
            case ArticleParams.LATEST_ARTICLES:
                id = String.valueOf(IViewAllPresenter.getArticle(position).getId());
                break;

            case ArticleParams.LATEST_PRODUCTS_ARTICLES:
                id = IViewAllPresenter.getProduct(position).getId();
                break;
        }

        Bundle bundle = getArguments();
        Intent intent = new Intent(getContext(), FullViewActivity.class);
        if(bundle != null) {
            intent.putExtra(ArticleParams.ID, id);
        }
        startActivity(intent);
    }
}
