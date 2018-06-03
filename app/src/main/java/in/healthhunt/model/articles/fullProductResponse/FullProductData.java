package in.healthhunt.model.articles.fullProductResponse;

import in.healthhunt.model.articles.productResponse.ProductPostItem;

public class FullProductData {
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
			"FullProductData{" +
			"post = '" + post + '\'' + 
			"}";
		}
}
