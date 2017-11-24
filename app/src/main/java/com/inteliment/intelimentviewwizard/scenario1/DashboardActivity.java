package com.inteliment.intelimentviewwizard.scenario1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.inteliment.intelimentviewwizard.R;
import com.inteliment.intelimentviewwizard.scenario2.NavigationActivity;
import com.itsronald.widget.ViewPagerIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivity extends AppCompatActivity implements DashboardContract {

    private static final String SELECTED_TEXT = "SELECTED_TEXT";
    private static final String SELECTED_COLOR = "SELECTED_COLOR";

    @BindView(R.id.list_item) RecyclerView mTripList;
    private DashboardPresenter mDashboardPresenter;
    private ItemListAdapter mAdapter;
    private int mSelectedColor = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mDashboardPresenter = new DashboardPresenter(this);
        intializeItemListRecycleView();
        initializeViewPager();

        if (savedInstanceState != null) {
            ((TextView)findViewById(R.id.tvSelectedItem))
                    .setText(savedInstanceState
                            .getString(SELECTED_TEXT, getString(R.string.not_selected)));

            mSelectedColor = savedInstanceState.getInt(SELECTED_COLOR, -1);
            if (mSelectedColor != -1) {
                findViewById(R.id.ll_btn_container).setBackgroundColor(mSelectedColor);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(DashboardActivity.this, NavigationActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /**
     * Initialize viewpager
     */
    private void initializeViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        final ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewPager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM;

        final ViewPagerIndicator indicator = new ViewPagerIndicator(this);
        indicator.setSelectedDotColor(ContextCompat.getColor(this, R.color.colorAccent));
        viewPager.addView(indicator, layoutParams);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDashboardPresenter.inflateList();
    }

    /**
     * Initialize recyclerview
     */
    private void intializeItemListRecycleView() {
        mTripList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(false);
        layoutManager.setStackFromEnd(true);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mTripList.setLayoutManager(layoutManager);
    }


    /**
     * Inflate recyclerview
     * @param items
     */
    @Override
    public void showItemList(List<String> items) {
        mAdapter = new ItemListAdapter(DashboardActivity.this, items);
        mTripList.setAdapter(mAdapter);
        mTripList.scrollToPosition(0);
    }

    /**
     * Update recyclerview click
     * @param s
     */
    public void updateItem(String s){
        ((TextView)findViewById(R.id.tvSelectedItem)).setText(s);
    }

    /**
     * Handle button click
     * @param view
     */
    @OnClick({R.id.btnBlue, R.id.btnGreen, R.id.btnRed})
    public void onRedClicked(View view) {
        switch (view.getId()){
            case R.id.btnBlue:
                findViewById(R.id.ll_btn_container).setBackgroundColor(Color.BLUE);
                mSelectedColor = Color.BLUE;
                break;
            case R.id.btnGreen:
                findViewById(R.id.ll_btn_container).setBackgroundColor(Color.GREEN);
                mSelectedColor = Color.GREEN;
                break;
            case R.id.btnRed:
                findViewById(R.id.ll_btn_container).setBackgroundColor(Color.RED);
                mSelectedColor = Color.RED;
                break;
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SELECTED_TEXT,
                ((TextView)findViewById(R.id.tvSelectedItem)).getText().toString());
        outState.putInt(SELECTED_COLOR, mSelectedColor);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDashboardPresenter.removeView();
    }
}
