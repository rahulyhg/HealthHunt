package in.healthhunt.model.articles.productResponse;

import java.util.List;

public class Data {
	private String total;
	private int offset;
	private int limit;
	private String elastic_token;
	private List<ProductPostItem> posts;
	private String date_healthlet;

	public void setTotal(String total){
		this.total = total;
	}

	public String getTotal(){
		return total;
	}

	public void setOffset(int offset){
		this.offset = offset;
	}

	public int getOffset(){
		return offset;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	public void setElastic_token(String elastic_token){
		this.elastic_token = elastic_token;
	}

	public String getElastic_token(){
		return elastic_token;
	}

	public void setPosts(List<ProductPostItem> posts){
		this.posts = posts;
	}

	public List<ProductPostItem> getPosts(){
		return posts;
	}

	public void setDate_healthlet(String date_healthlet){
		this.date_healthlet = date_healthlet;
	}

	public String getDate_healthlet(){
		return date_healthlet;
	}

	@Override
 	public String toString(){
		return 
			"BookMarkData{" +
			"total = '" + total + '\'' + 
			",offset = '" + offset + '\'' + 
			",limit = '" + limit + '\'' + 
			",elastic_token = '" + elastic_token + '\'' +
			",posts = '" + posts + '\'' + 
			",date_healthlet = '" + date_healthlet + '\'' +
			"}";
		}
}