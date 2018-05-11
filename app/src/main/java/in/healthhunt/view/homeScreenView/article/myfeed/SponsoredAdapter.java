package in.healthhunt.view.homeScreenView.article.myfeed;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class SponsoredAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int mCount;

    public SponsoredAdapter(int count) {
        mCount = count;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("TAg@@","viewType");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item_view, parent, false);
        return new SponsoredItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SponsoredItemViewHolder)holder).mArticleImage.setBackgroundResource(R.drawable.artical);
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    public class SponsoredItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.article_image)
        ImageView mArticleImage;

        public SponsoredItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
