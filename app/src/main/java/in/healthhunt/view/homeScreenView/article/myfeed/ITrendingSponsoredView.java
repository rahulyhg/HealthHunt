package in.healthhunt.view.homeScreenView.article.myfeed;

import in.healthhunt.model.articles.articleResponse.PostsItem;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public interface ITrendingSponsoredView {
    int getArticleCount();
    PostsItem getTrendingArticle(int pos);
    PostsItem getSponsoredArticle(int pos);
}
