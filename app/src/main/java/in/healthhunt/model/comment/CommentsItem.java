package in.healthhunt.model.comment;

public class CommentsItem{
	private String date;
	private int parent;
	private String image;
	private int post;
	private Author author;
	private String link;
	private int id;
	private String type;
	private String date_gmt;
	private Content content;
	private String status;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setParent(int parent){
		this.parent = parent;
	}

	public int getParent(){
		return parent;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setPost(int post){
		this.post = post;
	}

	public int getPost(){
		return post;
	}

	public void setAuthor(Author author){
		this.author = author;
	}

	public Author getAuthor(){
		return author;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setDate_gmt(String date_gmt){
		this.date_gmt = date_gmt;
	}

	public String getDate_gmt(){
		return date_gmt;
	}

	public void setContent(Content content){
		this.content = content;
	}

	public Content getContent(){
		return content;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"CommentsItem{" + 
			"date = '" + date + '\'' + 
			",parent = '" + parent + '\'' + 
			",image = '" + image + '\'' + 
			",post = '" + post + '\'' + 
			",author = '" + author + '\'' + 
			",link = '" + link + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",date_gmt = '" + date_gmt + '\'' +
			",content = '" + content + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
