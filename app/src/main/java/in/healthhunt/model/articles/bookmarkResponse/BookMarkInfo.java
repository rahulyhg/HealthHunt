package in.healthhunt.model.articles.bookmarkResponse;

public class BookMarkInfo{
	private String post_id;
	private String collection;
	private boolean isBookMark;
	private int type;

	public boolean isBookMark() {
		return isBookMark;
	}

	public void setBookMark(boolean bookMark) {
		isBookMark = bookMark;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setPost_id(String post_id){
		this.post_id = post_id;
	}

	public String getPost_id(){
		return post_id;
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
			"BookMarkInfo{" + 
			"post_id = '" + post_id + '\'' +
			",collection = '" + collection + '\'' + 
			"}";
		}
}
