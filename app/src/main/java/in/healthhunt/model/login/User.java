package in.healthhunt.model.login;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Table(name = "Users")
public class User extends Model{

	//@Column(name = "capabilities")
	//private Capabilities capabilities;

	@Column(name = "userImage")
	private String userImage;

	//@Column(name = "roles")
	private List<String> roles;

	@Column(name = "link")
	private String link;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "description")
	private String description;

	@Column(name = "url")
	private String url;
	//private Collections collections;

	//@Column(name = "popularity")
	private Popularity popularity;

	@Column(name = "name")
	private String name;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "user_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
	@SerializedName("id")
	private String user_id;

	@Column(name = "registeredDate")
	private String registeredDate;

	//@Column(name = "extraCapabilities")
	//private ExtraCapabilities extraCapabilities;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "email")
	private String email;

	@Column(name = "slug")
	private String slug;

	@Column(name = "username")
	private String username;

	/*public void setCapabilities(Capabilities capabilities){
		this.capabilities = capabilities;
	}

	public Capabilities getCapabilities(){
		return capabilities;
	}
*/
	public void setUserImage(String  userImage){
		this.userImage = userImage;
	}

	public String getUserImage(){
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

	/*public void setCollections(Collections collections){
		this.collections = collections;
	}

	public Collections getCollections(){
		return collections;
	}
*/
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

	public void setUserId(String id){
		this.user_id = id;
	}

	public String getUserId(){
		return user_id;
	}

	public void setRegisteredDate(String registeredDate){
		this.registeredDate = registeredDate;
	}

	public String getRegisteredDate(){
		return registeredDate;
	}

	/*public void setExtraCapabilities(ExtraCapabilities extraCapabilities){
		this.extraCapabilities = extraCapabilities;
	}

	public ExtraCapabilities getExtraCapabilities(){
		return extraCapabilities;
	}*/

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

	public static User getUser(){
		return new Select().from(User.class).executeSingle();
	}

	@Override
	public String toString(){
		return
				"User{" +
						//"capabilities = '" + capabilities + '\'' +
						",user_image = '" + userImage + '\'' +
						",roles = '" + roles + '\'' +
						",link = '" + link + '\'' +
						",last_name = '" + lastName + '\'' +
						",description = '" + description + '\'' +
						",url = '" + url + '\'' +
						//'" + collections + '\'' +
						",popularity = '" + popularity + '\'' +
						",name = '" + name + '\'' +
						",nickname = '" + nickname + '\'' +
						",userid = '" + user_id + '\'' +
						",registered_date = '" + registeredDate + '\'' +
						//",extra_capabilities = '" + extraCapabilities + '\'' +
						",first_name = '" + firstName + '\'' +
						",email = '" + email + '\'' +
						",slug = '" + slug + '\'' +
						",username = '" + username + '\'' +
						"}";
	}
}