package in.healthhunt.model.comment;

import java.util.List;

public class AllCommentInfo {
	private List<CommentsItem> comments;
	private int offset;
	private String limit;

	public void setComments(List<CommentsItem> comments){
		this.comments = comments;
	}

	public List<CommentsItem> getComments(){
		return comments;
	}

	public void setOffset(int offset){
		this.offset = offset;
	}

	public int getOffset(){
		return offset;
	}

	public void setLimit(String limit){
		this.limit = limit;
	}

	public String getLimit(){
		return limit;
	}

	@Override
 	public String toString(){
		return 
			"AllCommentInfo{" +
			"comments = '" + comments + '\'' + 
			",offset = '" + offset + '\'' + 
			",limit = '" + limit + '\'' + 
			"}";
		}
}