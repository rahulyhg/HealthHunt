package in.healthhunt.view.fullView.commentView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import in.healthhunt.R;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.model.comment.Author;
import in.healthhunt.model.comment.CommentsItem;
import in.healthhunt.model.comment.Content;
import in.healthhunt.model.preference.HealthHuntPreference;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.fullPresenter.IFullPresenter;
import in.healthhunt.view.fullView.URLImageParser;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private IFullPresenter IFullPresenter;
    private Context mContext;
    private ClickListener mClickListener;

    public CommentAdapter(Context context, IFullPresenter fullPresenter) {
        mContext = context;
        IFullPresenter = fullPresenter;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_item_view, parent, false);
        return IFullPresenter.createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        setContent(holder, position);
    }

    private void setContent(CommentViewHolder holder, int position) {
        CommentsItem commentsItem = IFullPresenter.getComment(position);

        if(commentsItem != null) {
            Author author = commentsItem.getAuthor();
            if(author != null){
                String name = author.getName();
                holder.mUserName.setText(name);

                String user_id = HealthHuntPreference.getString(mContext, Constants.USER_ID);
                Log.i("TAGUSERU", "User_ID " + user_id + " Author ID " + author.getId() );
                if(user_id.equals(String.valueOf(author.getId()))){
                    holder.mCommentEdit.setVisibility(View.VISIBLE);
                }
                else{
                    holder.mCommentEdit.setVisibility(View.GONE);
                }

                String url = author.getUrl();
                Glide.with(mContext)
                        .load(url)
                        .bitmapTransform(new CropCircleTransformation(mContext)).placeholder(R.mipmap.avatar)
                        .into(holder.mUserPic);

            }

            String date = commentsItem.getDate();
            if(date != null) {
                date = HealthHuntUtility.getDateWithFormat(date);
            }
            holder.mCommentDate.setText(date);

            Content content = commentsItem.getContent();
            if(content != null) {
                URLImageParser imageGetter = new URLImageParser(mContext, holder.mCommentText);
                Spannable html;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    html = (Spannable) Html.fromHtml(content.getRendered(), Html.FROM_HTML_MODE_LEGACY, imageGetter, null);
                } else {
                    html = (Spannable) Html.fromHtml(content.getRendered(), imageGetter, null);
                }
                holder.mCommentText.setText(html.toString().trim());
                holder.mCommentText.setVisibility(View.VISIBLE);
                holder.mCommentEditText.setVisibility(View.GONE);
                holder.mCommentUpdate.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public int getItemCount() {
        return IFullPresenter.getCommentCount();
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public interface ClickListener {
        void onMore(View v, int position);
        void update(View v, int position);
    }
}