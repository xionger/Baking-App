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

public class StepPageFragment extends Fragment implements Player.EventListener{

    private static final String DESCRIPTION_KEY = "DESCRIPTION_KEY";
    private static final String VIDEO_URL_KEY = "VIDEO_URL_KEY";
    private static final String IMAGE_URL_KEY = "IMAGE_URL_KEY";

    private static final String PLAY_READY_KEY = "PLAY_READY_KEY";
    private static final String PLAY_POSITION_KEY = "PLAY_POSITION_KEY";

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

    String videoUrl;

    boolean mPlayReady;
    long mPlayPosition;


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

        if (savedInstanceState != null){
            mPlayReady = savedInstanceState.getBoolean(PLAY_READY_KEY);
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        String description = getArguments().getString(DESCRIPTION_KEY);
        String imageUrl = getArguments().getString(IMAGE_URL_KEY);

        videoUrl = getArguments().getString(VIDEO_URL_KEY);

        mDescriptionTextView.setText(description);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(this)
                    .load(imageUrl)
                    .into(mStepThumbnailImageView);

            setViewVisibility(mStepThumbnailImageView, true);
        } else {
            setViewVisibility(mStepThumbnailImageView, false);
        }

        int orientation = getResources().getConfiguration().orientation;

        if (videoUrl != null && !videoUrl.isEmpty()) {

            if (savedInstanceState != null) {
                mPlayPosition = savedInstanceState.getLong(PLAY_POSITION_KEY);
            }

            setViewVisibility(mExoPlayerView, true);
            initializeMediaSession();
            initializePlayer(Uri.parse(videoUrl));

            if (orientation == Configuration.ORIENTATION_LANDSCAPE && !UiUtils.isTablet()) {
                expandVideoView(mExoPlayerView);
                hideSystemUI();
            }

        } else {
            setViewVisibility(mExoPlayerView, false);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(PLAY_POSITION_KEY, mPlayPosition);
        outState.putBoolean(PLAY_READY_KEY, mPlayReady);
    }

    private void initializeMediaSession() {

        mediaSession = new MediaSessionCompat(getContext(), "RecipeStepSinglePageFragment");

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
                super.onPlay();
                exoPlayer.setPlayWhenReady(true);
            }

            @Override
            public void onPause() {
                super.onPause();
                exoPlayer.setPlayWhenReady(false);
            }

            @Override
            public void onSkipToPrevious() {
                super.onSkipToPrevious();
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

    private void setViewVisibility(View view, boolean isShown){
        if (isShown){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.INVISIBLE);
        }
    }

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
        if (mediaSession != null) {
            mediaSession.setActive(false);
        }

        unbinder.unbind();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (exoPlayer != null) {
            mPlayPosition = exoPlayer.getCurrentPosition();
            mPlayReady = exoPlayer.getPlayWhenReady();
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(mPlayReady);
            exoPlayer.seekTo(mPlayPosition);
        } else if (videoUrl != null && !videoUrl.isEmpty()){
            initializeMediaSession();
            initializePlayer(Uri.parse(videoUrl));
            exoPlayer.setPlayWhenReady(mPlayReady);
            exoPlayer.seekTo(mPlayPosition);
        }
    }
}
