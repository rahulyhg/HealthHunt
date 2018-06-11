package in.healthhunt.model.articles.productResponse;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.healthhunt.model.articles.commonResponse.Author;
import in.healthhunt.model.articles.commonResponse.CategoriesItem;
import in.healthhunt.model.articles.commonResponse.Content;
import in.healthhunt.model.articles.commonResponse.CurrentUser;
import in.healthhunt.model.articles.commonResponse.Excerpt;
import in.healthhunt.model.articles.commonResponse.Likes;
import in.healthhunt.model.articles.commonResponse.MediaItem;
import in.healthhunt.model.articles.commonResponse.Synopsis;
import in.healthhunt.model.articles.commonResponse.TagsItem;
import in.healthhunt.model.articles.commonResponse.Title;

@Table(name = "ProductPosts")
public class ProductPostItem extends Model{

	@Column(name = "date")
	private String date;

	@Column(name = "trending")
	private int trending;

	@Column(name = "parent")
	private String parent;

	@Column(name = "password")
	private String password;

	@Column(name = "brand")
	private String brand;

	@Column(name = "status")
	private String status;

	@Column(name = "template")
	private String template;

	@Column(name = "menu_order")
	private String menu_order;

	@Column(name = "brand_other_name")
	private String brand_other_name;

	@Column(name = "product_type_other_name")
	private String product_type_other_name;

	@Column(name = "post_contactno")
	private String post_contactno;

	@Column(name = "link")
	private String link;

	@Column(name = "post_youtube_id")
	private String post_youtube_id;

	@Column(name = "media", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private List<MediaItem> media;

	@Column(name = "type")
	private String type;

	@Column(name = "title", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	public Title title;

	@Column(name = "content", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private Content content;

	@Column(name = "is_commented")
	private int is_commented;

	@Column(name = "post_name")
	private String post_name;

	@Column(name = "modified")
	private String modified;

	@Column(name = "product_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
	@SerializedName("id")
	private String product_id;


	@Column(name = "post_email")
	private String post_email;

	@Column(name = "categories", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private List<CategoriesItem> categories;

	@Column(name = "date_gmt")
	private String date_gmt;

	@Column(name = "slug")
	private String slug;

	@Column(name = "market_type")
	private String market_type;

	@Column(name = "post_currency")
	private String post_currency;

	@Column(name = "video_thumbnail_icon")
	private String video_thumbnail_icon;

	@Column(name = "Likes", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private Likes likes;

	@Column(name = "tss_loved")
	private int tss_loved;

	@Column(name = "CurrentUsers", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private CurrentUser current_user;

	@Column(name = "post_quantity")
	private String post_quantity;

	@Column(name = "post_unit")
	private String post_unit;

	@Column(name = "comments")
	private String comments;

	@Column(name = "modified_gmt")
	private String modified_gmt;

	@Column(name = "post_delivery")
	private String post_delivery;

	@Column(name = "post_location")
	private String post_location;

	@Column(name = "Authors", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private Author author;

	@Column(name = "format")
	private String format;

	@Column(name = "post_price")
	private String post_price;

	@Column(name = "Synopsis", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private Synopsis synopsis;

	@Column(name = "comment_status")
	private String comment_status;


	@Column(name = "tags", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private List<TagsItem> tags;

	@Column(name = "share_count")
	private int share_count;

	@Column(name = "video_thumbnail")
	private String video_thumbnail;

	@Column(name = "ping_status")
	private String ping_status;

	@Column(name = "read_time")
	private int read_time;

	@Column(name = "post_url")
	private String post_url;

	@Column(name = "company_name")
	private String company_name;

	@Column(name = "sticky")
	private boolean sticky;

	@Column(name = "Excerpts", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private Excerpt excerpt;

	@Column(name = "is_free_trial")
	private String is_free_trial;

	public ProductPostItem(){
		super();
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getMenu_order() {
		return menu_order;
	}

	public void setMenu_order(String menu_order) {
		this.menu_order = menu_order;
	}

	public String getBrand_other_name() {
		return brand_other_name;
	}

	public void setBrand_other_name(String brand_other_name) {
		this.brand_other_name = brand_other_name;
	}

	public String getProduct_type_other_name() {
		return product_type_other_name;
	}

	public void setProduct_type_other_name(String product_type_other_name) {
		this.product_type_other_name = product_type_other_name;
	}

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

	public void setProduct_id(String id){
		this.product_id = id;
	}

	public String getProduct_id(){
		return product_id;
	}

	public void setPost_email(String post_email){
		this.post_email = post_email;
	}

	public String getPost_email(){
		return post_email;
	}

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	public void setDate_gmt(String date_gmt){
		this.date_gmt = date_gmt;
	}

	public String getDate_gmt(){
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

	public void setModified_gmt(String modified_gmt){
		this.modified_gmt = modified_gmt;
	}

	public String getModified_gmt(){
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

	public void setTags(List<TagsItem> tags){
		this.tags = tags;
	}

	public List<TagsItem> getTags(){
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
			",id = '" + product_id + '\'' +
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