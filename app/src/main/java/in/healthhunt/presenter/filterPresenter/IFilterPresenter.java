package in.healthhunt.presenter.filterPresenter;

import java.util.List;

import in.healthhunt.model.filter.DataItem;
import in.healthhunt.view.homeScreenView.filterView.IFilterView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IFilterPresenter {
     void fetchFilters(String type);
     int getFilterProductCount();
     int getFilterBrandCount();
     DataItem getProductItem(int pos);
     DataItem getBrandItem(int pos);
     IFilterView getFilterView();

     String getProductID();
     void setProductID(String id);

     void addBrand(String id);
     void removeBrand(String id);
     boolean isBrandContain(String id);
     List<String> getBrandList();
     List<String> getCityList();

     String getCity();
     void  setCity(String name);
}
