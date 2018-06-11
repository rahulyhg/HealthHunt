package in.healthhunt.view.tagView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.model.tags.TagItem;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.tagPresenter.ITagPresenter;
import in.healthhunt.presenter.tagPresenter.TagPresenterImp;
import in.healthhunt.view.BaseActivity;
import in.healthhunt.view.onBoardingView.OnBoardingActivity;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TagActivity extends BaseActivity implements ITagView, TagAdapter.OnClickListener{

    @BindView(R.id.spinner)
    public Spinner mSpinner;

    @BindView(R.id.search_view)
    public AutoCompleteTextView mSearchView;

    @BindView(R.id.done)
    public TextView mDone;

    private String[] spinnerItems = {"English", "Hindi", "Punjabi"};

    private ITagPresenter ITagPresenter;

    private TagFragment mTagFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);
        ButterKnife.bind(this);

        addSpinnerAdapter();
        ITagPresenter = new TagPresenterImp(getApplicationContext(), this);
        ITagPresenter.loadFragment(TagFragment.class.getSimpleName(), null);
        //addTagAdapter();
        //mTagPresenterImp.fetchTags();
    }

    private void addSpinnerAdapter() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_text_item, spinnerItems);
        mSpinner.setAdapter(arrayAdapter);
    }


    @OnClick(R.id.done)
    void onDoneClick(){
        List<TagItem> itemList = ITagPresenter.getSelectedTagList();
        if(itemList.size() < 5) {
            Toast.makeText(getApplicationContext(), getString(R.string.select_at_least_5_tags),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        ITagPresenter.storeSelectedTags();
        Intent intent = new Intent(getApplicationContext(), OnBoardingActivity.class);
        startActivity(intent);
        finish();
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
        mTagFragment.updateAdapter();
        setSearchAdapter();
    }

    private void setSearchAdapter() {

        List<TagItem> tagItemList = ITagPresenter.getTagList();
        List<String> searchList = new ArrayList<String>();

        for(TagItem item: tagItemList){
            searchList.add(item.getName());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.popup_window_item, searchList);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        width = width - HealthHuntUtility.dpToPx(18, getApplicationContext());
        mSearchView.setDropDownWidth(width);
        mSearchView.setDropDownHorizontalOffset(HealthHuntUtility.dpToPx(1, getApplicationContext()));
        mSearchView.setDropDownVerticalOffset(HealthHuntUtility.dpToPx(1, getApplicationContext()));
        mSearchView.setAdapter(arrayAdapter);

        mSearchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                String selectedText = mSearchView.getText().toString();
                for(TagItem fullTagItem: ITagPresenter.getTagList()){
                    if(fullTagItem.getName().equalsIgnoreCase(selectedText)){
                        fullTagItem.setPressed(!fullTagItem.isPressed());

                        boolean isPressed = fullTagItem.isPressed();
                        if(isPressed){
                            ITagPresenter.addTag(fullTagItem);
                        }
                        else {
                            ITagPresenter.removeTag(fullTagItem);
                        }
                        break;
                    }
                }
                updateAdapter();
            }
        });

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

    @Override
    public TagViewHolder createTagViewHolder(View view) {
        return new TagViewHolder(view, ITagPresenter, this);
    }

    @Override
    public void loadFragment(String fragmentName, Bundle bundle) {
        mTagFragment = new TagFragment();
        showFragment(mTagFragment);
    }

    @Override
    public ITagPresenter getPresenter() {
        return ITagPresenter;
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.tag_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(int position) {
        List<TagItem> itemList = ITagPresenter.getSelectedTagList();
        if(itemList != null && !itemList.isEmpty()){
            mDone.setTextColor(ContextCompat.getColor(this, R.color.hh_edittext_text_color));
        }
        else {
            mDone.setTextColor(ContextCompat.getColor(this, R.color.hh_grey_dark));
        }
    }
}
