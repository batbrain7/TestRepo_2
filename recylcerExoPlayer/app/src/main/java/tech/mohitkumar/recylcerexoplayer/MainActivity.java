package tech.mohitkumar.recylcerexoplayer;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;

import java.util.ArrayList;

import tech.mohitkumar.recylcerexoplayer.Adapters.RecyclerVideoAdapter;
import tech.mohitkumar.recylcerexoplayer.Interface.VideoFinished;

public class MainActivity extends AppCompatActivity{

    Handler handler;
    Timeline.Window window;
    SimpleExoPlayerView simpleExoPlayerView;
    DataSource.Factory factory;
    SimpleExoPlayer player;
    DefaultTrackSelector trackSelector;

    RecyclerView recyclerView;
    RecyclerVideoAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<String> arraylist = new ArrayList<String>();

    boolean shouldAutoPlay;
    int playerWindow;
    long playerPosition;
    BandwidthMeter bandwidthMeter;
    DefaultExtractorsFactory defaultExtractorsFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arraylist.add("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8");
        arraylist.add("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8");
        arraylist.add("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8");
        arraylist.add("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8");
        arraylist.add("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerVideoAdapter(arraylist,getApplicationContext());
        recyclerView.setAdapter(adapter);

        adapter.setVideoFinished(new VideoFinished() {
            @Override
            public void onVideoFinished() {
                Log.d("TAG","GOT THE CALLBACk");

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public boolean top;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView
                        .getLayoutManager());

                if (top) {
                    int index = layoutManager.findFirstVisibleItemPosition();
                    recyclerView.smoothScrollToPosition(index);

                } else {
                    int index = layoutManager.findLastVisibleItemPosition();
                    recyclerView.smoothScrollToPosition(index);


                }


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    top = false;
                } else {
                    top = true;
                }

            }
        });
       // Intent intent = new Intent(MainActivity.this, CustomVideoPlayerActivity.class);
       // startActivity(intent);
    }

}
