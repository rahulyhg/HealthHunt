package in.healthhunt.model.articles.postProductResponse;

import java.util.List;

import in.healthhunt.model.articles.commonResponse.Author;
import in.healthhunt.model.articles.commonResponse.CurrentUser;
import in.healthhunt.model.articles.commonResponse.Likes;
import in.healthhunt.model.articles.commonResponse.MediaItem;
import in.healthhunt.model.articles.productResponse.Content;
import in.healthhunt.model.articles.productResponse.Excerpt;
import in.healthhunt.model.articles.productResponse.Synopsis;
import in.healthhunt.model.articles.productResponse.Title;

public class ProductPost {
	private String date;
	private int trending;
	private String post_contactno;
	private String link;
	private String post_youtube_id;
	private List<MediaItem> media;
	private String type;
	private Title title;
	private Content content;
	private int is_commented;
	private String post_name;
	private String modified;
	private int id;
	private String post_email;
	private List<Object> categories;
	private Object date_gmt;
	private String slug;
	private String market_type;
	private String post_currency;
	private String video_thumbnail_icon;
	private Likes likes;
	private int tss_loved;
	private CurrentUser current_user;
	private String post_quantity;
	private String post_unit;
	private String comments;
	private Object modified_gmt;
	private String post_delivery;
	private String post_location;
	private Author author;
	private String format;
	private String post_price;
	private Synopsis synopsis;
	private String comment_status;
	private List<Object> tags;
	private int share_count;
	private String video_thumbnail;
	private String ping_status;
	private int read_time;
	private String post_url;
	private String company_name;
	private boolean sticky;
	private Excerpt excerpt;
	private String is_free_trial;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setTrending(int trending){
		this.trending = trending;
	}

	public int getTrending(){
		return trending;
	}

	public void setPost_contactno(String post_contactno){
		this.post_contactno = post_contactno;
	}

	public String getPost_contactno(){
		return post_contactno;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}

	public void setPost_youtube_id(String post_youtube_id){
		this.post_youtube_id = post_youtube_id;
	}

	public String getPost_youtube_id(){
		return post_youtube_id;
	}

	public void setMedia(List<MediaItem> media){
		this.media = media;
	}

