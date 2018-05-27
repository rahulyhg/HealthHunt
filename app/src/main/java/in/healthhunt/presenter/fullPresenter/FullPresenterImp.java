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
import in.healthhunt.model.articles.postProductResponse.ProductPost;
import in.healthhunt.model.articles.postResponse.ArticlePost;
import in.healthhunt.model.comment.CommentData;
import in.healthhunt.model.comment.CommentRequest;
import in.healthhunt.model.comment.CommentsItem;
import in.healthhunt.model.likes.LikesInfo;
import in.healthhunt.model.likes.LikesRequest;
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
import in.healthhunt.view.fullView.commentView.CommentViewHolder;
import in.healthhunt.view.fullView.fullViewFragments.IFullFragment;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class FullPresenterImp implements IFullPresenter, IArticleInteractor.OnFullViewFinishListener,
        IProductInteractor.OnFullViewFinishListener,  IBookMarkInteractor.OnFinishListener ,
        ILikesInteractor.OnFinishListener, ICommentInteractor.OnFinishListener {

    private String TAG = FullPresenterImp.class.getSimpleName();
    private IFullFragment IFullFragment;
    private Context mContext;
    private ArticlePost mArticlePost;
    private ProductPost mProductPost;
   // private IFullInteractor IFullInteractor;
    private IBookMarkInteractor IBookMarkInteractor;
    private ILikesInteractor ILikesInteractor;
    private ICommentInteractor ICommentInteractor;
    private IArticleInteractor IArticleInteractor;
    private IProductInteractor IProductInteractor;
    private int mOffset = 0;
    private int mLimit = 10;
    private List<CommentsItem> mCommentsItems;

    public FullPresenterImp(Context context, IFullFragment fullView) {
        mContext =  context;
        IFullFragment = fullView;
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
        IFullFragment.showProgress();
        IArticleInteractor.fetchFullArticle(mContext, id, this);
    }

    @Override
    public void fetchProduct(String id) {
        IFullFragment.showProgress();
        IProductInteractor.fetchFullProduct(mContext, id, this);
    }

    @Override
    public ArticlePost getArticle() {
        return mArticlePost;
    }

    @Override
    public ProductPost getProduct() {
        return mProductPost;
    }

    @Override
    public void bookmark(String id) {
        IFullFragment.showProgress();
        IBookMarkInteractor.bookmark(mContext, id, IFullFragment.getPostType(), this);
    }

    @Override
    public void unBookmark(String id) {
        IFullFragment.showProgress();
        IBookMarkInteractor.unBookmark(mContext, id, IFullFragment.getPostType(), this);
    }

    @Override
    public CommentViewHolder createViewHolder(View view) {
        return IFullFragment.createViewHolder(view);
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
        IFullFragment.showProgress();
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
        IFullFragment.showProgress();
        ICommentInteractor.deleteComment(mContext, id, this);
    }

    @Override
    public void addNewComment(String post_id, String content) {
        IFullFragment.showProgress();
        CommentRequest request = new CommentRequest();
        request.setPost_id(post_id);
        request.setContent(content);
        Log.i("TAGPOSTID", " ID " + post_id + " content " + content);
        ICommentInteractor.addNewComment(mContext, request, this);
    }

    @Override
    public void updateComment(String id, String content) {
        IFullFragment.showProgress();
        CommentRequest request = new CommentRequest();
        request.setContent(content);
        ICommentInteractor.updateComment(mContext, id, request,this);
    }

    @Override
    public void updateLike(String id) {
        IFullFragment.showProgress();
        LikesRequest likesRequest = new LikesRequest();
        likesRequest.setLike_type(String.valueOf(2));
        ILikesInteractor.updateLikes(mContext, id, likesRequest, this);
    }

    @Override
    public void onArticleSuccess(ArticlePost item) {
        Log.i("TAGITEM", "ITEM " + item);
        mArticlePost = item;
        IFullFragment.hideProgress();
        IFullFragment.setContent();
    }

    @Override
    public void onProductSuccess(ProductPost item) {
        mProductPost = item;
        IFullFragment.hideProgress();
        IFullFragment.setContent();
    }

    @Override
    public void onBookMarkSuccess(BookMarkData markResponse) {
        IFullFragment.hideProgress();
        BookMarkInfo bookMarkInfo = markResponse.getBookMarkInfo();

        if(bookMarkInfo == null){
            Log.i("TAG", " Book Mark info is null");
            return;
        }

        int type = bookMarkInfo.getType();
        CurrentUser currentUser = null;
        switch (type){
            case ArticleParams.ARTICLE:
            case ArticleParams.VIDEO:
                if(bookMarkInfo.getPost_id().equals(String.valueOf(mArticlePost.getId()))) {
                    currentUser = mArticlePost.getCurrent_user();
                }
                break;

            case ArticleParams.PRODUCT:
                if(bookMarkInfo.getPost_id().equals(String.valueOf(mProductPost.getId()))) {
                    currentUser = mProductPost.getCurrent_user();
                }
                break;
        }

        if (currentUser != null) {
            currentUser.setBookmarked(bookMarkInfo.isBookMark());
        }

        IFullFragment.updateBookMarkIcon();
    }

    @Override
    public void onError(RestError errorInfo) {
        IFullFragment.hideProgress();
        if(errorInfo != null) {
            Toast.makeText(mContext, errorInfo.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLikesSuccess(LikesInfo likesInfo) {


        CurrentUser currentUser = null;

        switch (IFullFragment.getPostType()){
            case ArticleParams.ARTICLE:
            case ArticleParams.VIDEO:
                currentUser = mArticlePost.getCurrent_user();
                break;

            case ArticleParams.PRODUCT:
                currentUser = mProductPost.getCurrent_user();
                break;
        }

        if(currentUser != null) {
            int likes = currentUser.getLike();
            if(likes == 0){
                likes = 1;
            }
            else {
                likes = 0;
            }
            currentUser.setLike(likes);
        }

        IFullFragment.hideProgress();
        IFullFragment.updateLike();
    }

    @Override
    public void onCommentSuccess(List<CommentsItem> item) {
        IFullFragment.hideProgress();
        mCommentsItems = item;
        mOffset = mLimit;
        mLimit+=10;
        IFullFragment.updateCommentAdapter();
    }

    @Override
    public void onCommentDeleteSuccess(String id) {
        for(CommentsItem item: mCommentsItems){
            if(id.equals(String.valueOf(item.getId()))){
                mCommentsItems.remove(item);

                String comments = null;
                switch (IFullFragment.getPostType()){
                    case ArticleParams.ARTICLE:
                        comments = mArticlePost.getComments();;
                        int com = Integer.parseInt(comments);
                        com--;
                        mArticlePost.setComments(String.valueOf(com));

                        break;

                    case ArticleParams.PRODUCT:
                        comments = mProductPost.getComments();
                        int proCom = Integer.parseInt(comments);
                        proCom--;
                        mProductPost.setComments(String.valueOf(proCom));

                        break;
                }
                break;
            }
        }

        IFullFragment.hideProgress();
        IFullFragment.updateCommentAdapter();
    }

    @Override
    public void onNewCommentSuccess(CommentData newComment) {
        int post_comment = newComment.getPost_comment_count();

        switch (IFullFragment.getPostType()){
            case ArticleParams.ARTICLE:
                mArticlePost.setComments(String.valueOf(post_comment));
                break;

            case ArticleParams.PRODUCT:
                mProductPost.setComments(String.valueOf(post_comment));
                break;

        }

        mCommentsItems.add(newComment.getComment());
        IFullFragment.hideProgress();
        IFullFragment.updateCommentAdapter();
    }

    @Override
    public void onUpdateSuccess(CommentsItem commentsItem) {
       // int pos = 0;
        for(int i=0; i<mCommentsItems.size(); i++){
            CommentsItem item = mCommentsItems.get(i);
            if(item.getId() == commentsItem.getId()){
                //pos = i;
                mCommentsItems.set(i, commentsItem);
                break;
            }
        }

        /*Log.i("TAGDATA", " Content " + commentsItem.getContent().getRendered());
        CommentsItem item = mCommentsItems.get(pos);
        item.getContent().setRendered(commentsItem.getContent().getRendered());
        mCommentsItems.set(pos, item);
*/
      //  Log.i("TAGPOS " , "POS " + pos);
        IFullFragment.hideProgress();
        IFullFragment.updateCommentAdapter();
    }
}
