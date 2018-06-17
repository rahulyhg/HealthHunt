package in.healthhunt.model.articles.articleResponse;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
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

@Table(name = "ArticlePosts")
public class ArticlePostItem extends Model{

	@Column(name = "date")
	private String date;

	@Column(name = "trending")
	private int trending;

	@Column(name = "link")
	private String link;

	@Column(name = "post_youtube_id")
	private String post_youtube_id;

	@Column(name = "media", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private List<MediaItem> media;

	@Column(name = "type")
	private String type;

	@Column(name = "title", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private Title title;

	@Column(name = "content", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private Content content;

	@Column(name = "is_commented")
	private int is_commented;

	@Column(name = "modified")
	private String modified;

	@Column(name = "article_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
	@SerializedName("id")
	private String article_id;

	@Column(name = "categories", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private List<CategoriesItem> categories;

	@Column(name = "date_gmt")
	private String date_gmt;

	@Column(name = "slug")
	private String slug;

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

	@Column(name = "comments")
	private String comments;

	@Column(name = "modified_gmt")
	private String modified_gmt;

	@Column(name = "Authors", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private Author author;

	@Column(name = "format")
	private String format;

	@Column(name = "Synopsis", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private Synopsis synopsis;

	@Column(name = "comment_status")
	private String comment_status;

	@Column(name = "tags", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private List<TagsItem> tags;

	@Column(name = "share_count")
	private String share_count;

	@Column(name = "video_thumbnail")
	private String video_thumbnail;

	@Column(name = "ping_status")
	private String ping_status;

	@Column(name = "read_time")
	private String read_time;

	@Column(name = "sticky")
	private boolean sticky;

	@Column(name = "Excerpts", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
	private Excerpt excerpt;

	@Column(name = "is_free_trial")
	private String is_free_trial;

	@Column(name = "is_Video")  // not from server
	private boolean isVideo;

	public boolean isVideo() {
		return isVideo;
	}

	public void setVideo(boolean video) {
		isVideo = video;
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

	public void setModified(String modified){
		this.modified = modified;
	}

	public String getModified(){
		return modified;
	}

	public void setArticle_Id(String id){
		this.article_id = id;
	}

	public String getArticle_Id(){
		return article_id;
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

	public void setShare_count(String share_count){
		this.share_count = share_count;
	}

	public String getShare_count(){
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

	public void setRead_time(String read_time){
		this.read_time = read_time;
	}

	public String getRead_time(){
		return read_time;
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

	public void setSynopsis(Synopsis synopsis){
		this.synopsis = synopsis;
	}

	public Synopsis getSynopsis(){
		return synopsis;
	}

	public static void removeArticle(String article_id){
		new Delete().from(ArticlePostItem.class).where("article_id = ?" , article_id).execute();
	}

	public static ArticlePostItem getArticle(String article_id){
		return new Select().from(ArticlePostItem.class).where("article_id = ?" , article_id).executeSingle();
	}


	@Override
	public String toString(){
		return
				"ArticlePostItem{" +
						"date = '" + date + '\'' +
						",trending = '" + trending + '\'' +
						",link = '" + link + '\'' +
						",post_youtube_id = '" + post_youtube_id + '\'' +
						",media = '" + media + '\'' +
						",type = '" + type + '\'' +
						",title = '" + title + '\'' +
						",content = '" + content + '\'' +
						",is_commented = '" + is_commented + '\'' +
						",modified = '" + modified + '\'' +
						",id = '" + article_id + '\'' +
						",categories = '" + categories + '\'' +
						",date_gmt = '" + date_gmt + '\'' +
						",slug = '" + slug + '\'' +
						",post_currency = '" + post_currency + '\'' +
						",video_thumbnail_icon = '" + video_thumbnail_icon + '\'' +
						",likes = '" + likes + '\'' +
						",tss_loved = '" + tss_loved + '\'' +
						",current_user = '" + current_user + '\'' +
						",comments = '" + comments + '\'' +
						",modified_gmt = '" + modified_gmt + '\'' +
						",author = '" + author + '\'' +
						",format = '" + format + '\'' +
						",synopsis = '" + synopsis + '\'' +
						",comment_status = '" + comment_status + '\'' +
						",tags = '" + tags + '\'' +
						",share_count = '" + share_count + '\'' +
						",video_thumbnail = '" + video_thumbnail + '\'' +
						",ping_status = '" + ping_status + '\'' +
						",read_time = '" + read_time + '\'' +
						",sticky = '" + sticky + '\'' +
						",excerpt = '" + excerpt + '\'' +
						",is_free_trial = '" + is_free_trial + '\'' +
						"}";
	}
}