package in.healthhunt.model.beans;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class Tag {

    private long mTagId;
    private String mTag;
    private String mUrl;

    public String getmTag() {
        return mTag;
    }

    public void setmTag(String mTag) {
        this.mTag = mTag;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public long getmTagId() {
        return mTagId;
    }

    public void setmTagId(long mTagId) {
        this.mTagId = mTagId;
    }

}
