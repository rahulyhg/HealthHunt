package in.healthhunt.model.deletePost;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;

public class DeleteArticleData {
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
			",posts = '" + post + '\'' + "}";
		}
}