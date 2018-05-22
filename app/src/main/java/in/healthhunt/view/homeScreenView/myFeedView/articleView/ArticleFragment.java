package in.healthhunt.view.homeScreenView.myFeedView.articleView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import butterknife.Unbinder;
import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.articleResponse.CategoriesItem;
import in.healthhunt.model.articles.articleResponse.TagsItem;
import in.healthhunt.model.articles.articleResponse.Title;
import in.healthhunt.model.articles.commonResponse.Author;
import in.healthhunt.model.articles.commonResponse.CurrentUser;
import in.healthhunt.model.articles.commonResponse.MediaItem;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.articlePresenter.IArticlePresenter;
import in.healthhunt.view.fullView.FullViewActivity;
import in.healthhunt.view.viewAll.ViewAllFragment;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class ArticleFragment extends Fragment {

    private Unbinder mUnBinder;


    @BindView(R.id.tags_article_view_item)
    RelativeLayout mTagItemView;

    @BindView(R.id.last_page_view_all)
    TextView mViewAll;

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

    private int mType;
    private int mPos;
    private IArticlePresenter IArticlePresenter;
    private ArticlePostItem mArticlePostItem;

    public ArticleFragment(){
    }

    @SuppressLint("ValidFragment")
    public ArticleFragment(IArticlePresenter articlePresenter) {
        IArticlePresenter = articlePresenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_item_view, container, false);
        mUnBinder = ButterKnife.bind(this, view);

        Bundle bundle = getArguments();

        if(bundle != null) {
            boolean isLast = bundle.getBoolean(ArticleParams.IS_LAST_PAGE);
            mType = bundle.getInt(ArticleParams.ARTICLE_TYPE);
            mPos = bundle.getInt(ArticleParams.POSITION);
            mArticlePostItem = IArticlePresenter.getArticle(mPos);

            if(!isLast) {
                mTagItemView.setVisibility(View.VISIBLE);
                mViewAll.setVisibility(View.GONE);
                setContent();
            }
            else {
                mTagItemView.setVisibility(View.GONE);
                mViewAll.setVisibility(View.VISIBLE);
            }
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }


    @OnClick(R.id.tags_article_view_item)
    void onArticleClick(){
        openFullView();
    }

    @OnClick(R.id.last_page_view_all)
    void onViewAll(){
        Bundle bundle = new Bundle();
        bundle.putInt(ArticleParams.ARTICLE_TYPE, mType);
        IArticlePresenter.updateBottomNavigation();
        IArticlePresenter.loadFragment(ViewAllFragment.class.getSimpleName(), bundle);
    }

    private void openFullView() {
        Intent intent = new Intent(getContext(), FullViewActivity.class);
        intent.putExtra(ArticleParams.ID, String.valueOf(mArticlePostItem.getId()));
        intent.putExtra(ArticleParams.POST_TYPE, ArticleParams.ARTICLE);
        startActivity(intent);
    }

    @OnClick(R.id.article_bookmark)
    void onBookMark(){
        Log.i("TAGBOOK","On Bookmark Click");
        String id = String.valueOf(mArticlePostItem.getId());
        CurrentUser currentUser = mArticlePostItem.getCurrent_user();
        Log.i("TAGBOOK","currentUser " + currentUser);
        if(currentUser != null) {
            if(!currentUser.isBookmarked()){
                Log.i("TAGBOOK","Bookmark");
                IArticlePresenter.bookmark(id, mType);
            }
            else {
                IArticlePresenter.unBookmark(id, mType);
            }
        }
    }

    private void setContent() {
        String categoryName = null;
        List<CategoriesItem> categories = mArticlePostItem.getCategories();
        if(categories != null && !categories.isEmpty()){
            categoryName = categories.get(0).getName();
        }
        if(categoryName != null) {
            mCategoryName.setText(categoryName);
        }


        Author author = mArticlePostItem.getAuthor();
        if(author != null){
            String authorName = author.getName();
            if(authorName != null) {
                mAuthorName.setText(authorName);
            }

            String authorUrl = author.getUrl();
            if(authorUrl != null) {
                authorUrl = authorUrl.replace("\n", "");
                Glide.with(this)
                        .load(authorUrl).placeholder(R.mipmap.default_profile)
                        .bitmapTransform(new CropCircleTransformation(getContext()))
                        .into(mAuthorImage);
            }
            else {
                mAuthorImage.setBackgroundResource(R.mipmap.default_profile);
            }
        }



        Title title = mArticlePostItem.getTitle();
        String articleTitle = null;
        if(title != null) {
            articleTitle = title.getRendered();
        }
        if(articleTitle != null) {
            mArticleTitle.setText(articleTitle);
        }


        String tagsName = "";
        List<TagsItem> tagItems = mArticlePostItem.getTags();
        if(tagItems != null && !tagItems.isEmpty()) {
            for(TagsItem  tagItem: tagItems) {
                tagsName = "#" + tagItem.getName() + " ";
            }
        }
        mHashTags.setText(tagsName);

        String readingTime = mArticlePostItem.getRead_time();
        if(readingTime != null) {
            mReadingTime.setText(readingTime + " min read");
        }

        String date = mArticlePostItem.getDate();
        date = HealthHuntUtility.getDateWithFormat(date);
        if(date != null) {
            mArticleDate.setText(date);
        }

        CurrentUser currentUser = mArticlePostItem.getCurrent_user();
        if(currentUser != null) {
            Log.i("TAGUSER", " ISBOOK" + currentUser.isBookmarked());
            updateBookMark(currentUser.isBookmarked());
        }

        String articleUrl = null;
        List<MediaItem> mediaItems = mArticlePostItem.getMedia();
        if(mediaItems != null && !mediaItems.isEmpty()) {
            MediaItem media = mediaItems.get(0);
            if("image".equals(media.getMedia_type())) {
                articleUrl = media.getUrl();
            }
        }
        if(articleUrl != null) {
            Glide.with(this).load(articleUrl).placeholder(R.drawable.artical).into(mArticleImage);
        }
        else {
            mArticleImage.setBackgroundResource(R.drawable.artical);
        }
    }

    private void updateBookMark(boolean isBookMark) {
        Log.i("TAGBOOKMARK", "ISBOOK " + isBookMark);
        if(!isBookMark){
            mArticleBookMark.setColorFilter(null);
            mArticleBookMark.setImageResource(R.mipmap.ic_bookmark);
        }
        else {
            mArticleBookMark.setColorFilter(ContextCompat.getColor(getContext(), R.color.hh_green_light2), PorterDuff.Mode.SRC_IN);
        }
    }
}
