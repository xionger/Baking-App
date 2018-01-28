package com.xiongxh.baking_app.recipesteps;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSource;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSourceFactory;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.xiongxh.baking_app.BakingApp;
import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class StepPageFragment extends Fragment{
//public class StepPageFragment extends Fragment implements Player.EventListener{

    private static final String DESCRIPTION_KEY = "DESCRIPTION_KEY";
    private static final String VIDEO_URL_KEY = "VIDEO_URL_KEY";
    private static final String IMAGE_URL_KEY = "IMAGE_URL_KEY";

    @BindView(R.id.cv_recipe_step_desc)
    CardView mDescriptionCardView;
    @BindView(R.id.iv_recipe_step_thumbnail)
    ImageView mStepThumbnailImageView;
    @BindView(R.id.tv_recipe_step_desc)
    TextView mDescriptionTextView;

    @BindView(R.id.pv_recipe_step)
    SimpleExoPlayerView mExoPlayerView;

    SimpleExoPlayer exoPlayer;
    private MediaSessionCompat mediaSession;
    private PlaybackStateCompat.Builder stateBuilder;

    Unbinder unbinder;

    public StepPageFragment(){}

    public static StepPageFragment newInstance(String desc, String videoUrl, String imgUrl){
        Bundle arguments = new Bundle();
        arguments.putString(DESCRIPTION_KEY, desc);
        arguments.putString(VIDEO_URL_KEY, videoUrl);
        arguments.putString(IMAGE_URL_KEY, imgUrl);

        StepPageFragment stepPageFragment = new StepPageFragment();
        stepPageFragment.setArguments(arguments);
        return stepPageFragment;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_step_page, container,false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        String description = getArguments().getString(DESCRIPTION_KEY);
        String imageUrl = getArguments().getString(IMAGE_URL_KEY);
        String videoUrl = getArguments().getString(VIDEO_URL_KEY);

        startPage(videoUrl, imageUrl, description);

//        //setViewVisibility(mDescriptionCardView, true);
//
//        int orientation = getResources().getConfiguration().orientation;
//
//        String description = getArguments().getString(DESCRIPTION_KEY);
//        Timber.d(DESCRIPTION_KEY + "=======================================:" + description);
//        mDescriptionTextView.setText(description);
//
//        String imgUrl = getArguments().getString(IMAGE_URL_KEY);
//        if (imgUrl != null && !imgUrl.isEmpty()){
//            Glide.get(getContext()).clearDiskCache();
//            Glide.with(this)
//                    .load(imgUrl)
//                    .into(mStepThumbnailImageView);
//
//            setViewVisibility(mStepThumbnailImageView, true);
//        }else{
//            setViewVisibility(mStepThumbnailImageView, false);
//        }
//
//        String videoUrl = getArguments().getString(VIDEO_URL_KEY);
//        Timber.d(VIDEO_URL_KEY + "++++++++++++++++++++++++++++++" + videoUrl);
//
//        if (videoUrl != null && !videoUrl.isEmpty()){
//            Timber.d("show exoplayer ...");
//            setViewVisibility(mExoPlayerView, true);
//            //initializeMediaSession();
//            //initializePlayer(Uri.parse(videoUrl));
//            startPlayer(videoUrl);
//
////            if (null != mExoPlayerView.getPlayer()) {
////                Timber.d("mExoPlayerView is not null ...");
////                mExoPlayerView.getPlayer().release();
////            } else{
////                Timber.d("mExoPlayerView is null ...");
////            }
//            if (orientation == Configuration.ORIENTATION_LANDSCAPE && !UiUtils.isTablet()) {
//                // Expand video, hide description, hide system UI
//                expandVideoView(mExoPlayerView);
//                setViewVisibility(mDescriptionCardView, false);
//                hideSystemUI();
//            }
//
//        }else{
//            Timber.d("hide exoplayer ...");
//            setViewVisibility(mExoPlayerView, false);
//        }

    }

    @Override
    public void onPause(){
        super.onPause();
        //releasePlayer();
        if (null != mExoPlayerView.getPlayer()) {
            mExoPlayerView.getPlayer().release();
        }
    }

//    @Override
//    public void onResume(){
//        super.onResume();
//    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    public void startPage(String videoUrl, String imageUrl, String desc){
        SimpleExoPlayer simpleExoPlayer = null;

        if (null != videoUrl && !videoUrl.isEmpty()) {
            simpleExoPlayer = ExoPlayerFactory
                    .newSimpleInstance(BakingApp.get(), new DefaultTrackSelector());

            Uri uri = Uri.parse(videoUrl);

            Timber.d("uri: " + uri);

            String userAgent = Util.getUserAgent(BakingApp.get(), "Recipe Step");

            Timber.d("useragent: " + userAgent);

            OkHttpDataSource.Factory factory =
                    new OkHttpDataSourceFactory(BakingApp.get().client, userAgent, null);

            MediaSource mediaSource =
                    new ExtractorMediaSource(uri, factory, new DefaultExtractorsFactory(), null, null);

            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(true);
        }

        if (simpleExoPlayer == null){
            Timber.d("simpleExoPlayer is null ...");
        } else{
            Timber.d("simpleExoPlayer is not null ...");
        }

        showPage(simpleExoPlayer, imageUrl, desc);
    }

    public void showPage(SimpleExoPlayer player, String imageUrl, String description) {
        if (null != mExoPlayerView.getPlayer()) {
            Timber.d("mPlayerView is not null ...");
            mExoPlayerView.getPlayer().release();
        } else {
            Timber.d("mPlayerView is null ...");
        }

        Timber.d(DESCRIPTION_KEY + "=======================================:" + description);
        mDescriptionTextView.setText(description);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.get(getContext()).clearDiskCache();
            Glide.with(this)
                    .load(imageUrl)
                    .into(mStepThumbnailImageView);

            setViewVisibility(mStepThumbnailImageView, true);
        } else {
            setViewVisibility(mStepThumbnailImageView, false);
        }

        if (null == player) {
            mExoPlayerView.setVisibility(View.GONE);
        } else {
            mExoPlayerView.setVisibility(View.VISIBLE);
            mExoPlayerView.setPlayer(player);

            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE && !UiUtils.isTablet()) {
                // Expand video, hide description, hide system UI
                expandVideoView(mExoPlayerView);
                setViewVisibility(mDescriptionCardView, false);
                hideSystemUI();
            }
        }
    }
