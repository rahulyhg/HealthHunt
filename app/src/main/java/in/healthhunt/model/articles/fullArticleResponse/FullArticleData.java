package in.healthhunt.model.articles.fullArticleResponse;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;

public class FullArticleData {
	private ArticlePostItem post;

	public void setPost(ArticlePostItem post){
		this.post = post;
	}

	public ArticlePostItem getPost(){
		return post;
	}

	@Override
 	public String toString(){
		return 
			"BookMarkData{" +
			"post = '" + post + '\'' + 
			"}";
		}
}
