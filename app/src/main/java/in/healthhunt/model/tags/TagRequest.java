package in.healthhunt.model.tags;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishekkumar on 5/6/18.
 */

public class TagRequest {

    @SerializedName("page")
    private int page;

    @SerializedName("per_page")
    private int perPage;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
}
