package in.healthhunt.model.comment;

/**
 * Created by abhishekkumar on 5/22/18.
 */

public class CommentRequest {

    String post_id;
    String content;

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
