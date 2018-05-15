package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface ITrendingSponsoredPresenter {
    int getCount();
    ArticlePostItem getTrendingArticles(int pos);
    ArticlePostItem getSponsoredArticles(int pos);

    //RecyclerView.ViewHolder createArticleHolder(View view);
}
