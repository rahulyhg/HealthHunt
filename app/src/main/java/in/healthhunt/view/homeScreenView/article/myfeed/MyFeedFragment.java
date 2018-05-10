package in.healthhunt.view.homeScreenView.article.myfeed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.IMyFeedPresenter;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.MyFeedPresenterImp;
import in.healthhunt.view.homeScreenView.HomeActivity;

/**
 * Created by abhishekkumar on 5/3/18.
 */

public class MyFeedFragment extends Fragment implements IMyFeedView {

    @BindView(R.id.my_feed_recycler_list)
    RecyclerView mFeedViewer;

    private IMyFeedPresenter IMyFeedPresenter;
    private FragmentManager mFragmentManager;
    private MyFeedAdapter mFeedAdapter;
    private List<String> mArticleNames = new ArrayList<String>();
    private String[] mArticleNames1= {"Based on tags", "continue reading", "Trending", "Sponsored", "Top Products", "Latest articles",
    "Webinars", "Latest products"};

    private List<Integer> mArticles = new ArrayList<Integer>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_feed, container, false);
        ButterKnife.bind(this, view);

        Log.i("TagFrag", "MyFeed Fragment");
        mFragmentManager = getFragmentManager();
        IMyFeedPresenter = new MyFeedPresenterImp(getContext(), this);


        mArticleNames.add("Based on tags");
        mArticleNames.add("continue reading");
        mArticleNames.add("Trending");
        mArticleNames.add("Sponsored");
        mArticleNames.add("Top Products");
        mArticleNames.add("Latest articles");
        mArticleNames.add("Webinars");
        mArticleNames.add("Latest products");


        mArticles.add(R.layout.article_view);
        mArticles.add(R.layout.continue_article_view);
        mArticles.add(R.layout.trending_article_view);
        mArticles.add(R.layout.sponsored_article_view);
        mArticles.add(R.layout.top_products_article_view);
        mArticles.add(R.layout.latest_article_view);
        mArticles.add(R.layout.webinars_article_view);
        mArticles.add(R.layout.latest_products_article_view);
        setAdapter();
        return view;
    }

    private void setAdapter() {
        mFeedAdapter = new MyFeedAdapter(IMyFeedPresenter, mArticles, mArticleNames);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mFeedViewer.setLayoutManager(layoutManager);
        mFeedViewer.setAdapter(mFeedAdapter);
        mFeedAdapter.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mArticles.size();
    }

    @Override
    public RecyclerView.ViewHolder createArticleHolder(View view, int type) {

        RecyclerView.ViewHolder viewHolder = null;
       // switch (type) {
            if(mArticleNames.get(type).equals("Based on tags"))
                viewHolder = new ArticleViewHolder(view, mFragmentManager, this);

            else if(mArticleNames.get(type).equals("continue reading"))
                viewHolder = new ContinueArticleViewHolder(view, mFragmentManager, this, type);

            else if(mArticleNames.get(type).equals("Trending"))
                viewHolder = new TrendingArticleViewHolder(view, this);

            else if(mArticleNames.get(type).equals("Sponsored"))
                viewHolder = new SponsoredArticleViewHolder(view, this, type);

           else if(mArticleNames.get(type).equals("Top Products"))
                viewHolder = new TopProductViewHolder(view);

            else if(mArticleNames.get(type).equals("Latest articles"))
                viewHolder = new LatestArticleViewHolder(view, mFragmentManager, this);

            else if(mArticleNames.get(type).equals("Webinars"))
                viewHolder = new WebinarsArticleViewHolder(view, mFragmentManager, this);

            else if(mArticleNames.get(type).equals("Latest products"))
                viewHolder = new LatestProductViewHolder(view, mFragmentManager, this);

        return viewHolder;
    }

    @Override
    public void onClickViewAll(String tag, Bundle bundle) {
        ((HomeActivity)getActivity()).getHomePresenter().loadFragment(tag, bundle);
    }

    @Override
    public void onClickCrossView(int index) {
        Log.i("TAG", "Index " + index);
       mArticleNames.remove(index);
       mFeedAdapter.deleteItem(index);    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("TagFrag", "onDestroyView");

        mFeedViewer = null;
        mFeedAdapter = null;
    }
}
