package in.healthhunt.model.login;

import java.util.List;

public class User{
	private int user_status;
	private String gender;
	private Object user_image;
	private List<String> roles;
	private String link;
	private String last_name;
	private String description;
	private String url;
	private Collections collections;
	private Popularity popularity;
	private String name;
	private String nickname;
	private int id;
	private String user_activation_key;
	private String registered_date;
	private String first_name;
	private String email;
	private String slug;
	private String username;

	public void setUser_status(int user_status){
		this.user_status = user_status;
	}

	public int getUser_status(){
		return user_status;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setUser_image(Object user_image){
		this.user_image = user_image;
	}

	public Object getUser_image(){
		return user_image;
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

	public void setLast_name(String last_name){
		this.last_name = last_name;
	}

	public String getLast_name(){
		return last_name;
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

	public void setUser_activation_key(String user_activation_key){
		this.user_activation_key = user_activation_key;
	}

	public String getUser_activation_key(){
		return user_activation_key;
	}

	public void setRegistered_date(String registered_date){
		this.registered_date = registered_date;
	}

	public String getRegistered_date(){
		return registered_date;
	}

	public void setFirst_name(String first_name){
		this.first_name = first_name;
	}

	public String getFirst_name(){
		return first_name;
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
			"user_status = '" + user_status + '\'' +
			",gender = '" + gender + '\'' + 
			",user_image = '" + user_image + '\'' +
			",roles = '" + roles + '\'' + 
			",link = '" + link + '\'' + 
			",last_name = '" + last_name + '\'' +
			",description = '" + description + '\'' + 
			",url = '" + url + '\'' + 
			",collections = '" + collections + '\'' + 
			",popularity = '" + popularity + '\'' + 
			",name = '" + name + '\'' + 
			",nickname = '" + nickname + '\'' + 
			",id = '" + id + '\'' + 
			",user_activation_key = '" + user_activation_key + '\'' +
			",registered_date = '" + registered_date + '\'' +
			",first_name = '" + first_name + '\'' +
			",email = '" + email + '\'' + 
			",slug = '" + slug + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}