package in.healthhunt.view.homeScreenView;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import in.healthhunt.R;
import in.healthhunt.presenter.homeScreenPresenter.IHomePresenter;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class HomeScreenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mHomeItemList;
    private IHomePresenter IHomePresenter;
    private FragmentManager mFragmentManager;

    public HomeScreenAdapter(IHomePresenter homePresenter, FragmentManager fragmentManager) {
        IHomePresenter = homePresenter;
        mFragmentManager = fragmentManager;
    }

    /*@Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        Log.i("TAG11", "type= " + viewType);
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
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.latest_products_article_view, parent, false);
                break;
        }
        return IHomePresenter.createArticleHolder(view, mFragmentManager, viewType);
    }*/

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position == 5){
            //holder.
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
