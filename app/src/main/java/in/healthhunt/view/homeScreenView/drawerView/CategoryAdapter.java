package in.healthhunt.view.homeScreenView.drawerView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.model.tags.TagItem;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.IHomePresenter;

/**
 * Created by abhishekkumar on 5/26/18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private IHomePresenter IHomePresenter;
    private Context mContext;
    private ClickListener mClickListener;


    public CategoryAdapter(Context context, IHomePresenter homePresenter) {
        mContext = context;
        IHomePresenter = homePresenter;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_view, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        TagItem item = IHomePresenter.getCategory(position);
        String name = item.getName();
        holder.mCategoryName.setText(name);

        int res = HealthHuntUtility.getCategoryIcon(name);

        if(IHomePresenter.isCategoryContain(item)) {
            if (name.equalsIgnoreCase(Constants.All)) {
                holder.mCategoryView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.hh_green_light2));
                holder.mCategoryImage.setColorFilter(null);
                holder.mCategoryName.setTextColor(Color.WHITE);

            }
            else if (name.equalsIgnoreCase(Constants.NUTRITION)) {
                holder.mCategoryView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.hh_orange_light));
                holder.mCategoryImage.setColorFilter(null);
                holder.mCategoryName.setTextColor(Color.WHITE);

            }
            else if (name.equalsIgnoreCase(Constants.FITNESS)) {
                holder.mCategoryView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.hh_blue_light));
                holder.mCategoryImage.setColorFilter(null);
                holder.mCategoryName.setTextColor(Color.WHITE);
            }
            else if (name.equalsIgnoreCase(Constants.ORGANIC_BEAUTY)) {
                holder.mCategoryView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.hh_green_light2));
                holder.mCategoryImage.setColorFilter(null);
                holder.mCategoryName.setTextColor(Color.WHITE);

            }
            else if (name.equalsIgnoreCase(Constants.MENTAL_WELLBEING)) {
                holder.mCategoryView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.hh_yello_light));
                holder.mCategoryImage.setColorFilter(null);
                holder.mCategoryName.setTextColor(Color.WHITE);

            }
            else if (name.equalsIgnoreCase(Constants.LOVE)) {
                holder.mCategoryView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.hh_red_light));
                holder.mCategoryImage.setColorFilter(null);
                holder.mCategoryName.setTextColor(Color.WHITE);

            }
        }
        else {
            holder.mCategoryImage.setColorFilter(ContextCompat.getColor(mContext, R.color.hh_text_color), PorterDuff.Mode.SRC_ATOP);
            holder.mCategoryView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.hh_background_light));
            holder.mCategoryName.setTextColor(ContextCompat.getColor(mContext, R.color.hh_text_color3));
        }

        holder.mCategoryImage.setImageResource(res);
    }

    @Override
    public int getItemCount() {
        return IHomePresenter.getCategoryCount();
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public interface ClickListener {
        void ItemClicked(View v, int pos);
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.category_drawer_item_view)
        LinearLayout mCategoryView;

        @BindView(R.id.category_image)
        ImageView mCategoryImage;

        @BindView(R.id.category_name)
        TextView mCategoryName;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.category_drawer_item_view)
        void onCategoryClick(View view){
            mClickListener.ItemClicked(view, getAdapterPosition());
            notifyDataSetChanged();
        }
    }
}
