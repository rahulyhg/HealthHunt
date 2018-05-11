package in.healthhunt.view.homeScreenView.article.viewall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.Author;
import in.healthhunt.model.articles.articleResponse.CategoriesItem;
import in.healthhunt.model.articles.articleResponse.MediaItem;
import in.healthhunt.model.articles.articleResponse.PostsItem;
import in.healthhunt.model.articles.articleResponse.TagsItem;
import in.healthhunt.model.articles.articleResponse.Title;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter.IViewAllPresenter;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class ViewAllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private IViewAllPresenter IViewAllPresenter;
    private Context mContext;
    private int mType;

    public ViewAllAdapter(Context context, IViewAllPresenter viewAllPresenter, int type) {
        mContext = context;
        IViewAllPresenter = viewAllPresenter;
        mType = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("TAg@@","viewType");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_article_item_view, parent, false);
        return IViewAllPresenter.createArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (mType) {
            case ArticleParams.TAGS_VIEW_ALL_TYPE:{
                List<PostsItem> list = IViewAllPresenter.getAllArticles();
                PostsItem tagsItem = list.get(position);
                setContent(((ViewAllHolder)holder), tagsItem);
                break;
            }

            case ArticleParams.LATEST_VIEW_ALL_TYPE:{
                break;
            }

            case ArticleParams.LATEST_PRODUCT_VIEW_ALL_TYPE:{
                break;
            }
        }
    }

    private void setContent(ViewAllHolder holder, PostsItem postsItem) {
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
                Glide.with(mContext).load(url).override(300, 100).placeholder(R.drawable.artical).into(holder.mArticleImage);
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
                        .bitmapTransform(new CropCircleTransformation(mContext))/*.placeholder(R.mipmap.default_profile)*/
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
            /*if(date != null) {
                date = HealthHuntUtility.getDateWithFormat("dd-MMM-yyyy", date);
            }*/
            holder.mArticleDate.setText(date);
        }
    }

    @Override
    public int getItemCount() {
        return IViewAllPresenter.getCount();
    }

}
