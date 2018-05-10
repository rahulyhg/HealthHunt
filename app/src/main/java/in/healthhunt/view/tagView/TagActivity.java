package in.healthhunt.view.tagView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.model.beans.SpaceDecoration;
import in.healthhunt.model.tags.TagItem;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.tagPresenter.TagPresenterImp;
import in.healthhunt.view.BaseActivity;
import in.healthhunt.view.onBoardingView.OnBoardingActivity;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TagActivity extends BaseActivity implements ITagView{

    @BindView(R.id.tags_recycler_list)
    public RecyclerView mRecyclerView;

    @BindView(R.id.select_all)
    public TextView mSelectAll;

    @BindView(R.id.spinner)
    public Spinner mSpinner;

    @BindView(R.id.done)
    public TextView mDone;

    private String[] spinnerItems = {"English", "Hindi", "Punjabi"};

    private TagPresenterImp mTagPresenterImp;

    private ProgressDialog mProgress;

    private TagAdapter mTagAdapter;

    private boolean isSelectAll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);
        ButterKnife.bind(this);

        mProgress = new ProgressDialog(this);
        mProgress.setIndeterminate(true);
        mProgress.setMessage(getResources().getString(R.string.please_wait));

        addSpinnerAdapter();
        mTagPresenterImp = new TagPresenterImp(getApplicationContext(), this);
        addTagAdapter();
        mTagPresenterImp.fetchTags();
    }

    private void addTagAdapter() {
        mTagAdapter = new TagAdapter(getApplicationContext(), mTagPresenterImp);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addItemDecoration(new SpaceDecoration(HealthHuntUtility.
                dpToPx(8, getApplicationContext()), SpaceDecoration.GRID));
        mRecyclerView.setAdapter(mTagAdapter);
    }

    private void addSpinnerAdapter() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems);
        mSpinner.setAdapter(arrayAdapter);
    }


    @OnClick(R.id.done)
    void onDoneClick(){
         List<TagItem> itemList = mTagPresenterImp.getSelectedTagList();
         if(itemList.size() < 5) {
             Toast.makeText(getApplicationContext(), getString(R.string.select_at_least_5_tags),
                     Toast.LENGTH_SHORT).show();
             return;
         }

        mTagPresenterImp.storeSelectedTags();
        Intent intent = new Intent(getApplicationContext(), OnBoardingActivity.class);
        startActivity(intent);
    }


    @Override
    public void onShowProgress() {
        mProgress.show();
    }

    @Override
    public void onHideProgress() {
        mProgress.dismiss();
    }

    @Override
    public void updateAdapter() {
        /*if(mTagAdapter == null){
            addTagAdapter();
        }
        else */{
            mTagAdapter.notifyDataSetChanged();
        }
    }

    /*@Override
    public void setTags(List<TagItem> list) {

        Log.i("TAG123", "List = " + list);
        if(list != null && !list.isEmpty()){
            mTags.addAll(list);
        }

        if(mTagAdapter == null){
            addTagAdapter();
        }
        mTagAdapter.updateTagList(list);
        mTagAdapter.notifyDataSetChanged();
    }*/

    @Override
    public void showAlert(String msg) {
        final Dialog dialog = new Dialog(this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alertdialog_view);
        //dialog.

        TextView message = dialog.findViewById(R.id.alert_message);
        message.setText(msg);

        TextView title = dialog.findViewById(R.id.alert_title);
        title.setVisibility(View.GONE);

        Button okButton = dialog.findViewById(R.id.action_button);
        okButton.setText(android.R.string.ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    @OnClick(R.id.select_all)
    void onSelectAll(){
        isSelectAll = !isSelectAll;
        if(isSelectAll) {
            mTagPresenterImp.selectAll();
            mTagAdapter.setSelectAll(true);
        }
        else {
            mTagPresenterImp.unSelectAll();
            mTagAdapter.setSelectAll(false);
        }
    }
}
