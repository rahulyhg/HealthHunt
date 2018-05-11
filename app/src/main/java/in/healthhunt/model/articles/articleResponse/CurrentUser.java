package in.healthhunt.model.articles.articleResponse;

import java.util.List;

public class CurrentUser{
	private int hay;
	private int like;
	private List<Object> collections;
	private boolean bookmarked;
	private int nay;

	public void setHay(int hay){
		this.hay = hay;
	}

	public int getHay(){
		return hay;
	}

	public void setLike(int like){
		this.like = like;
	}

	public int getLike(){
		return like;
	}

	public void setCollections(List<Object> collections){
		this.collections = collections;
	}

	public List<Object> getCollections(){
		return collections;
	}

	public void setBookmarked(boolean bookmarked){
		this.bookmarked = bookmarked;
	}

	public boolean isBookmarked(){
		return bookmarked;
	}

	public void setNay(int nay){
		this.nay = nay;
	}

	public int getNay(){
		return nay;
	}

	@Override
 	public String toString(){
		return 
			"CurrentUser{" + 
			"hay = '" + hay + '\'' + 
			",like = '" + like + '\'' + 
			",collections = '" + collections + '\'' + 
			",bookmarked = '" + bookmarked + '\'' + 
			",nay = '" + nay + '\'' + 
			"}";
		}
}