/*

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if ((playbackState == Player.STATE_READY) && playWhenReady) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, exoPlayer.getCurrentPosition(), 1f);
        } else if ((playbackState == Player.STATE_READY)) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PAUSED, exoPlayer.getCurrentPosition(), 1f);
        }
        mediaSession.setPlaybackState(stateBuilder.build());
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }

    // EXO PLAYER METHODS

    private void initializeMediaSession() {

        mediaSession = new MediaSessionCompat(getContext(), "StepPageFragment");

        mediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        mediaSession.setMediaButtonReceiver(null);
        stateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mediaSession.setPlaybackState(stateBuilder.build());
        mediaSession.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPlay() {
                exoPlayer.setPlayWhenReady(true);
            }

            @Override
            public void onPause() {
                exoPlayer.setPlayWhenReady(false);
            }

            @Override
            public void onSkipToPrevious() {
                exoPlayer.seekTo(0);
            }
        });
        mediaSession.setActive(true);
    }

    private void initializePlayer(Uri mediaUri) {
        if (exoPlayer == null) {

            TrackSelector trackSelector = new DefaultTrackSelector();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);
            mExoPlayerView.setPlayer(exoPlayer);
            exoPlayer.addListener(this);

            String userAgent = Util.getUserAgent(getContext(), "StepVideo");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        }
    }


    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }

        if (mediaSession != null) {
            mediaSession.setActive(false);
        }
    }
*/

    private void setViewVisibility(View view, boolean isShown){
        if (isShown){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.INVISIBLE);
        }
    }

    private void expandVideoView(SimpleExoPlayerView exoPlayer) {
        exoPlayer.getLayoutParams().height = LayoutParams.MATCH_PARENT;
        exoPlayer.getLayoutParams().width = LayoutParams.MATCH_PARENT;
    }

    private void hideSystemUI() {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }
}
