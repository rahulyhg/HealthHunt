package in.healthhunt.view.fullView.fullViewFragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.bumptech.glide.Glide;

import java.io.UnsupportedEncodingException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.commonResponse.Author;
import in.healthhunt.model.articles.commonResponse.CategoriesItem;
import in.healthhunt.model.articles.commonResponse.Content;
import in.healthhunt.model.articles.commonResponse.CurrentUser;
import in.healthhunt.model.articles.commonResponse.Excerpt;
import in.healthhunt.model.articles.commonResponse.Likes;
import in.healthhunt.model.articles.commonResponse.MediaItem;
import in.healthhunt.model.articles.commonResponse.Synopsis;
import in.healthhunt.model.articles.commonResponse.TagsItem;
import in.healthhunt.model.articles.commonResponse.Title;
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.model.beans.SpaceDecoration;
import in.healthhunt.model.comment.CommentsItem;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.model.utility.URLImageParser;
import in.healthhunt.presenter.fullPresenter.FullPresenterImp;
import in.healthhunt.presenter.fullPresenter.IFullPresenter;
import in.healthhunt.view.fullView.commentView.CommentAdapter;
import in.healthhunt.view.fullView.commentView.CommentViewHolder;
import in.healthhunt.view.homeScreenView.HomeActivity;
import in.healthhunt.view.homeScreenView.IHomeView;
import in.healthhunt.view.viewAll.ViewAllFragment;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by abhishekkumar on 5/24/18.
 */

