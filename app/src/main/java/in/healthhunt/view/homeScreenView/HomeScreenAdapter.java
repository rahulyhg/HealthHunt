package in.healthhunt.view.homeScreenView;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_view, parent, false);
        return IHomePresenter.createArticleHolder(view, mFragmentManager);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
  }

    @Override
    public int getItemCount() {
        return IHomePresenter.getCount();
    }
}
