package in.healthhunt.view.homeScreenView.article.myfeed;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public interface ITopProductView {
    int getCount();
    RecyclerView.ViewHolder createArticleHolder(View view);
}
