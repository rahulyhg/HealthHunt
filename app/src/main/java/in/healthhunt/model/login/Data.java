package in.healthhunt.model.login;

public class Data{
	private User user;

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"user = '" + user + '\'' + 
			"}";
		}
}
