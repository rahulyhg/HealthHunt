package in.healthhunt.view.homeScreenView.article;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.healthhunt.R;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class TopProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int mCount;

    public TopProductAdapter(int count) {
        mCount = count;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("TAg@@","viewType");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_products_article_item_view, parent, false);
        return new TopProductItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    public class  TopProductItemViewHolder extends RecyclerView.ViewHolder {

        public TopProductItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
