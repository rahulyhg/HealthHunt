package in.healthhunt.view.fullView;

import android.view.View;

import in.healthhunt.view.fullView.commentView.CommentViewHolder;

/**
 * Created by abhishekkumar on 5/16/18.
 */

public interface IFullView {
    void showProgress();
    void hideProgress();
    void setContent();
    int getPostType();
    void updateBookMarkIcon();
    CommentViewHolder createViewHolder(View view);
    void updateCommentAdapter();
}
