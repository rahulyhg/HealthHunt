package in.healthhunt.view.tagView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.healthhunt.R;
import in.healthhunt.model.tags.TagItem;
import in.healthhunt.presenter.tagPresenter.ITagPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TagSearchAdapter extends ArrayAdapter implements Filterable {

    private ITagPresenter ITagPresenter;
    private Context mContext;
    private List<TagItem> mSearchItems;
    private List<TagItem> mTagItems;
    private ListFilter listFilter = new ListFilter();

    public TagSearchAdapter(Context context, int resource, ITagPresenter tagPresenter) {
        super(context, resource);
        mContext = context;
        ITagPresenter = tagPresenter;
    }

    @Override
    public int getCount() {
        int count = 0;
        if(mSearchItems != null){
            count = mSearchItems.size();
        }
        return count;
    }

    @Override
    public String getItem(int position) {
        if(mSearchItems != null && !mSearchItems.isEmpty()){
            return mSearchItems.get(position).getName();
        }
        return null;
    }

    public TagItem getTagItem(int pos){
        if(mSearchItems != null && !mSearchItems.isEmpty()){
            return mSearchItems.get(pos);
        }
        return null;
    }

    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.popup_window_item, parent, false);
        }

        TextView strName = (TextView) view.findViewById(R.id.popup_window_item_text);
        TagItem tagItem = mSearchItems.get(position);
        strName.setText(tagItem.getName());
        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return listFilter;
    }

    public class ListFilter extends Filter {
        private Object lock = new Object();

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            if (mTagItems == null) {
                synchronized (lock) {
                    mTagItems = new ArrayList<TagItem>(ITagPresenter.getTagList());
                }
            }

            if (prefix == null || prefix.length() == 0) {
                synchronized (lock) {
                    results.values = mTagItems;
                    results.count =  mTagItems.size();
                }
            } else {
                final String searchStrLowerCase = prefix.toString().toLowerCase();

                ArrayList<TagItem> matchValues = new ArrayList<TagItem>();

                for (TagItem tagItem : mTagItems) {
                    String name = tagItem.getName();
                    if (name.toLowerCase().startsWith(searchStrLowerCase)) {
                        matchValues.add(tagItem);
                    }
                }

                results.values = matchValues;
                results.count = matchValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                mSearchItems = (ArrayList<TagItem>)results.values;
            } else {
                mSearchItems = null;
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }
}