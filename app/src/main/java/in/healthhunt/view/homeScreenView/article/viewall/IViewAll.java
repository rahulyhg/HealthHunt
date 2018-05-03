package in.healthhunt.view.homeScreenView.article.viewall;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public interface IViewAll {
    int getCount();
    RecyclerView.ViewHolder onCreateViewHolder(View view);
}
