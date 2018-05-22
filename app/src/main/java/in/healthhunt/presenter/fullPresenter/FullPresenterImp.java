package in.healthhunt.presenter.fullPresenter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.bookmarkResponse.BookMarkData;
import in.healthhunt.model.articles.bookmarkResponse.BookMarkInfo;
import in.healthhunt.model.articles.commonResponse.CurrentUser;
import in.healthhunt.model.articles.postResponse.ArticlePost;
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.model.comment.CommentRequest;
import in.healthhunt.model.comment.CommentsItem;
import in.healthhunt.model.comment.NewComment;
import in.healthhunt.model.likes.LikesInfo;
import in.healthhunt.presenter.interactor.articleInteractor.ArticleInteractorImpl;
import in.healthhunt.presenter.interactor.articleInteractor.IArticleInteractor;
import in.healthhunt.presenter.interactor.bookMarkInteractor.BookMarkInteractorImpl;
import in.healthhunt.presenter.interactor.bookMarkInteractor.IBookMarkInteractor;
import in.healthhunt.presenter.interactor.commentInteractor.CommentInteractorImpl;
import in.healthhunt.presenter.interactor.commentInteractor.ICommentInteractor;
import in.healthhunt.presenter.interactor.likesInteractor.ILikesInteractor;
import in.healthhunt.presenter.interactor.likesInteractor.LikesInteractorImpl;
import in.healthhunt.presenter.interactor.productInteractor.IProductInteractor;
import in.healthhunt.presenter.interactor.productInteractor.ProductInteractorImpl;
import in.healthhunt.view.fullView.IFullView;
import in.healthhunt.view.fullView.commentView.CommentViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class FullPresenterImp implements IFullPresenter, IArticleInteractor.OnFullViewFinishListener,
        IProductInteractor.OnFullViewFinishListener,  IBookMarkInteractor.OnFinishListener ,
        ILikesInteractor.OnFinishListener, ICommentInteractor.OnFinishListener {

    private String TAG = FullPresenterImp.class.getSimpleName();
    private IFullView IFullView;
    private Context mContext;
    private ArticlePost mArticlePost;
   // private IFullInteractor IFullInteractor;
    private IBookMarkInteractor IBookMarkInteractor;
    private ILikesInteractor ILikesInteractor;
    private ICommentInteractor ICommentInteractor;
    private IArticleInteractor IArticleInteractor;
    private IProductInteractor IProductInteractor;
    private int mOffset = 0;
    private int mLimit = 10;
    private List<CommentsItem> mCommentsItems;

    public FullPresenterImp(Context context, IFullView fullView) {
        mContext =  context;
        IFullView = fullView;
        //IFullInteractor = new FullInteractorImpl();
        IBookMarkInteractor = new BookMarkInteractorImpl();
        ILikesInteractor = new LikesInteractorImpl();
        ICommentInteractor = new CommentInteractorImpl();
        IArticleInteractor = new ArticleInteractorImpl();
        IProductInteractor = new ProductInteractorImpl();
    }

    @Override
    public void loadFragment(String tag, Bundle bundle) {

    }

    @Override
    public void fetchArticle(String id) {
        IFullView.showProgress();
        IArticleInteractor.fetchFullArticle(mContext, id, this);
    }

    @Override
    public ArticlePost getArticle() {
        return mArticlePost;
    }

    @Override
    public ProductPostItem getProduct() {
        return null;
    }

    @Override
    public void bookmark(String id) {
        IFullView.showProgress();
        IBookMarkInteractor.bookmark(mContext, id, IFullView.getPostType(), this);
    }

    @Override
    public void unBookmark(String id) {
        IFullView.showProgress();
        IBookMarkInteractor.unBookmark(mContext, id, IFullView.getPostType(), this);
    }

    @Override
    public CommentViewHolder createViewHolder(View view) {
        return IFullView.createViewHolder(view);
    }

    @Override
    public int getCommentCount() {
        int size  = 0;
        if(mCommentsItems != null){
            size = mCommentsItems.size();
        }
        return size;
    }

    @Override
    public void fetchComments(String id) {
        IFullView.showProgress();
        mOffset = 0;
        mLimit = 10;
        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.POST_ID, id);
        map.put(ArticleParams.OFFSET, String.valueOf(mOffset));
        map.put(ArticleParams.LIMIT, String.valueOf(mLimit));
        ICommentInteractor.fetchComments(mContext, map, this);
    }

    @Override
    public List<CommentsItem> getComments() {
        return mCommentsItems;
    }

    @Override
    public CommentsItem getComment(int pos) {
        CommentsItem commentsItem = mCommentsItems.get(pos);
        return commentsItem;
    }

    @Override
    public void deleteComment(String id) {
        IFullView.showProgress();
        ICommentInteractor.deleteComment(mContext, id, this);
    }

    @Override
    public void addNewComment(String post_id, String content) {
        IFullView.showProgress();
        CommentRequest request = new CommentRequest();
        request.setPost_id(post_id);
        request.setContent(content);
        Log.i("TAGPOSTID", " ID " + post_id + " content " + content);
        ICommentInteractor.addNewComment(mContext, request, this);
    }

    @Override
    public void onArticleSuccess(ArticlePost item) {
        Log.i("TAGITEM", "ITEM " + item);
        mArticlePost = item;
        IFullView.hideProgress();
        IFullView.setContent();
    }

    @Override
    public void onProductSuccess(ProductPostItem item) {
        IFullView.hideProgress();
    }

    @Override
    public void onBookMarkSuccess(BookMarkData markResponse) {
        IFullView.hideProgress();
        BookMarkInfo bookMarkInfo = markResponse.getBookMarkInfo();

        if(bookMarkInfo == null){
            Log.i("TAG", " Book Mark info is null");
            return;
        }

        int type = bookMarkInfo.getType();

        switch (type){
            case ArticleParams.ARTICLE:
                if(bookMarkInfo.getPost_id().equals(String.valueOf(mArticlePost.getId()))) {
                    CurrentUser currentUser = mArticlePost.getCurrent_user();
                    if (currentUser != null) {
                        currentUser.setBookmarked(bookMarkInfo.isBookMark());
                    }
                }
                break;

            case ArticleParams.PRODUCT:
                break;
        }

        IFullView.updateBookMarkIcon();
    }

    @Override
    public void onError(RestError errorInfo) {
        IFullView.hideProgress();
        if(errorInfo != null) {
            Toast.makeText(mContext, errorInfo.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLikesSuccess(LikesInfo likesInfo) {

    }

    @Override
    public void onCommentSuccess(List<CommentsItem> item) {
        IFullView.hideProgress();
        mCommentsItems = item;
        mOffset = mLimit;
        mLimit+=10;
        IFullView.updateCommentAdapter();
    }

    @Override
    public void onCommentDeleteSuccess(String id) {
        for(CommentsItem item: mCommentsItems){
            if(id.equals(String.valueOf(item.getId()))){
                mCommentsItems.remove(item);
                String comments = mArticlePost.getComments();
                int com = Integer.parseInt(comments);
                com--;
                mArticlePost.setComments(String.valueOf(com));
                break;
            }
        }

        IFullView.hideProgress();
        IFullView.updateCommentAdapter();
    }

    @Override
    public void onNewCommentSuccess(NewComment newComment) {
        int post_comment = newComment.getPost_comment_count();
        mArticlePost.setComments(String.valueOf(post_comment));
        mCommentsItems.add(newComment.getComment());
        IFullView.hideProgress();
        IFullView.updateCommentAdapter();
    }
}
