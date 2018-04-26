package in.healthhunt.view.tagView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.model.beans.Tag;
import in.healthhunt.presenter.tagPresenter.TagPresenterImp;
import in.healthhunt.view.BaseActivity;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TagActivity extends BaseActivity {

    @BindView(R.id.tags_recycler_list)
    public RecyclerView mRecyclerView;

    @BindView(R.id.select_all)
    public TextView mSelectAll;

    @BindView(R.id.spinner)
    public Spinner mSpinner;

    private String[] spinnerItems = {"English", "Hindi", "Punjabi"};

    private TagPresenterImp mTagPresenterImp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);
        ButterKnife.bind(this);

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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        //gridLayoutManager.setS
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(tagAdapter);
    }

    private void addSpinnerAdapter() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems);
        mSpinner.setAdapter(arrayAdapter);
    }


    @OnClick(R.id.done)
    void onDoneClick(){
         // To Do
    }


}
