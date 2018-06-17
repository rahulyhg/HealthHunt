package in.healthhunt.presenter.homeScreenPresenter;

import android.os.Bundle;

import java.util.List;

import in.healthhunt.model.tags.TagItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IHomePresenter{
    void loadFooterFragment(String tag, Bundle bundle);
    void loadNonFooterFragment(String tag, Bundle bundle);
    void addCategory(TagItem tagItem);
    void removeCategory(TagItem tagItem);
    boolean isCategoryContain(TagItem tagItem);
    List<TagItem> getSelectedCategoryList();
    TagItem getSelectedCategory(String tagId);
    TagItem getCategory(int pos);
    int getCategoryCount();
    void fetchCategories();
}
