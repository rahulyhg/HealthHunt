package in.healthhunt.model.beans.login;

import java.util.List;

public class User{
	private int userStatus;
	private String gender;
	private Object userImage;
	private List<String> roles;
	private String link;
	private String lastName;
	private String description;
	private String url;
	private Collections collections;
	private Popularity popularity;
	private String name;
	private String nickname;
	private int id;
	private String userActivationKey;
	private String registeredDate;
	private String firstName;
	private String email;
	private String slug;
	private String username;

	public void setUserStatus(int userStatus){
		this.userStatus = userStatus;
	}

	public int getUserStatus(){
		return userStatus;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setUserImage(Object userImage){
		this.userImage = userImage;
	}

	public Object getUserImage(){
		return userImage;
	}

	public void setRoles(List<String> roles){
		this.roles = roles;
	}

	public List<String> getRoles(){
		return roles;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setCollections(Collections collections){
		this.collections = collections;
	}

	public Collections getCollections(){
		return collections;
	}

	public void setPopularity(Popularity popularity){
		this.popularity = popularity;
	}

	public Popularity getPopularity(){
		return popularity;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public String getNickname(){
		return nickname;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUserActivationKey(String userActivationKey){
		this.userActivationKey = userActivationKey;
	}

	public String getUserActivationKey(){
		return userActivationKey;
	}

	public void setRegisteredDate(String registeredDate){
		this.registeredDate = registeredDate;
	}

	public String getRegisteredDate(){
		return registeredDate;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"user_status = '" + userStatus + '\'' + 
			",gender = '" + gender + '\'' + 
			",user_image = '" + userImage + '\'' + 
			",roles = '" + roles + '\'' + 
			",link = '" + link + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",description = '" + description + '\'' + 
			",url = '" + url + '\'' + 
			",collections = '" + collections + '\'' + 
			",popularity = '" + popularity + '\'' + 
			",name = '" + name + '\'' + 
			",nickname = '" + nickname + '\'' + 
			",id = '" + id + '\'' + 
			",user_activation_key = '" + userActivationKey + '\'' + 
			",registered_date = '" + registeredDate + '\'' + 
			",first_name = '" + firstName + '\'' + 
			",email = '" + email + '\'' + 
			",slug = '" + slug + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}