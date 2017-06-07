package tech.mohitkumar.recylcerexoplayer.Adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

import tech.mohitkumar.recylcerexoplayer.CustomView.CustomExoPlayerView;
import tech.mohitkumar.recylcerexoplayer.CustomView.CustomPlaybackControlView;
import tech.mohitkumar.recylcerexoplayer.R;

/**
 * Created by mohitkumar on 06/06/17.
 */

public class RecyclerVideoAdapter extends RecyclerView.Adapter<RecyclerVideoAdapter.RecyclerViewHolder> {

    ArrayList<String> list = new ArrayList<String>();
    Context context;

    public RecyclerVideoAdapter(ArrayList<String> list,Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,list,context);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {

        String s = holder.list.get(position);

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
        holder.player.setPlayWhenReady(shouldAutoPlay);
        MediaSource mediaSource = new HlsMediaSource(Uri.parse(s),mediaDataSourceFactory, mainHandler, null);
        Format textFormat = Format.createTextSampleFormat(null, MimeTypes.APPLICATION_SUBRIP,
                        null, Format.NO_VALUE, Format.NO_VALUE, "en", null);
        MediaSource subtitleSource = new SingleSampleMediaSource(null, mediaDataSourceFactory, textFormat, C.TIME_UNSET);
        MergingMediaSource mergedSource = new MergingMediaSource(mediaSource, subtitleSource);
        holder.player.prepare(mergedSource);

        holder.itemView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                holder.player.setPlayWhenReady(shouldAutoPlay);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                CustomExoPlayerView cust = (CustomExoPlayerView) v.findViewById(R.id.player_view);
                cust.controller.dispatchKeyEvent(KeyEvent.KEYCODE_MEDIA_PAUSE);
                Log.d("Updated the button","P");
            }
        });
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        Context context;
        ArrayList<String> list = new ArrayList<String>();
        CustomExoPlayerView customExoPlayerView;
        SimpleExoPlayer player;
        CustomPlaybackControlView customPlaybackControlView;

        public RecyclerViewHolder(View itemView,ArrayList<String> list,Context context) {
            super(itemView);
            this.context = context;
            this.list = list;
            this.customExoPlayerView = (CustomExoPlayerView) itemView.findViewById(R.id.player_view);
        }
    }
}
