package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

import in.healthhunt.model.articles.articleResponse.PostsItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface ITrendingSponsoredPresenter {
    int getCount();
    PostsItem getTrendingArticles(int pos);
    PostsItem getSponsoredArticles(int pos);

    //RecyclerView.ViewHolder createArticleHolder(View view);
}
