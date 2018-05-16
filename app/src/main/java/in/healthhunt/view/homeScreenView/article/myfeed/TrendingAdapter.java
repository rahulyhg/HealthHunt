package in.healthhunt.view.homeScreenView.article.myfeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.articleResponse.CategoriesItem;
import in.healthhunt.model.articles.articleResponse.TagsItem;
import in.healthhunt.model.articles.articleResponse.Title;
import in.healthhunt.model.articles.commonResponse.Author;
import in.healthhunt.model.articles.commonResponse.MediaItem;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.ITrendingSponsoredPresenter;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class TrendingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ITrendingSponsoredPresenter mPresenter;
    private Context mContext;
    private ClickListener mClickListener;

    public TrendingAdapter(Context context, ITrendingSponsoredPresenter presenter) {
        mPresenter = presenter;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("TAg@@","viewType");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item_view, parent, false);
        return new TrendingItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setContent(((TrendingItemViewHolder)holder), position);
    }

    private void setContent(TrendingItemViewHolder holder, int pos) {

        ArticlePostItem postsItem = mPresenter.getTrendingArticles(pos);
        if(postsItem != null) {
            String url = null;
            List<MediaItem> mediaItems = postsItem.getMedia();
            if(mediaItems != null && !mediaItems.isEmpty()) {
                MediaItem media = mediaItems.get(0);
                if("image".equals(media.getMedia_type())) {
                    url = media.getUrl();

                }
            }
            if(url != null) {
                Log.i("TAG11", "url " + url);
                Glide.with(mContext).load(url).placeholder(R.drawable.artical).into(holder.mArticleImage);
            }
            else {
                holder.mArticleImage.setBackgroundResource(R.drawable.artical);
            }


            String categoryName = null;
            List<CategoriesItem> categories = postsItem.getCategories();
            if(categories != null && !categories.isEmpty()){
                categoryName = categories.get(0).getName();
            }

            holder.mCategoryName.setText(categoryName);

            Author author = postsItem.getAuthor();
            if(author != null){
                holder.mAuthorName.setText(author.getName());

                String authorUrl = author.getUrl();
                authorUrl = authorUrl.replace("\n", "");
                Glide.with(mContext)
                        .load(authorUrl)
                        .bitmapTransform(new CropCircleTransformation(mContext)).placeholder(R.mipmap.default_profile)
                        .into(holder.mAuthorImage);

            }

            Title title = postsItem.getTitle();
            String articleTitle = null;
            if(title != null) {
                articleTitle = title.getRendered();
            }
            holder.mArticleTitle.setText(articleTitle);


            String tagsName = "";
            List<TagsItem> tagItems = postsItem.getTags();
            if(tagItems != null && !tagItems.isEmpty()) {
                for(TagsItem  tagItem: tagItems) {
                    tagsName = tagsName + "#" + tagItem.getName() + "  ";
                }
            }

            holder.mHashTags.setText(tagsName);


            String readingTime = postsItem.getRead_time();
            readingTime = readingTime + " min read";
            holder.mReadingTime.setText(readingTime);

            String date = postsItem.getDate();
            if(date != null) {
                date = HealthHuntUtility.getDateWithFormat(date);
            }
            holder.mArticleDate.setText(date);
        }
    }


    @Override
    public int getItemCount() {
        return mPresenter.getCount();
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public interface ClickListener {
        void ItemClicked(View v, int position);
    }

    public class TrendingItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.article_item_view)
        RelativeLayout mArticleView;

        @BindView(R.id.article_image)
        ImageView mArticleImage;

        @BindView(R.id.article_bookmark)
        ImageView mArticleBookMark;

        @BindView(R.id.category_image)
        ImageView mCategoryImage;

        @BindView(R.id.category_name)
        TextView mCategoryName;

        @BindView(R.id.author_pic)
        ImageView mAuthorImage;

        @BindView(R.id.author_name)
        TextView mAuthorName;

        @BindView(R.id.article_content)
        TextView mArticleTitle;

        @BindView(R.id.hash_tags)
        TextView mHashTags;

        @BindView(R.id.reading_time)
        TextView mReadingTime;

        @BindView(R.id.article_date)
        TextView mArticleDate;

        public TrendingItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void notifyDataChanged() {
            notifyDataSetChanged();
        }

        @OnClick(R.id.article_item_view)
        void onClick(View view) {
            if(mClickListener != null) {
                mClickListener.ItemClicked(view, getAdapterPosition());
            }
        }
    }
}
