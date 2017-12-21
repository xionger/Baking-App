package com.xiongxh.baking_app.recipedetail;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.StepViewHolder>{

    private List<Step> mSteps = new ArrayList<>();

    //final OnStepClickListener recipeClickListener;

    int currentPos;
/*
    RecipeDetailAdapter(List<Step> steps, OnStepClickListener listener) {
        setSteps(steps);
        recipeClickListener = listener;
    }
    */

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe_detail, parent, false);

        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        holder.bindStep(mSteps.get(position), position);
    }

    @Override
    public int getItemCount() {
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

    class StepViewHolder extends RecyclerView.ViewHolder {// implements View.OnClickListener {

        //@BindView(R.id.list_step_layout)
        //RelativeLayout stepItemLayout;
        @BindView(R.id.tv_step_short_description)
        TextView mShortDescriptionView;
        //@BindView(R.id.list_step_video_icon) ImageView videoIcon;
        /*
        @BindColor(R.color.colorGrayBackground)
        int normalItemBackground;
        @BindColor(R.color.colorPrimaryLight)
        int currentItemBackground;
        */

        //private int mSepId;

        StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            //itemView.setOnClickListener(this);
        }

        void bindStep(@NonNull Step step, int bindPosition) {

            int stepId = step.getId();
            String description = step.getShortDescription();

            mShortDescriptionView.setText(String.format(Locale.US, "%d. %s", stepId, description));

            /*
            String video = step.getVideoURL();

            if (video.isEmpty()) {
                videoIcon.setVisibility(View.INVISIBLE);
            } else {
                videoIcon.setVisibility(View.VISIBLE);
            }

            if (currentPos == bindPosition) {
                stepItemLayout.setBackgroundColor(currentItemBackground);
            } else {
                stepItemLayout.setBackgroundColor(normalItemBackground);
            }
            */
        }
/*
        @Override
        public void onClick(View v) {
            currentPos = currentId;
            recipeClickListener.stepClicked(currentId);
            notifyDataSetChanged();
        }
        */
    }
/*
    interface OnStepClickListener {

        void stepClicked(int stepId);
    }
    */
}
