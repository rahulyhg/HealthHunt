package in.healthhunt.model.articles.postProductResponse;

public class FullProductData {
	private ProductPost post;

	public void setPost(ProductPost post){
		this.post = post;
	}

	public ProductPost getPost(){
		return post;
	}

	@Override
 	public String toString(){
		return 
			"FullProductData{" +
			"post = '" + post + '\'' + 
			"}";
		}
}
