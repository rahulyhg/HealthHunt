package in.healthhunt.model.login;

public class Popularity{
	private int hays;
	private int profileViews;
	private int observedPosts;
	private int nays;
	private int likes;

	public void setHays(int hays){
		this.hays = hays;
	}

	public int getHays(){
		return hays;
	}

	public void setProfileViews(int profileViews){
		this.profileViews = profileViews;
	}

	public int getProfileViews(){
		return profileViews;
	}

	public void setObservedPosts(int observedPosts){
		this.observedPosts = observedPosts;
	}

	public int getObservedPosts(){
		return observedPosts;
	}

	public void setNays(int nays){
		this.nays = nays;
	}

	public int getNays(){
		return nays;
	}

	public void setLikes(int likes){
		this.likes = likes;
	}

	public int getLikes(){
		return likes;
	}

	@Override
 	public String toString(){
		return 
			"Popularity{" + 
			"hays = '" + hays + '\'' + 
			",profile_views = '" + profileViews + '\'' + 
			",observed_posts = '" + observedPosts + '\'' + 
			",nays = '" + nays + '\'' + 
			",likes = '" + likes + '\'' + 
			"}";
		}
}
