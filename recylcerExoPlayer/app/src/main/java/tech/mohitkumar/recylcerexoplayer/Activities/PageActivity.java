package tech.mohitkumar.recylcerexoplayer.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import tech.mohitkumar.recylcerexoplayer.Adapters.RecyclerSimpleAdapter;
import tech.mohitkumar.recylcerexoplayer.Adapters.RecyclerVideoAdapter;
import tech.mohitkumar.recylcerexoplayer.MainActivity;
import tech.mohitkumar.recylcerexoplayer.Models.CardDataProvider;
import tech.mohitkumar.recylcerexoplayer.R;

public class PageActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerSimpleAdapter adapter;
    RecyclerView.LayoutManager linearLayoutManager;

    String[] names = {"Bunny Video","Blender Foundation","Statistics","Bunny"};
    String[] likes = {"23","344","7","26"};
    String[] comments = {"1","23","","3"};
    String[] links = {"http://playertest.longtailvideo.com/adaptive/bbbfull/bbbfull.m3u8","https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8","https://d2zihajmogu5jn.cloudfront.net/bipbop-advanced/bipbop_16x9_variant.m3u8","http://playertest.longtailvideo.com/adaptive/bbbfull/bbbfull.m3u8"};

    ArrayList<CardDataProvider> arrayList = new ArrayList<CardDataProvider>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        int i=0;
        for(String a : names) {
            CardDataProvider cardDataProvider = new CardDataProvider(a,links[i],comments[i],likes[i]);
            arrayList.add(cardDataProvider);
            i++;
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);

        linearLayoutManager = new LinearLayoutManager(PageActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerSimpleAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(adapter);

    }
}
