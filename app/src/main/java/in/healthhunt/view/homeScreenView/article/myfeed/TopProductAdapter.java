package in.healthhunt.view.homeScreenView.article.myfeed;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.healthhunt.R;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.ITopProductPresenter;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class TopProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int mCount;
    private ITopProductPresenter ITopProductPresenter;

    public TopProductAdapter(int count, ITopProductPresenter topProductPresenter) {
        mCount = count;
        ITopProductPresenter = topProductPresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("TAg@@","viewType");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_products_article_item_view, parent, false);
        return ITopProductPresenter.createArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return ITopProductPresenter.getCount();
    }

    public static class  TopProductItemViewHolder extends RecyclerView.ViewHolder {

        public TopProductItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
