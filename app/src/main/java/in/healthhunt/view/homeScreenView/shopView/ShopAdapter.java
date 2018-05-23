package in.healthhunt.view.homeScreenView.shopView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.healthhunt.R;
import in.healthhunt.presenter.homeScreenPresenter.shopPresenter.IShopPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopViewHolder> {

    private IShopPresenter IShopPresenter;
    private Context mContext;
    private ClickListener mClickListener;

    public ShopAdapter(Context context, IShopPresenter shopPresenter) {
        mContext = context;
        IShopPresenter = shopPresenter;
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shop_item_view, parent, false);
        return IShopPresenter.createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return IShopPresenter.getCount();
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public interface ClickListener {
        void ItemClicked(View v, int position);
    }
}