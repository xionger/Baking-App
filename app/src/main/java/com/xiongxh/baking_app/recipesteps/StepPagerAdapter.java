package com.xiongxh.baking_app.recipesteps;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.data.bean.Step;

import java.util.List;
import java.util.Locale;

import timber.log.Timber;

public class StepPagerAdapter extends FragmentPagerAdapter{
    private List<Step> mSteps;
    private final String mStepLabel;


    StepPagerAdapter(FragmentManager fragmentManager, List<Step> steps, Context context){
        super(fragmentManager);
        setSteps(steps);
        mStepLabel = context.getResources().getString(R.string.recipe_step_label);
    }

    @Override
    public Fragment getItem(int position) {
        Timber.d("StepPageFragment position: " + position);
        Timber.d("Step description: " + mSteps.get(position).getDescription());
        return StepPageFragment.newInstance(
                mSteps.get(position).getDescription(),
                mSteps.get(position).getVideoURL(),
                mSteps.get(position).getThumbnailURL()
        );
    }

    @Override
    public int getCount() {
        return mSteps.size();
    }

    public void setSteps(@NonNull List<Step> steps){
        Timber.d("setSteps, number of steps: " + steps.size());
        this.mSteps = steps;
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position){
        Timber.d("Entering CharSequence, position: " + position);
        return String.format(Locale.US, mStepLabel, position);
    }
}
