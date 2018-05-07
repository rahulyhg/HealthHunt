package in.healthhunt.view.homeScreenView.article.viewall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ViewAllHolder extends RecyclerView.ViewHolder {

    private Context mContext;

    public ViewAllHolder(View articleView) {
        super(articleView);
        mContext = articleView.getContext();
    }
}