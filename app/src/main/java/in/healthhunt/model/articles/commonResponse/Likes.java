package in.healthhunt.model.articles.commonResponse;

public class Likes{
	private String hays;
	private String nays;
	private String likes;

	public void setHays(String hays){
		this.hays = hays;
	}

	public String getHays(){
		return hays;
	}

	public void setNays(String nays){
		this.nays = nays;
	}

	public String getNays(){
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
			"Likes{" + 
			"hays = '" + hays + '\'' + 
			",nays = '" + nays + '\'' + 
			",likes = '" + likes + '\'' + 
			"}";
		}
}
