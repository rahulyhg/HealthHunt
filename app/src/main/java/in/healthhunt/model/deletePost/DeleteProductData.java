package in.healthhunt.model.deletePost;

import in.healthhunt.model.articles.productResponse.ProductPostItem;

public class DeleteProductData {
	private ProductPostItem post;

	public void setPost(ProductPostItem post){
		this.post = post;
	}

	public ProductPostItem getPost(){
		return post;
	}

	@Override
 	public String toString(){
		return 
			"BookMarkData{" +
			",posts = '" + post + '\'' + "}";
		}
}