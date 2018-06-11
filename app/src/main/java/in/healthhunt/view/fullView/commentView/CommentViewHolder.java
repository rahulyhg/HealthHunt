package in.healthhunt.view.fullView.commentView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.presenter.fullPresenter.IFullPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class CommentViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.profile_pic)
    public ImageView mUserPic;

    @BindView(R.id.user_name)
    public TextView mUserName;

    @BindView(R.id.commented_date)
    public TextView mCommentDate;

    @BindView(R.id.comment_edit)
    public ImageView mCommentEdit;

    @BindView(R.id.comment_text)
    public TextView mCommentText;

    @BindView(R.id.edit_comment_text)
    public EditText mCommentEditText;

    @BindView(R.id.update_comment)
    public Button mCommentUpdate;

    private Context mContext;
    private IFullPresenter IFullPresenter;
    private CommentAdapter.ClickListener mClickListener;
    public CommentViewHolder(View itemView, IFullPresenter fullPresenter, CommentAdapter.ClickListener clickListener) {
        super(itemView);
        IFullPresenter = fullPresenter;
        mClickListener = clickListener;
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.comment_edit)
    void onMoreClick(View view){
        mClickListener.onMore(view, getAdapterPosition());
    }

    @OnClick(R.id.update_comment)
    void onUpdate(View view){
        mClickListener.update(view, getAdapterPosition());
    }
}