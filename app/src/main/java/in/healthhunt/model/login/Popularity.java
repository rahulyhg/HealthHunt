package in.healthhunt.model.login;

public class Popularity{
	private int hays;
	private int profile_views;
	private int observed_posts;
	private int nays;
	private String likes;

	public void setHays(int hays){
		this.hays = hays;
	}

	public int getHays(){
		return hays;
	}

	public void setProfile_views(int profile_views){
		this.profile_views = profile_views;
	}

	public int getProfile_views(){
		return profile_views;
	}

	public void setObserved_posts(int observed_posts){
		this.observed_posts = observed_posts;
	}

	public int getObserved_posts(){
		return observed_posts;
	}

	public void setNays(int nays){
		this.nays = nays;
	}

	public int getNays(){
		return nays;
	}

	public void setLikes(String likes){
		this.likes = likes;
	}

	public String getLikes(){
		return likes;
	}

	@Override
 	public String toString(){
		return 
			"Popularity{" + 
			"hays = '" + hays + '\'' + 
			",profile_views = '" + profile_views + '\'' +
			",observed_posts = '" + observed_posts + '\'' +
			",nays = '" + nays + '\'' + 
			",likes = '" + likes + '\'' + 
			"}";
		}
}
