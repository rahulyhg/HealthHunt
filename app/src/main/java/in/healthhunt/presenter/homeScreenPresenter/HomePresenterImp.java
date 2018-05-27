package in.healthhunt.presenter.homeScreenPresenter;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import in.healthhunt.view.homeScreenView.IHomeView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class HomePresenterImp implements IHomePresenter {

    private String TAG = HomePresenterImp.class.getSimpleName();
    private IHomeView IHomeView;
    private Context mContext;
    private List<String> mCategoryList;

    public HomePresenterImp(Context context, IHomeView homeView) {
        mContext =  context;
        IHomeView = homeView;
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
    public void addCategory(String str) {
        if(mCategoryList == null){
            mCategoryList = new ArrayList<String>();
        }
        mCategoryList.add(str);
        IHomeView.closeDrawer();
        IHomeView.updateMyFeedArticles();
    }

    @Override
    public void removeCategory(String str) {
        mCategoryList.remove(str);
        IHomeView.closeDrawer();
        IHomeView.updateMyFeedArticles();
    }

    @Override
    public boolean isCategoryContain(String str) {
        if(mCategoryList != null && mCategoryList.contains(str)){
            return true;
        }
        return false;
    }

    @Override
    public List<String> getCategoryList() {
        return mCategoryList;
    }
}
