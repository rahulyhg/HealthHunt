package in.healthhunt.presenter.homeScreenPresenter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.model.tags.TagItem;
import in.healthhunt.presenter.interactor.categoryInteractor.CategoryInteractorImpl;
import in.healthhunt.presenter.interactor.categoryInteractor.ICategoryInteractor;
import in.healthhunt.view.homeScreenView.IHomeView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class HomePresenterImp implements IHomePresenter, ICategoryInteractor.OnFinishListener {

    private String TAG = HomePresenterImp.class.getSimpleName();
    private IHomeView IHomeView;
    private Context mContext;
    private List<TagItem> mCategoryList;
    private List<TagItem> mSelectedCategoryList;
    private ICategoryInteractor ICategoryInteractor;

    public HomePresenterImp(Context context, IHomeView homeView) {
        mContext =  context;
        IHomeView = homeView;
        ICategoryInteractor = new CategoryInteractorImpl();
    }

    @Override
    public void loadFooterFragment(String tag, Bundle bundle) {
        IHomeView.loadFooterFragment(tag, bundle);
    }

    @Override
    public void loadNonFooterFragment(String tag, Bundle bundle) {
        IHomeView.loadNonFooterFragment(tag, bundle);
    }

    @Override
    public void addCategory(TagItem tagItem) {
        if(mSelectedCategoryList == null){
            mSelectedCategoryList = new ArrayList<TagItem>();
        }
        mSelectedCategoryList.add(tagItem);
    }

    @Override
    public void removeCategory(TagItem tagItem) {
        mSelectedCategoryList.remove(tagItem);
        if(mSelectedCategoryList != null && mSelectedCategoryList.isEmpty()){
            addDefaultSelectedCategory();
        }
    }

    @Override
    public boolean isCategoryContain(TagItem tagItem) {
        if(mSelectedCategoryList != null && !mSelectedCategoryList.isEmpty()){
            for(TagItem item: mSelectedCategoryList){
                if(item.getId() == tagItem.getId()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<TagItem> getSelectedCategoryList() {
        return mSelectedCategoryList;
    }

    @Override
    public TagItem getSelectedCategory(String tagId) {

        for(TagItem tagItem: mSelectedCategoryList){
            if(String.valueOf(tagItem.getId()).equalsIgnoreCase(tagId)){
                return tagItem;
            }
        }
        return null;
    }

    @Override
    public TagItem getCategory(int pos) {
        return mCategoryList.get(pos);
    }

    @Override
    public int getCategoryCount() {
        int count = 0;
        if(mCategoryList != null){
            count = mCategoryList.size();
        }
        return count;
    }

    @Override
    public void fetchCategories() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.CATEGORIES, String.valueOf(1));
        ICategoryInteractor.fetchCategories(mContext, map, this);
    }

    @Override
    public void onCategorySuccess(List<TagItem> categoriesList) {
        mCategoryList = categoriesList;
        sortCategory();
        addDefaultSelectedCategory();
        IHomeView.updateDrawerFragment();
    }

    private void addDefaultSelectedCategory() {
        if(mCategoryList != null){
            TagItem tagItem = mCategoryList.get(0); //For All
            if(!isCategoryContain(tagItem)) {
                addCategory(tagItem);
            }
        }
    }

    @Override
    public void onError(RestError errorInfo) {

    }

    private void sortCategory() {
        List<TagItem> itemList = new ArrayList<TagItem>();
        itemList.addAll(mCategoryList);


        if(mCategoryList.size() > 5) {
            for (int j = 0; j < mCategoryList.size(); j++) {
                TagItem tagItem = mCategoryList.get(j);
                if (tagItem.getName().equalsIgnoreCase(Constants.All)) {
                    itemList.set(0, tagItem);
                } else if (tagItem.getName().equalsIgnoreCase(Constants.NUTRITION)) {
                    itemList.set(1, tagItem);
                } else if (tagItem.getName().equalsIgnoreCase(Constants.FITNESS)) {
                    itemList.set(2, tagItem);
                } else if (tagItem.getName().equalsIgnoreCase(Constants.ORGANIC_BEAUTY)) {
                    itemList.set(3, tagItem);
                } else if (tagItem.getName().equalsIgnoreCase(Constants.MENTAL_WELLBEING)) {
                    itemList.set(4, tagItem);
                } else if (tagItem.getName().equalsIgnoreCase(Constants.LOVE)) {
                    itemList.set(5, tagItem);
                }
            }
            mCategoryList = itemList;
        }

        Log.i("TAGCATEROY", "Listy " + mCategoryList);

    }
}
