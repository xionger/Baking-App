package com.xiongxh.baking_app.recipesteps;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.data.bean.Step;
import com.xiongxh.baking_app.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class RecipeStepsFragment extends Fragment implements RecipeStepsContract.View {

    private static final String RECIPE_ID_KEY = "RECIPIE_ID_KEY";
    private static final String STEP_ID_KEY = "STEP_ID_KEY";
    private RecipeStepsContract.Presenter mRecipeStepsPresenter;
    private Unbinder unbinder;

    private int mStepId;

    @BindView(R.id.recipe_step_page)
    ViewPager mStepPagerView;

    @BindView(R.id.recipe_step_tablayout)
    TabLayout mStepTabLayout;

    StepPagerAdapter mStepPagerAdapter;

    public RecipeStepsFragment() {
    }

    public static RecipeStepsFragment newInstance(int stepId){
        Bundle args = new Bundle();
        RecipeStepsFragment fragment = new RecipeStepsFragment();
        args.putInt(STEP_ID_KEY, stepId);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (null == savedInstanceState){
            mStepId = getArguments().getInt(STEP_ID_KEY);
            Timber.d("savedInstanceState is null, stepId: " + mStepId);
        }else {
            mStepId = savedInstanceState.getInt(STEP_ID_KEY);
            Timber.d("savedInstanceState is not null, stepId: " + mStepId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        mStepPagerAdapter =
                new StepPagerAdapter(getFragmentManager(),
                new ArrayList<>(0),
                getContext());

        mStepPagerView.setAdapter(mStepPagerAdapter);

        setupPagerViewListener();
        mStepTabLayout.setupWithViewPager(mStepPagerView);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE && !UiUtils.isTablet()){
            mStepTabLayout.setVisibility(View.GONE);
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putInt(STEP_ID_KEY, mStepId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume(){
        super.onResume();
        mRecipeStepsPresenter.subscribe(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        mRecipeStepsPresenter.unsubscribe();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showErrorMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(RecipeStepsContract.Presenter presenter){
        this.mRecipeStepsPresenter = presenter;
    }

    @Override
    public void showRecipeName(String recipeName) {
        if (!UiUtils.isTablet()) {
            getActivity().setTitle(recipeName);
        }
    }

    @Override
    public void showStepsInPager(List<Step> steps){
        Timber.d("Entering showStepsInPager()...");
        mStepPagerAdapter.setSteps(steps);
    }

    @Override
    public void moveToCurrentStepPage() {
        mStepPagerView.setCurrentItem(mStepId);
    }

    private void setupPagerViewListener() {
        Timber.d("Entering setupPagerViewListener() ...");
        mStepPagerView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                mStepId = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

}
