package tech.mohitkumar.recylcerexoplayer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

import tech.mohitkumar.recylcerexoplayer.CustomView.CustomExoPlayerView;
import tech.mohitkumar.recylcerexoplayer.CustomView.CustomPlaybackControlView;
import tech.mohitkumar.recylcerexoplayer.MainActivity;
import tech.mohitkumar.recylcerexoplayer.Models.CardDataProvider;
import tech.mohitkumar.recylcerexoplayer.R;

/**
 * Created by mohitkumar on 10/06/17.
 */

public class RecyclerSimpleAdapter extends RecyclerView.Adapter<RecyclerSimpleAdapter.RecyclerViewHolder> {

    Context context;
    ArrayList<CardDataProvider> arrayList = new ArrayList<CardDataProvider>();

    public RecyclerSimpleAdapter(ArrayList<CardDataProvider> arrayList, Context applicationContext) {
        this.context = applicationContext;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,context,arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        final CardDataProvider s = holder.list.get(position);

        //videoFinished.onInteraction(position);

        holder.comments.setText(s.getComments());
        holder.name.setText(s.getName());
        holder.likes.setText(s.getLikes());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        Handler mainHandler;
        Timeline.Window window;
        DataSource.Factory mediaDataSourceFactory;
        DefaultTrackSelector trackSelector;
        final boolean shouldAutoPlay;
        int playerWindow;
        long playerPosition;
        BandwidthMeter bandwidthMeter;
        DefaultExtractorsFactory extractorsFactory;

        shouldAutoPlay = true;
        bandwidthMeter = new DefaultBandwidthMeter();
        mediaDataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "mediaPlayerSample"), (TransferListener<? super DataSource>) bandwidthMeter);
        mainHandler = new Handler();
        window = new Timeline.Window();

        holder.customExoPlayerView.requestFocus();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(mainHandler, videoTrackSelectionFactory);
        holder.player = ExoPlayerFactory.newSimpleInstance(context, trackSelector, new DefaultLoadControl(), null, false);
        holder.customExoPlayerView.setPlayer(holder.player);
       // holder.player.setPlayWhenReady(shouldAutoPlay);
        MediaSource mediaSource = new HlsMediaSource(Uri.parse(s.getLink()),mediaDataSourceFactory, mainHandler, null);
        holder.player.prepare(mediaSource);

        Log.d("INEND",Integer.toString(holder.getAdapterPosition()));


        holder.player.addListener(new ExoPlayer.EventListener() {
            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == ExoPlayer.STATE_ENDED) {
                    Log.d("ENTERED","Entered");
                   // videoFinished.onVideoFinished(position);
                }
            }

            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity() {

            }
        });

//        holder.itemView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
//            @Override
//            public void onViewAttachedToWindow(View v) {
//                holder.player.setPlayWhenReady(shouldAutoPlay);
//            }
//
//            @Override
//            public void onViewDetachedFromWindow(View v) {
//                CustomExoPlayerView cust = (CustomExoPlayerView) v.findViewById(R.id.player_view);
//                cust.controller.dispatchKeyEvent(KeyEvent.KEYCODE_MEDIA_PAUSE);
//                Log.d("Updated the button","P");
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView heart;
        TextView likes,comments;
        Context context;
        ArrayList<CardDataProvider> list = new ArrayList<CardDataProvider>();
        CustomExoPlayerView customExoPlayerView;
        SimpleExoPlayer player;
        CustomPlaybackControlView customPlaybackControlView;

        public RecyclerViewHolder(View itemView,Context context,ArrayList<CardDataProvider> list) {
            super(itemView);
            this.list = list;
            this.context = context;
            this.likes = (TextView) itemView.findViewById(R.id.no_likes);
            this.comments = (TextView) itemView.findViewById(R.id.comment_id);
            this.customExoPlayerView = (CustomExoPlayerView) itemView.findViewById(R.id.player_view_card);
            this.name = (TextView) itemView.findViewById(R.id.name_card);
            this.heart = (ImageView) itemView.findViewById(R.id.heart);
        }
    }
}
