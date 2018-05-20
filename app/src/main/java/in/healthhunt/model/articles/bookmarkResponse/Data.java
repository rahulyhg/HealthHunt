package in.healthhunt.model.articles.bookmarkResponse;

public class Data{
	private int postId;
	private String collection;
	private boolean isBookMark;
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isBookMark() {
		return isBookMark;
	}

	public void setBookMark(boolean bookMark) {
		isBookMark = bookMark;
	}

	public void setPostId(int postId){
		this.postId = postId;
	}

	public int getPostId(){
		return postId;
	}

	public void setCollection(String collection){
		this.collection = collection;
	}

	public String getCollection(){
		return collection;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"post_id = '" + postId + '\'' + 
			",collection = '" + collection + '\'' + 
			"}";
		}
}
