package tech.mohitkumar.recylcerexoplayer;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.Toast;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;

import java.util.ArrayList;

import tech.mohitkumar.recylcerexoplayer.Adapters.RecyclerVideoAdapter;

public class MainActivity extends AppCompatActivity{

    Handler handler;
    Timeline.Window window;
    SimpleExoPlayerView simpleExoPlayerView;
    DataSource.Factory factory;
    SimpleExoPlayer player;
    DefaultTrackSelector trackSelector;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
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

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                int visibleItemCount = recyclerView.getChildCount();
//                int totalItemCount = layoutManager.getItemCount();
//                Log.d("VIS_TOTAL",Integer.toString(visibleItemCount));
//
//                if (dy > 0) {
//                    // Scrolling up
//                   // Log.d("Current",Integer.toString(recyclerView.getVerticalScrollbarPosition()));
//                    //recyclerView.getLayoutManager().scrollToPosition(visibleItemCount);
//                    Log.d("VISIBLE",Integer.toString(recyclerView.getChildCount()));
//                   // layoutManager.scrollToPosition(recyclerView.getChildCount() + 1);
//                    recyclerView.smoothScrollToPosition(recyclerView.getChildCount() + 1);
//                   // recyclerView.stopScroll();
//                   // Toast.makeText(getApplicationContext(),"Scrolled up",Toast.LENGTH_SHORT).show();
//                   // Log.d("NEXT",Integer.toString(recyclerView.getVerticalScrollbarPosition() + 1));
//
//                } else if(dy < 0) {
//                    // Scrolling down
//                   // Toast.makeText(getApplicationContext(),"Scrolled down",Toast.LENGTH_SHORT).show();
//                   // Log.d("Current",Integer.toString(recyclerView.getVerticalScrollbarPosition()));
//                    //recyclerView.getLayoutManager().scrollToPosition(recyclerView.getChildCount() - 1);
//                    Log.d("VISIBLE",Integer.toString(recyclerView.getChildCount()));
//                   recyclerView.smoothScrollToPosition(recyclerView.getChildCount() - 1);
//                  //  recyclerView.stopScroll();
//                    // Log.d("Next",Integer.toString(recyclerView.getVerticalScrollbarPosition() - 1));
//                }
//            }
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
//                    // Do something
//                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                    // Do something
//                } else {
//                    // Do something
//                }
//            }
//        });

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
