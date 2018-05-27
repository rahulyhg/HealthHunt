package in.healthhunt.view.tagView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.healthhunt.R;
import in.healthhunt.model.tags.TagItem;
import in.healthhunt.presenter.tagPresenter.ITagPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TagAdapter extends RecyclerView.Adapter<TagViewHolder> {

    private ITagPresenter ITagPresenter;
    private Context mContext;
    private boolean isSelectAll;
    private Map<String, Integer> iconMap;
    private OnClickListener mOnClickListener;

    public TagAdapter(Context context, ITagPresenter tagPresenter) {
        mContext = context;
        ITagPresenter = tagPresenter;
        createIconMap();
    }

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item_view, parent, false);
        return ITagPresenter.createTagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {

        List<TagItem> tags = ITagPresenter.getTagList();
        TagItem tagItem = tags.get(position);

        String tagName = tagItem.getName();
        if(iconMap.containsKey(tagName)){
            Log.i("TAGPress", "name " + tagName);
            holder.mTagImage.setImageResource(iconMap.get(tagName));
        }
        holder.mTag.setText(tagName);
        holder.setViewPos(position);
        holder.updateTag(tagItem);

        String url = tagItem.getIcon();
        if(url != null) {
            Glide.with(mContext)
                    .load(url)
                    .placeholder(R.mipmap.ic_exercise)
                    .into(holder.mTagImage);
        }
    }

    @Override
    public int getItemCount() {
        return ITagPresenter.getTagCount();
    }


    public void setSelectAll(boolean isAll) {
        List<TagItem> list = ITagPresenter.getTagList();
        for(TagItem tagItem: list) {
            tagItem.setPressed(isAll);
        }
        notifyDataSetChanged();
    }

    public void setClickListener(OnClickListener onClickListener){
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onItemClick(int position);
    }

    private void createIconMap() {
        iconMap = new HashMap<String, Integer>();

        iconMap.put("Addiction", R.mipmap.ic_addiction);
        iconMap.put("alcohol", R.mipmap.ic_alcohol);
        iconMap.put("ayurveda", R.mipmap.ic_ayurvedic);
        iconMap.put("beauty", R.mipmap.ic_beauty);
        iconMap.put("detox", R.mipmap.ic_detox);
        iconMap.put("Exercise", R.mipmap.ic_exercise);
        iconMap.put("friendship", R.mipmap.ic_friendship);
        iconMap.put("hair", R.mipmap.ic_hair);
        iconMap.put("happiness", R.mipmap.ic_happiness);
        iconMap.put("Health", R.mipmap.ic_health);

        iconMap.put("home", R.mipmap.ic_home);
        iconMap.put("hygiene", R.mipmap.ic_hygiene);
        iconMap.put("love", R.mipmap.ic_love);
        iconMap.put("men's health", R.mipmap.ic_mens_health);
        iconMap.put("mental wellbeing", R.mipmap.ic_mental);
        iconMap.put("Nutrition", R.mipmap.ic_nutrition);
        iconMap.put("parenting", R.mipmap.ic_parents);
        iconMap.put("pregnancy", R.mipmap.ic_pregnant);
        iconMap.put("recipe", R.mipmap.ic_recipe);

        iconMap.put("self-development", R.mipmap.ic_self_development);
        iconMap.put("sex", R.mipmap.ic_sex);
        iconMap.put("Skin", R.mipmap.ic_skin);
        iconMap.put("spirituality", R.mipmap.ic_spirit);
        iconMap.put("students", R.mipmap.ic_students_cap);
        iconMap.put("weight", R.mipmap.ic_weight);
        iconMap.put("women's health", R.mipmap.ic_womens_health);
        iconMap.put("Yoga", R.mipmap.ic_yoga);
    }
}