public class FullArticleFragment extends Fragment implements IFullFragment, CommentAdapter.ClickListener,
        RelatedArticlesAdapter.ClickListener, RelatedProductAdapter.ClickListener {

    @BindView(R.id.full_article_read_time)
    TextView mReadTime;

    @BindView(R.id.full_likes_count)
    TextView mLikesCount;

    @BindView(R.id.full_article_like)
    ImageView mLikeImage;

    @BindView(R.id.full_view_category_name)
    TextView mCategoryName;

    @BindView(R.id.full_article_category_icon)
    ImageView mCategoryImage;

    @BindView(R.id.full_article_image)
    ImageView mArticleImage;

    @BindView(R.id.full_article_content)
    TextView mTitle;

    @BindView(R.id.author_pic)
    ImageView mAuthorImage;

    @BindView(R.id.author_name)
    TextView mAuthorName;

    @BindView(R.id.author_publish_date)
    TextView mPublishDate;

    @BindView(R.id.full_article_description)
    TextView mContent;

    @BindView(R.id.full_article_hash_tags)
    TextView mHashTags;

    @BindView(R.id.comment_count)
    TextView mCommentCount;

    @BindView(R.id.comments_view_all_text)
    TextView mCommentViewAll;

    @BindView(R.id.comments_view_all_arrow)
    ImageView mCommentArrow;

    @BindView(R.id.comment_view)
    LinearLayout mCommentView;

    @BindView(R.id.full_article_download)
    ImageView mDownloadView;

    @BindView(R.id.full_article_bookmark)
    ImageView mBookMark;

    @BindView(R.id.full_view)
    LinearLayout mFullView;

    @BindView(R.id.comments_recycler_list)
    RecyclerView mCommentViewer;

    @BindView(R.id.full_view_scroll)
    ScrollView mFullViewScroll;

    @BindView(R.id.send_comment)
    Button mSendComment;

    @BindView(R.id.comment_content)
    EditText mCommentContent;

    @BindView(R.id.related_recycler_list)
    RecyclerView mRelatedArticleViewer;

    @BindView(R.id.related_view_all)
    LinearLayout mArticleViewAll;

    @BindView(R.id.related_product_recycler_list)
    RecyclerView mRelatedProductViewer;

    @BindView(R.id.related_product_view_all)
    LinearLayout mProductViewAll;

    @BindView(R.id.related_product_view)
    LinearLayout mRelatedProductView;

    @BindView(R.id.related_article_root_view)
    LinearLayout mRelatedArticleView;

    private IFullPresenter IFullPresenter;
    private IHomeView IHomeView;
    private Unbinder mUnbinder;
    private int mType;
    private boolean isDownloaded;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IFullPresenter = new FullPresenterImp(getContext(), this);
        IHomeView  =(HomeActivity) getActivity();

        String id = getArguments().getString(ArticleParams.ID);
        mType = getArguments().getInt(ArticleParams.POST_TYPE);
        isDownloaded = getArguments().getBoolean(Constants.IS_DOWNLOADED);
        if(!isDownloaded) {
            IFullPresenter.fetchArticle(id);
        }
        else {
            fetchArticleFromDataBase(id);
        }
    }

    private void fetchArticleFromDataBase(String id) {
        IHomeView.showProgress();
        FetchArticlesTask productsTask = new FetchArticlesTask(id);
        productsTask.execute();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_article_view, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        IHomeView.setStatusBarTranslucent(true);
        IHomeView.hideBottomFooter();
        IHomeView.hideActionBar();
        setContent();
        //mCommentContent.setOnFocusChangeListener(focusListener);

        return view;
    }

    /*private View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus){
                mFullViewScroll.setScrollY(mCommentView.getScrollX());//;View.FOCUS_UP);
            } else {

            }
        }
    };*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null){
            mUnbinder.unbind();
        }
    }

    @Override
    public void showProgress() {
        IHomeView.showProgress();
    }

    @Override
    public void hideProgress() {
        IHomeView.hideProgress();
    }

    @Override
    public void setContent() {
        ArticlePostItem articlePost = IFullPresenter.getArticle();

        if (articlePost != null) {
            setTopImageContent(articlePost);
            setArticleContent(articlePost);
            setAboutContent(articlePost);
            setCommentContent(articlePost);
            setRelatedArticleAdapter();
            setRelatedProductAdapter();

            Log.i("TAGPOSTART", "PIOST " + articlePost);
        }
    }

    private void setRelatedArticleAdapter() {
        if(IFullPresenter.getRelatedArticlesCount() > 0){
            mRelatedArticleView.setVisibility(View.VISIBLE);
        }
        else {
            mRelatedArticleView.setVisibility(View.GONE);
        }

        RelatedArticlesAdapter relatedArticlesAdapter = new RelatedArticlesAdapter(getContext(), IFullPresenter);
        relatedArticlesAdapter.setClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRelatedArticleViewer.setLayoutManager(layoutManager);
        mRelatedArticleViewer.addItemDecoration(new SpaceDecoration(HealthHuntUtility.dpToPx(8, getContext()), SpaceDecoration.VERTICAL));
        mRelatedArticleViewer.setAdapter(relatedArticlesAdapter);
        mRelatedArticleViewer.setFocusableInTouchMode(false);
    }

    private void setRelatedProductAdapter() {

        if(IFullPresenter.getRelatedProductsCount() > 0){
            mRelatedProductView.setVisibility(View.VISIBLE);
        }
        else {
            mRelatedProductView.setVisibility(View.GONE);
        }
        RelatedProductAdapter relatedProductAdapter = new RelatedProductAdapter(getContext(), IFullPresenter);
        relatedProductAdapter.setClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRelatedProductViewer.setLayoutManager(layoutManager);
        mRelatedProductViewer.addItemDecoration(new SpaceDecoration(HealthHuntUtility.dpToPx(8, getContext()), SpaceDecoration.VERTICAL));
        mRelatedProductViewer.setAdapter(relatedProductAdapter);
        mRelatedProductViewer.setFocusableInTouchMode(false);
    }


    @Override
    public int getPostType() {
        return mType;
    }

    @Override
    public void updateBookMarkIcon() {
        CurrentUser currentUser = null;
        ArticlePostItem articlePost = IFullPresenter.getArticle();
        currentUser = articlePost.getCurrent_user();
        if(currentUser != null) {
            updateBookMark(currentUser.isBookmarked());
        }

        if(isDownloaded & currentUser != null){
            currentUser.save();
            articlePost.save();
            IHomeView.updateDownloadData();
        }
    }

    @Override
    public CommentViewHolder createViewHolder(View view) {
        return new CommentViewHolder(view, IFullPresenter, this);
    }

    private void setCommentAdapter(){
        CommentAdapter commentAdapter = new CommentAdapter(getContext(), IFullPresenter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mCommentViewer.setLayoutManager(layoutManager);
        mCommentViewer.addItemDecoration(new SpaceDecoration(HealthHuntUtility.dpToPx(8, getContext()), SpaceDecoration.VERTICAL));
        mCommentViewer.setAdapter(commentAdapter);
    }

    @Override
    public void updateCommentAdapter() {
        mCommentContent.setText("");
        CommentAdapter commentAdapter = (CommentAdapter) mCommentViewer.getAdapter();
        if(commentAdapter == null){
            setCommentAdapter();
        }
        else {
            commentAdapter.notifyDataSetChanged();
        }

        setCommentContent(IFullPresenter.getArticle());
    }

    @Override
    public void updateLike() {
        CurrentUser currentUser =  IFullPresenter.getArticle().getCurrent_user();
        if(currentUser != null) {
            int like = currentUser.getLike();
            int count = Integer.parseInt(mLikesCount.getText().toString());
            if (like > 0) {
                updateLikeIcon(true);
                count++;
            } else {
                updateLikeIcon(false);
                if(count > 0) {
                    count--;
                }
            }

            ArticlePostItem article = IFullPresenter.getArticle();
            if(article != null && article.getLikes() != null){
                article.getLikes().setLikes(String.valueOf(count));
                mLikesCount.setText(String.valueOf(count));
            }
        }
    }

    @Override
    public void updateRelatedArticleAdapter() {
        if(mRelatedArticleViewer != null){
            mRelatedArticleViewer.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void updateRelatedProductAdapter() {
        if(mRelatedProductViewer != null){
            mRelatedProductViewer.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void loadFragment(String fragmentName, Bundle bundle) {
        IHomeView.getHomePresenter().loadNonFooterFragment(fragmentName, bundle);
    }

    @Override
    public void updateProductSaved(ProductPostItem postItem) {
        IHomeView.updateProductSavedData(postItem);
    }

    @Override
    public void updateArticleSaved(ArticlePostItem articlePostItem) {
        IHomeView.updateArticleSavedData(articlePostItem);
    }

    public void updateLikeIcon(boolean isLike) {
        if(!isLike){
            mLikeImage.setColorFilter(null);
            mLikeImage.setImageResource(R.mipmap.ic_full_article_heart);
        }
        else {
            mLikeImage.setColorFilter(ContextCompat.getColor(getContext(), R.color.hh_green_light2), PorterDuff.Mode.SRC_IN);
        }
    }

    @OnClick(R.id.comments_view_all)
    void onViewAll(){
        Log.i("TAGCOMMENT" , " isShown " + mCommentView.isShown());

        if(mCommentView.isShown()){
            mCommentViewAll.setText(R.string.view_all);
            mCommentArrow.setImageResource(R.mipmap.ic_chevron_down);
            mCommentView.setVisibility(View.GONE);
            mCommentViewer.setVisibility(View.GONE);
        }
        else {
            mCommentViewAll.setText(R.string.close_all);
            mCommentArrow.setImageResource(R.mipmap.ic_chevron_up);
            mCommentView.setVisibility(View.VISIBLE);
            mCommentViewer.setVisibility(View.VISIBLE);

            CommentAdapter adapter = (CommentAdapter) mCommentViewer.getAdapter();
            if(adapter == null) {
                ArticlePostItem post = IFullPresenter.getArticle();
                IFullPresenter.fetchComments(String.valueOf(post.getArticle_Id()));
                mFullViewScroll.fullScroll(View.FOCUS_DOWN);
            }
        }
    }

    @OnClick(R.id.full_article_bookmark)
    void onBookMark(){
        CurrentUser currentUser = null;
        ArticlePostItem articlePost = IFullPresenter.getArticle();
        if(articlePost != null){
            currentUser = articlePost.getCurrent_user();
            if(currentUser != null ) {
                if(!currentUser.isBookmarked()) {
                    IFullPresenter.bookmark(String.valueOf(articlePost.getArticle_Id()), ArticleParams.ARTICLE);
                }
                else {
                    IFullPresenter.unBookmark(String.valueOf(articlePost.getArticle_Id()), ArticleParams.ARTICLE);
                }
            }
        }
    }

    @OnClick(R.id.send_comment)
    void onSend(View view){
        String content = mCommentContent.getText().toString().trim();

        try {
            byte[] data = content.getBytes("UTF-8");
            content = Base64.encodeToString(data, Base64.DEFAULT);
        }
        catch (UnsupportedEncodingException unsupportedEncodingException){

        }

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        ArticlePostItem articlePost = IFullPresenter.getArticle();
        if(articlePost != null){
            String post_id = articlePost.getArticle_Id();
            if(!content.isEmpty()){
                IFullPresenter.addNewComment(String.valueOf(post_id), content);
            }
        }
    }

    @OnClick(R.id.full_article_like)
    void onLikeClick(){
        String id = IFullPresenter.getArticle().getArticle_Id();
        IFullPresenter.updateLike(String.valueOf(id));
    }

    private void setCommentContent(ArticlePostItem articlePost) {
        String commentCount = articlePost.getComments();
        if(commentCount != null){
            int count = Integer.parseInt(commentCount);
            if(count > 0){
                mCommentCount.setText(commentCount);
            }
            else {
                mCommentCount.setText("");
            }
        }
    }

    private void setAboutContent(ArticlePostItem articlePost) {
    }

    private void setArticleContent(ArticlePostItem articlePost) {

        Title title = articlePost.getTitle();
        String articleTitle = null;
        if (title != null) {
            articleTitle = title.getRendered();
        }
        mTitle.setText(articleTitle);

        Content content = articlePost.getContent();
        Log.i("TAGCONTENT", "Content " + content.getRendered());
        if (content != null) {
            URLImageParser imageGetter = new URLImageParser(getContext(), mContent);
            Spannable html;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                html = (Spannable) Html.fromHtml(content.getRendered(), Html.FROM_HTML_MODE_LEGACY, imageGetter, null);
            } else {
                html = (Spannable) Html.fromHtml(content.getRendered(), imageGetter, null);
            }
            mContent.setText(html);
        }

        String tagsName = "";
        List<TagsItem> tagItems = articlePost.getTags();
        if (tagItems != null && !tagItems.isEmpty()) {
            for (TagsItem tagItem : tagItems) {
                String tag = tagItem.getName();
                tagsName = "#" + tag + " ";
            }
        }
        mHashTags.setText(tagsName);

        String date = articlePost.getDate();
        date = HealthHuntUtility.getDateWithFormat(date);
        if (date != null) {
            date.trim();
            mPublishDate.setText(date);
        }


        Author author = articlePost.getAuthor();
        if (author != null) {
            mAuthorName.setText(author.getName());
            String authorUrl = author.getUrl();
            if (authorUrl != null) {
                authorUrl = authorUrl.replace("\n", "");
                Glide.with(this)
                        .load(authorUrl).placeholder(R.mipmap.default_profile)
                        .bitmapTransform(new CropCircleTransformation(getContext()))
                        .into(mAuthorImage);
            } else {
                mAuthorImage.setBackgroundResource(R.mipmap.default_profile);
            }
        }
    }

    private void setTopImageContent(ArticlePostItem articlePost) {

        String readTime = articlePost.getRead_time();
        if (readTime != null) {
            readTime = getString(R.string.read_time, readTime);
        }

        mReadTime.setText(readTime);

        CurrentUser currentUser = articlePost.getCurrent_user();
        if(currentUser != null) {
            updateBookMark(currentUser.isBookmarked());

            int like = currentUser.getLike();
            if(like > 0){
                updateLikeIcon(true);
            }
            else {
                updateLikeIcon(false);
            }
        }


        Likes likes = articlePost.getLikes();
        if(likes != null){
            String likeCount = likes.getLikes();
            if(likeCount != null){
                mLikesCount.setText(likeCount);
            }
        }


        String categoryName = null;
        List<CategoriesItem> categories = articlePost.getCategories();
        if (categories != null && !categories.isEmpty()) {
            categoryName = categories.get(0).getName();
            mCategoryName.setText(categoryName);
            mCategoryImage.setColorFilter(ContextCompat.getColor(getContext(), R.color.hh_blue_light), PorterDuff.Mode.SRC_IN);
            int res = HealthHuntUtility.getCategoryIcon(categoryName);
            if(res != 0){
                mCategoryImage.setImageResource(res);
            }
            else {
                mCategoryImage.setImageResource(R.mipmap.ic_fitness);
            }
        }

        String url = null;
        List<MediaItem> mediaItems = articlePost.getMedia();
        if (mediaItems != null && !mediaItems.isEmpty()) {
            MediaItem media = mediaItems.get(0);
            Log.i("TAGMedia", "media " + media);
            if ("image".equals(media.getMedia_type())) {
                url = media.getUrl();
                Log.i("TAGMedia", "URL " + url);

            }
        }

        if (url != null) {
            Glide.with(this).load(url).placeholder(R.drawable.artical).into(mArticleImage);
        } else {
            mArticleImage.setBackgroundResource(R.drawable.artical);
        }
    }

    @OnClick(R.id.full_article_download)
    void onDownloadClick(){
        final Dialog mBottomSheetDialog = new Dialog(getContext(), R.style.MaterialDialogSheet);
        View customView = getLayoutInflater().inflate(R.layout.download_popup_window_view,null);
        mBottomSheetDialog.setContentView(customView); // your custom view.
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                getResources().getDimensionPixelOffset(R.dimen.popup_window_height));
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);


        LinearLayout shareView = customView.findViewById(R.id.share_view);
        shareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
                //Toast.makeText(getContext(), "Share", Toast.LENGTH_SHORT).show();
                shareArticle();
            }
        });

        LinearLayout downloadView = customView.findViewById(R.id.download_article_view);
        downloadView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
                storeArticle();
            }
        });

        mBottomSheetDialog.show();
    }

    private void shareArticle(){
        ArticlePostItem articlePost = IFullPresenter.getArticle();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, articlePost.getLink());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getString(R.string.share)));
    }

    private void updateBookMark(boolean isBookMark) {
        Log.i("TAGBOOKMARK", "ISBOOK " + isBookMark);
        if(!isBookMark){
            mBookMark.setColorFilter(null);
            mBookMark.setImageResource(R.mipmap.ic_bookmark_full_view);
        }
        else {
            mBookMark.setColorFilter(ContextCompat.getColor(getContext(), R.color.hh_green_light2), PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    public void onMore(View view, final int position) {
        Log.i("TAGPOPUP", "onMore");

        PopupMenu popup = new PopupMenu(getContext(), view);
        popup.setGravity(Gravity.LEFT);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                CommentsItem commentsItem = IFullPresenter.getComment(position);
                int id = item.getItemId();
                switch (id){
                    case R.id.comment_edit:
                        editComment(position);
                        break;
                    case R.id.comment_delete:
                        IFullPresenter.deleteComment(String.valueOf(commentsItem.getId()));
                        break;
                }
                return true;
            }
        });
        popup.inflate(R.menu.comment_menu);
        popup.show();
    }

    @Override
    public void update(View view, int position) {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


        CommentViewHolder holder = (CommentViewHolder) mCommentViewer.getChildViewHolder(mCommentViewer.getChildAt(position));
        CommentsItem commentsItem = IFullPresenter.getComment(position);
        int id = commentsItem.getId();
        String content = holder.mCommentEditText.getText().toString();

        try {
            byte[] data = content.getBytes("UTF-8");
            content = Base64.encodeToString(data, Base64.DEFAULT);
        }
        catch (UnsupportedEncodingException unsupportedEncodingException){

        }
        IFullPresenter.updateComment(String.valueOf(id), content);
    }

    private void editComment(int position) {
        // CommentsItem commentsItem = IFullPresenter.getComment(position);
        CommentViewHolder holder = (CommentViewHolder) mCommentViewer.getChildViewHolder(mCommentViewer.getChildAt(position));
        holder.mCommentText.setVisibility(View.GONE);
        holder.mCommentEditText.setVisibility(View.VISIBLE);
        holder.mCommentUpdate.setVisibility(View.VISIBLE);
        // Spannable html = (Spannable) Html.fromHtml(commentsItem.getContent().getRendered());
        holder.mCommentEditText.setText(holder.mCommentText.getText().toString().trim());
        holder.mCommentEditText.requestFocus();
    }

    @Override
    public void onRelatedItemClicked(View v, int position, int type) {
        String id = null;
        String tag = FullArticleFragment.class.getSimpleName();
        if(type == ArticleParams.ARTICLE){
            ArticlePostItem postItem = IFullPresenter.getRelatedArticle(position);
            id = String.valueOf(postItem.getArticle_Id());
            tag = FullArticleFragment.class.getSimpleName();

        }
        else if(type == ArticleParams.PRODUCT){
            ProductPostItem postItem = IFullPresenter.getRelatedProduct(position);
            id = String.valueOf(postItem.getProduct_id());
            tag = FullProductFragment.class.getSimpleName();
        }
        Bundle bundle = new Bundle();
        bundle.putString(ArticleParams.ID, String.valueOf(id));
        bundle.putInt(ArticleParams.POST_TYPE, type);
        IFullPresenter.loadFragment(tag, bundle);
    }

    private void openViewAllFragment() {
          }

    @OnClick(R.id.related_view_all)
    void onRelatedViewAll(){
        Bundle bundle = new Bundle();
        String id = IFullPresenter.getArticle().getArticle_Id();
        bundle.putInt(ArticleParams.ARTICLE_TYPE, ArticleParams.RELATED_ARTICLES);
        bundle.putString(ArticleParams.ID, String.valueOf(id));
        IFullPresenter.loadFragment(ViewAllFragment.class.getSimpleName(), bundle);
    }

    @OnClick(R.id.related_product_view_all)
    void onRelatedProductViewAll(){
        Bundle bundle = new Bundle();
        String id = IFullPresenter.getArticle().getArticle_Id();
        bundle.putInt(ArticleParams.ARTICLE_TYPE, ArticleParams.RELATED_PRODUCTS);
        bundle.putString(ArticleParams.ID, String.valueOf(id));
        IFullPresenter.loadFragment(ViewAllFragment.class.getSimpleName(), bundle);
    }

    private void storeArticle(){
        ArticlePostItem articlePostItem = IFullPresenter.getArticle();

        Log.i("TAGPOSTSINGLE", "Article " + articlePostItem);
        Title title = articlePostItem.getTitle();
        if(title != null){
            title.save();
        }

        Content content = articlePostItem.getContent();
        if(content != null){
            content.save();
        }

        List<MediaItem> mediaItems = articlePostItem.getMedia();
        if(mediaItems != null){

            for(MediaItem item: mediaItems){
                item.setParent_id(articlePostItem.getArticle_Id());
                item.save();
            }
        }

        List<CategoriesItem> categoriesItemList = articlePostItem.getCategories();
        if(categoriesItemList != null){
            for (CategoriesItem item : categoriesItemList){
                item.setParent_id(articlePostItem.getArticle_Id());
                item.save();
            }
        }

        Likes likes = articlePostItem.getLikes();
        if(likes != null){
            likes.save();
        }

        CurrentUser currentUser = articlePostItem.getCurrent_user();
        if(currentUser != null){
            /*List<Collections> collections = currentUser.getCollections();
            if(collections != null){
                for(Collections collect: collections) {
                    collect.setParent_id(productPost.getProduct_id());
                    collect.save();
                }
            }*/
            currentUser.save();
        }

        Author author = articlePostItem.getAuthor();
        if(author != null){
            author.save();
        }

        Synopsis synopsis = articlePostItem.getSynopsis();
        if(synopsis != null){
            synopsis.save();
        }

        List<TagsItem> tagsItems = articlePostItem.getTags();
        if(tagsItems != null){
            for(TagsItem tagsItem: tagsItems){
                tagsItem.setParent_id(articlePostItem.getArticle_Id());
                tagsItem.save();
            }
        }

        Excerpt excerpt = articlePostItem.getExcerpt();
        if(excerpt != null){
            excerpt.save();
        }

        articlePostItem.save();
        IHomeView.updateDownloadData();
    }

    private class FetchArticlesTask extends AsyncTask<Void, Void, ArticlePostItem> {

        private String mArticleID;
        public FetchArticlesTask(String id){
            mArticleID = id;
        }

        @Override
        protected ArticlePostItem doInBackground(Void... voids) {

            ArticlePostItem articlePostItem = new Select().from(ArticlePostItem.class).
                    where("article_id = ?" , mArticleID).executeSingle();

            if(articlePostItem == null){
                Log.i("TAGPOS", "Article Post is null");
                return null;
            }

            Log.i("TAGDATA", "articlePost " + articlePostItem);

            /*List<MediaItem> mediaItem = new Select().from(MediaItem.class).
                    where("parent_id = ?" , productPost.getProduct_id()).execute();
            productPost.setMedia(mediaItem);

            List<CategoriesItem> categoriesItems = new Select().from(CategoriesItem.class).
                    where("parent_id = ?" , productPost.getProduct_id()).execute();

            productPost.setCategories(categoriesItems);

            List<TagsItem> tagsItems = new Select().from(TagsItem.class).
                    where("parent_id = ?" , productPost.getProduct_id()).execute();
            productPost.setTags(tagsItems);

            CurrentUser currentUser = productPost.getCurrent_user();
            if(currentUser != null) {
                List<Collections> collections = new Select().from(Collections.class).
                        where("parent_id = ?", productPost.getProduct_id()).execute();
                currentUser.setCollections(collections);
            }*/
            return articlePostItem;
        }

        @Override
        protected void onPostExecute(ArticlePostItem articlePostItem) {
            super.onPostExecute(articlePostItem);
            IHomeView.hideProgress();
            IFullPresenter.updateArticle(articlePostItem);
        }
    }
}
