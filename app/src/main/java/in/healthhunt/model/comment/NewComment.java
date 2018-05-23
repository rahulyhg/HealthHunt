package in.healthhunt.model.comment;

public class NewComment {
	private CommentsItem comment;
	private int post_comment_count;

	public void setComment(CommentsItem comment){
		this.comment = comment;
	}

	public CommentsItem getComment(){
		return comment;
	}

	public int getPost_comment_count() {
		return post_comment_count;
	}

	public void setPost_comment_count(int post_comment_count) {
		this.post_comment_count = post_comment_count;
	}

	@Override
 	public String toString(){
		return 
			"NewComment{" +
			"comment = '" + comment + '\'' +
			",post_comment_count = '" + post_comment_count + '\'' +
			"}";
		}
}