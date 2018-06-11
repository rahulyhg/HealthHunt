package in.healthhunt.view.homeScreenView.filterView.cityView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.presenter.filterPresenter.IFilterPresenter;

/**
 * Created by abhishekkumar on 5/27/18.
 */

public class CityAdapter extends ArrayAdapter<String> implements Filterable {

    List<String> resultList;
    Context mContext;
    int mResource;

     PlaceAPI mPlaceAPI = new PlaceAPI();
    //IFilterPresenter IFilterPresenter;

    public CityAdapter(Context context, int resource, IFilterPresenter filterPresenter) {
        super(context, resource);

        mContext = context;
        mResource = resource;
       // IFilterPresenter = filterPresenter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CityViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.popup_window_item, parent, false);
            viewHolder = new CityViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (CityViewHolder) convertView.getTag();
        }

        viewHolder.mCityName.setText(resultList.get(position));

        return convertView;
    }


    @Override
    public int getCount() {
        // Last item will be the footer
        int count = 0;
        if(resultList != null){
            count = resultList.size();
        }
        return count;
    }

    @Override
    public String getItem(int position) {
        if(resultList != null) {
            return resultList.get(position);
        }
        return null;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                Log.i("TAGFilterResults", "Content " + constraint);
                if (constraint != null) {
                    resultList = mPlaceAPI.autocomplete(constraint.toString());
                    Log.i("TAGFilterResults", "result " + resultList);

                    filterResults.values = resultList;
                    filterResults.count = resultList.size();
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                }
                else {
                    notifyDataSetInvalidated();
                }
            }
        };

        return filter;
    }


    public void updateList(){
       // resultList = IFilterPresenter.getCityList();
        notifyDataSetChanged();
    }


    class CityViewHolder{

        @BindView(R.id.popup_window_item_text)
        TextView mCityName;

        public CityViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
