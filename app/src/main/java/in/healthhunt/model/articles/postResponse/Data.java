package in.healthhunt.model.articles.postResponse;

public class Data{
	private ArticlePost post;

	public void setPost(ArticlePost post){
		this.post = post;
	}

	public ArticlePost getPost(){
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
