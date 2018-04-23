package in.healthhunt.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.healthhunt.R;
import in.healthhunt.model.beans.Tag;
import in.healthhunt.presenter.TagPresenterImp;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TagActivity extends BaseActivity {

    @BindView(R.id.tags_recycler_list)
    public RecyclerView mRecyclerView;

    @BindView(R.id.select_all)
    public TextView mSelectAll;

    @BindView(R.id.spinner_toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.spinner)
    public Spinner mSpinner;

    private String[] spinnerItems = {"English", "Hindi", "Punjabi"};

    private TagPresenterImp mTagPresenterImp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);
        ButterKnife.bind(this);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }
        addSpinnerAdapter();
        mTagPresenterImp = new TagPresenterImp(getApplicationContext());

        //Temp added code
        List<Tag> list = new ArrayList<Tag>();
        for(int i=0; i<10; i++){
            Tag tag = new Tag();
            tag.setmTag("Exercise");
            list.add(tag);
        }

        mTagPresenterImp.initTags(list);
        addTagAdapter();
    }

    private void addTagAdapter() {
        TagAdapter tagAdapter = new TagAdapter(mTagPresenterImp);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(tagAdapter);
    }

    private void addSpinnerAdapter() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems);
        mSpinner.setAdapter(arrayAdapter);
    }


}
