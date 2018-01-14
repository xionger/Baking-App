package com.xiongxh.baking_app.recipesteps;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.data.bean.Step;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;


public class RecipeStepsFragment extends Fragment implements RecipeStepsContract.View {

    private static final String RECIPE_ID_KEY = "RECIPIE_ID";
    private static final String STEP_ID_KEY = "STEP_ID";
    private RecipeStepsContract.Presenter mRecipeStepsPresenter;
    private Unbinder unbinder;

    @BindView(R.id.vp_step_recipe)
    SimpleExoPlayerView mPlayView;
    @BindView(R.id.tv_step_recipe)
    TextView mStepTextView;
    @BindView(R.id.tv_step_number)
    TextView mStepNumberTextView;

    @BindView(R.id.btn_backward)
    FloatingActionButton mBackButton;
    @BindView(R.id.btn_forward)
    FloatingActionButton mNextButton;

    public RecipeStepsFragment() {
        // Required empty public constructor
    }

    public static RecipeStepsFragment newInstance(int recipeId, int stepId){
        Bundle args = new Bundle();
        RecipeStepsFragment fragment = new RecipeStepsFragment();
        args.putInt(RECIPE_ID_KEY, recipeId);
        args.putInt(STEP_ID_KEY, stepId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        int stepId;

        if (null != savedInstanceState){
            stepId = savedInstanceState.getInt(STEP_ID_KEY);
            Timber.d("savedInstanceState is null, stepId: " + stepId);
        }else {
            stepId = getArguments().getInt(STEP_ID_KEY);
            Timber.d("savedInstanceState is not null, stepId: " + stepId);
        }

        int recipeId = getArguments().getInt(RECIPE_ID_KEY);
        Timber.d("recipeId: " + recipeId);

        mRecipeStepsPresenter = new RecipeStepsPresenter(this, recipeId, stepId);

        if (stepId == 0){

        }
        mBackButton.setOnClickListener(v -> mRecipeStepsPresenter.showPreviousStep());
        mNextButton.setOnClickListener(v -> mRecipeStepsPresenter.showNextStep());

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putInt(STEP_ID_KEY, mRecipeStepsPresenter.getCurrentIndex());
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
        mPlayView.getPlayer().release();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showStep(Step step, @NonNull SimpleExoPlayer player){
        if (null != mPlayView.getPlayer()) {
            Timber.d("mPlayerView is not null ...");
            mPlayView.getPlayer().release();
        } else{
            Timber.d("mPlayerView is null ...");
        }
        mStepTextView.setText(step.getDescription());

        if (null == player){
            mPlayView.setVisibility(View.GONE);
        }else{
            mPlayView.setVisibility(View.VISIBLE);
            mPlayView.setPlayer(player);
        }
    }

    @Override
    public void setStepNumber(String stepNumber){
        mStepNumberTextView.setText(stepNumber);
    }

    @Override
    public void setBackButton(boolean existed){
        mBackButton.setVisibility(existed ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void setNextButton(boolean existed) {
        mNextButton.setVisibility(existed ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void showRecipeName(String recipeName) {
        getActivity().setTitle(recipeName);
    }

    @Override
    public void showErrorMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
