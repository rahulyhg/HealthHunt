package in.healthhunt.view.homeScreenView.article.myfeed;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.IMyFeedPresenter;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class MyFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private IMyFeedPresenter IMyFeedPresenter;


    public MyFeedAdapter(IMyFeedPresenter feedPresenter) {
        IMyFeedPresenter = feedPresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        Log.i("TAG561", "onCreate ViewType " + viewType);
        int layout = IMyFeedPresenter.getView(viewType);
        view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return IMyFeedPresenter.createArticleHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder != null && holder instanceof ContinueArticleViewHolder) {
            ((ContinueArticleViewHolder) holder).hideContinueView();
        }
    }

    @Override
    public int getItemCount() {
        Map<Integer, Integer> map = IMyFeedPresenter.getArticlesType();
        int count = 0;
        if(map != null){
            count = map.size();
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        Map<Integer, Integer> map = IMyFeedPresenter.getArticlesType();
        int type = 0;
        if(map != null && position < map.size()){
            type = map.get(position);
        }
        return type;
    }

    public void deleteItem(int index) {
        notifyItemRemoved(index);
    }
}
