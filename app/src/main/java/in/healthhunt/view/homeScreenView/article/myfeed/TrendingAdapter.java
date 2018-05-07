package in.healthhunt.view.homeScreenView.article.myfeed;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.healthhunt.R;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class TrendingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int mCount;

    public TrendingAdapter(int count) {
        mCount = count;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("TAg@@","viewType");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item_view, parent, false);
        return new TrendingItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    public class TrendingItemViewHolder extends RecyclerView.ViewHolder {

        public TrendingItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
