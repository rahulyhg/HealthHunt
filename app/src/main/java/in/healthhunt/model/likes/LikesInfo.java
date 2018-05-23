package in.healthhunt.model.likes;

import in.healthhunt.model.articles.commonResponse.Likes;

public class LikesInfo {
	private int post;
	private Likes likes;

	public void setPost(int post){
		this.post = post;
	}

	public int getPost(){
		return post;
	}

	public void setLikes(Likes likes){
		this.likes = likes;
	}

	public Likes getLikes(){
		return likes;
	}

	@Override
 	public String toString(){
		return 
			"LikesInfo{" +
			"post = '" + post + '\'' + 
			",likes = '" + likes + '\'' + 
			"}";
		}
}
