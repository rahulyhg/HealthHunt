package in.healthhunt.view.homeScreenView.article.myfeed;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.IMyFeedPresenter;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class MyFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private IMyFeedPresenter IMyFeedPresenter;
    private List<Integer> mArticles;
    private List<String> mArticleNames;


    public MyFeedAdapter(IMyFeedPresenter feedPresenter, List<Integer> articles, List<String> artiname) {
        IMyFeedPresenter = feedPresenter;
        mArticles = articles;
        mArticleNames = artiname;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        Log.i("TAG561", "onCreate ViewType " + viewType);
        int layout = mArticles.get(viewType);
        view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        /*Log.i("TAG11", "type= " + viewType);
        String article;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_view, parent, false);
                break;

            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.continue_article_view, parent, false);
                break;

            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_article_view, parent, false);
                break;

            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sponsored_article_view, parent, false);
                break;

            case 4:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_products_article_view, parent, false);
                break;

            case 5:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_view, parent, false);
                break;

            case 6:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_view, parent, false);
                break;

            case 7:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.latest_products_article_view, parent, false);
                break;*/
       // }
        return IMyFeedPresenter.createArticleHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.i("TAG56", "Type Pos " + position);
        return position;
    }

    public void deleteItem(int index) {
        mArticles.remove(index);
        //notifyDataSetChanged();
        notifyItemRemoved(index);
    }
}