	public List<MediaItem> getMedia(){
		return media;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setTitle(Title title){
		this.title = title;
	}

	public Title getTitle(){
		return title;
	}

	public void setContent(Content content){
		this.content = content;
	}

	public Content getContent(){
		return content;
	}

	public void setIs_commented(int is_commented){
		this.is_commented = is_commented;
	}

	public int getIs_commented(){
		return is_commented;
	}

	public void setPost_name(String post_name){
		this.post_name = post_name;
	}

	public String getPost_name(){
		return post_name;
	}

	public void setModified(String modified){
		this.modified = modified;
	}

	public String getModified(){
		return modified;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPost_email(String post_email){
		this.post_email = post_email;
	}

	public String getPost_email(){
		return post_email;
	}

	public void setCategories(List<Object> categories){
		this.categories = categories;
	}

	public List<Object> getCategories(){
		return categories;
	}

	public void setDate_gmt(Object date_gmt){
		this.date_gmt = date_gmt;
	}

	public Object getDate_gmt(){
		return date_gmt;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setMarket_type(String market_type){
		this.market_type = market_type;
	}

	public String getMarket_type(){
		return market_type;
	}

	public void setPost_currency(String post_currency){
		this.post_currency = post_currency;
	}

	public String getPost_currency(){
		return post_currency;
	}

	public void setVideo_thumbnail_icon(String video_thumbnail_icon){
		this.video_thumbnail_icon = video_thumbnail_icon;
	}

	public String getVideo_thumbnail_icon(){
		return video_thumbnail_icon;
	}

	public void setLikes(Likes likes){
		this.likes = likes;
	}

	public Likes getLikes(){
		return likes;
	}

	public void setTss_loved(int tss_loved){
		this.tss_loved = tss_loved;
	}

	public int getTss_loved(){
		return tss_loved;
	}

	public void setCurrent_user(CurrentUser current_user){
		this.current_user = current_user;
	}

	public CurrentUser getCurrent_user(){
		return current_user;
	}

	public void setPost_quantity(String post_quantity){
		this.post_quantity = post_quantity;
	}

	public String getPost_quantity(){
		return post_quantity;
	}

	public void setPost_unit(String post_unit){
		this.post_unit = post_unit;
	}

	public String getPost_unit(){
		return post_unit;
	}

	public void setComments(String comments){
		this.comments = comments;
	}

	public String getComments(){
		return comments;
	}

	public void setModified_gmt(Object modified_gmt){
		this.modified_gmt = modified_gmt;
	}

	public Object getModified_gmt(){
		return modified_gmt;
	}

	public void setPost_delivery(String post_delivery){
		this.post_delivery = post_delivery;
	}

	public String getPost_delivery(){
		return post_delivery;
	}

	public void setPost_location(String post_location){
		this.post_location = post_location;
	}

	public String getPost_location(){
		return post_location;
	}

	public void setAuthor(Author author){
		this.author = author;
	}

	public Author getAuthor(){
		return author;
	}

	public void setFormat(String format){
		this.format = format;
	}

	public String getFormat(){
		return format;
	}

	public void setPost_price(String post_price){
		this.post_price = post_price;
	}

	public String getPost_price(){
		return post_price;
	}

	public void setSynopsis(Synopsis synopsis){
		this.synopsis = synopsis;
	}

	public Synopsis getSynopsis(){
		return synopsis;
	}

	public void setComment_status(String comment_status){
		this.comment_status = comment_status;
	}

	public String getComment_status(){
		return comment_status;
	}

	public void setTags(List<Object> tags){
		this.tags = tags;
	}

	public List<Object> getTags(){
		return tags;
	}

	public void setShare_count(int share_count){
		this.share_count = share_count;
	}

	public int getShare_count(){
		return share_count;
	}

	public void setVideo_thumbnail(String video_thumbnail){
		this.video_thumbnail = video_thumbnail;
	}

	public String getVideo_thumbnail(){
		return video_thumbnail;
	}

	public void setPing_status(String ping_status){
		this.ping_status = ping_status;
	}

	public String getPing_status(){
		return ping_status;
	}

	public void setRead_time(int read_time){
		this.read_time = read_time;
	}

	public int getRead_time(){
		return read_time;
	}

	public void setPost_url(String post_url){
		this.post_url = post_url;
	}

	public String getPost_url(){
		return post_url;
	}

	public void setCompany_name(String company_name){
		this.company_name = company_name;
	}

	public String getCompany_name(){
		return company_name;
	}

	public void setSticky(boolean sticky){
		this.sticky = sticky;
	}

	public boolean isSticky(){
		return sticky;
	}

	public void setExcerpt(Excerpt excerpt){
		this.excerpt = excerpt;
	}

	public Excerpt getExcerpt(){
		return excerpt;
	}

	public void setIs_free_trial(String is_free_trial){
		this.is_free_trial = is_free_trial;
	}

	public String getIs_free_trial(){
		return is_free_trial;
	}

	@Override
 	public String toString(){
		return 
			"ProductPost{" +
			"date = '" + date + '\'' + 
			",trending = '" + trending + '\'' + 
			",post_contactno = '" + post_contactno + '\'' +
			",link = '" + link + '\'' + 
			",post_youtube_id = '" + post_youtube_id + '\'' +
			",media = '" + media + '\'' + 
			",type = '" + type + '\'' + 
			",title = '" + title + '\'' + 
			",content = '" + content + '\'' + 
			",is_commented = '" + is_commented + '\'' +
			",post_name = '" + post_name + '\'' +
			",modified = '" + modified + '\'' + 
			",id = '" + id + '\'' + 
			",post_email = '" + post_email + '\'' +
			",categories = '" + categories + '\'' + 
			",date_gmt = '" + date_gmt + '\'' +
			",slug = '" + slug + '\'' + 
			",market_type = '" + market_type + '\'' +
			",post_currency = '" + post_currency + '\'' +
			",video_thumbnail_icon = '" + video_thumbnail_icon + '\'' +
			",likes = '" + likes + '\'' + 
			",tss_loved = '" + tss_loved + '\'' +
			",current_user = '" + current_user + '\'' +
			",post_quantity = '" + post_quantity + '\'' +
			",post_unit = '" + post_unit + '\'' +
			",comments = '" + comments + '\'' + 
			",modified_gmt = '" + modified_gmt + '\'' +
			",post_delivery = '" + post_delivery + '\'' +
			",post_location = '" + post_location + '\'' +
			",author = '" + author + '\'' + 
			",format = '" + format + '\'' + 
			",post_price = '" + post_price + '\'' +
			",synopsis = '" + synopsis + '\'' + 
			",comment_status = '" + comment_status + '\'' +
			",tags = '" + tags + '\'' + 
			",share_count = '" + share_count + '\'' +
			",video_thumbnail = '" + video_thumbnail + '\'' +
			",ping_status = '" + ping_status + '\'' +
			",read_time = '" + read_time + '\'' +
			",post_url = '" + post_url + '\'' +
			",company_name = '" + company_name + '\'' +
			",sticky = '" + sticky + '\'' + 
			",excerpt = '" + excerpt + '\'' + 
			",is_free_trial = '" + is_free_trial + '\'' +
			"}";
		}
}