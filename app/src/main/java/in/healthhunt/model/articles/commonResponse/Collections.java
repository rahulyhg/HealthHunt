package in.healthhunt.model.articles.commonResponse;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishekkumar on 5/31/18.
 */

@Table(name = "Collections")
public class Collections extends Model {


    @SerializedName("id")
    @Column(name = "collect_id")
    String collect_id;

    @Column(name = "name")
    String name;

    @Column(name = "parent_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE, onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    private long parent_id;

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }


    public Collections(){
        super();
    }

    public String getCollect_Id() {
        return collect_id;
    }

    public void setCollect_Id(String id) {
        this.collect_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
