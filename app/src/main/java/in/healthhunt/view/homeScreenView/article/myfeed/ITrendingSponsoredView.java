package in.healthhunt.view.homeScreenView.article.myfeed;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public interface ITrendingSponsoredView {
    int getArticleCount();
    ArticlePostItem getTrendingArticle(int pos);
    ArticlePostItem getSponsoredArticle(int pos);
}
