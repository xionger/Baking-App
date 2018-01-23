package com.xiongxh.baking_app.recipedetail;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.data.bean.Step;
import com.xiongxh.baking_app.recipesteps.RecipeStepsActivity;
import com.xiongxh.baking_app.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.StepViewHolder>{

    private List<Step> mSteps = new ArrayList<>();

    final OnStepClickListener recipeClickListener;

    int currentPos;

    interface OnStepClickListener {

        void stepClicked(int stepId);
    }

    RecipeDetailAdapter(List<Step> steps, OnStepClickListener listener) {
        setSteps(steps);
        recipeClickListener = listener;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_step_detail, parent, false);

        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        if (mSteps == null){
            return;
        }

        holder.bindStep(mSteps.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (mSteps == null){
            return 0;
        }
        return mSteps.size();
    }

    @Override
    public long getItemId(int position) {
        return mSteps.get(position).getId();
    }

    void refreshSteps(List<Step> steps) {
        setSteps(steps);
        notifyDataSetChanged();
    }

    private void setSteps(@NonNull List<Step> steps) {
        this.mSteps = steps;
    }

    class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.list_step_layout)
        RelativeLayout stepItemLayout;
        @BindView(R.id.tv_step_short_description)
        TextView mShortDescriptionView;

        @BindView(R.id.list_step_video_icon) ImageView videoIcon;

        @BindColor(R.color.colorNormalBackground)
        int normalItemBackground;
        @BindColor(R.color.colorPrimaryLight)
        int currentItemBackground;


        private int mStepId;

        StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        void bindStep(@NonNull Step step, int bindPosition) {

            mStepId = step.getIdx();
            String description = step.getShortDescription();

            mShortDescriptionView.setText(String.format(Locale.US, "%d. %s", mStepId, description));

            String video = step.getVideoURL();

            if (video.isEmpty()) {
                videoIcon.setVisibility(View.INVISIBLE);
            } else {
                videoIcon.setVisibility(View.VISIBLE);
            }

            if (currentPos == bindPosition && UiUtils.isTablet()) {
                stepItemLayout.setBackgroundColor(currentItemBackground);
            }else {
                stepItemLayout.setBackgroundColor(normalItemBackground);
            }

        }

        @Override
        public void onClick(View view) {
            currentPos = mStepId;
            recipeClickListener.stepClicked(mStepId);
            notifyDataSetChanged();
        }
    }

}
