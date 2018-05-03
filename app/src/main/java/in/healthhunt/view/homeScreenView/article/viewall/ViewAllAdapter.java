package in.healthhunt.view.homeScreenView.article.viewall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.healthhunt.R;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter.IViewAllPresenter;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class ViewAllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private IViewAllPresenter IViewAllPresenter;
    private Context mContext;

    public ViewAllAdapter(Context context, IViewAllPresenter viewAllPresenter) {
        mContext = context;
        IViewAllPresenter = viewAllPresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("TAg@@","viewType");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_article_item_view, parent, false);
        return IViewAllPresenter.createArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return IViewAllPresenter.getCount();
    }

}
