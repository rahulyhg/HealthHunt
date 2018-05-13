package in.healthhunt.view.homeScreenView.article.myfeed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.view.fullView.FullViewActivity;
import in.healthhunt.view.homeScreenView.HomeActivity;
import in.healthhunt.view.homeScreenView.article.viewall.ViewAllFragment;
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

    int mType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_item_view, container, false);
        mUnBinder = ButterKnife.bind(this, view);

        Bundle bundle = getArguments();

        if(bundle != null) {
            boolean isLast = bundle.getBoolean(ArticleParams.IS_LAST_PAGE);
            mType = bundle.getInt(ArticleParams.ARTICLE_TYPE);

            if(!isLast) {
                mTagItemView.setVisibility(View.VISIBLE);
                mViewAll.setVisibility(View.GONE);
                setContent(bundle);
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
        ((HomeActivity)getActivity()).updateNavigation();
        ((HomeActivity)getActivity()).getHomePresenter().loadFragment(ViewAllFragment.class.getSimpleName(), bundle);
    }

    private void openFullView() {
        Bundle bundle = getArguments();

        Intent intent = new Intent(getContext(), FullViewActivity.class);
        if(bundle != null) {
            intent.putExtra(ArticleParams.ID, bundle.getString(ArticleParams.ID));
        }
        startActivity(intent);
    }

    private void setContent(Bundle bundle) {
        String categoryName = bundle.getString(ArticleParams.CATEGORY_NAME);
        if(categoryName != null) {
            mCategoryName.setText(categoryName);
        }



        String authorName = bundle.getString(ArticleParams.AUTHOR_NAME);
        if(authorName != null) {
            mAuthorName.setText(authorName);
        }

        String articleTitle = bundle.getString(ArticleParams.ARTICLE_TITLE);
        if(articleTitle != null) {
            mArticleTitle.setText(articleTitle);
        }

        ArrayList<String> arrayList = bundle.getStringArrayList(ArticleParams.ARTICLE_TAGS_NAME_LIST);
        String tags = "";
        if(tags != null && !tags.isEmpty()) {
            for (String tag : arrayList) {
                tags = "#" + tag + "  ";
            }
        }

        mHashTags.setText(tags);

        String readingTime = bundle.getString(ArticleParams.ARTICLE_READ_TIME);
        if(readingTime != null) {
            mReadingTime.setText(readingTime + " min read");
        }

        String date = bundle.getString(ArticleParams.ARTICLE_DATE);
        if(date != null) {
            mArticleDate.setText(date);
        }


        String articleUrl = bundle.getString(ArticleParams.ARTICLE_URL);
        if(articleUrl != null) {
            Log.i("TAG11", "articleUrl " + articleUrl);
            Glide.with(this).load(articleUrl).placeholder(R.drawable.artical).override(300,100).into(mArticleImage);
        }
        else {
            mArticleImage.setBackgroundResource(R.drawable.artical);
        }

        String authorUrl = bundle.getString(ArticleParams.AUTHOR_URL);
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
}
