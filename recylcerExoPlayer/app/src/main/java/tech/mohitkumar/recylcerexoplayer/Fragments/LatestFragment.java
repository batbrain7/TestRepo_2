package tech.mohitkumar.recylcerexoplayer.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


import tech.mohitkumar.recylcerexoplayer.Adapters.RecyclerViewAdapter;
import tech.mohitkumar.recylcerexoplayer.Models.CardViewData;
import tech.mohitkumar.recylcerexoplayer.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class LatestFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    CardViewData cardViewData;
    ArrayList<CardViewData> arrayList = new ArrayList<CardViewData>();

    public LatestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_latest, container, false);

        for(int i=0;i<5;i++) {
            if(i%2 == 0)
                cardViewData = new CardViewData("http://playertest.longtailvideo.com/adaptive/bbbfull/bbbfull.m3u8","","","","","","","","");
            else
                cardViewData = new CardViewData("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8","","","","","","","","");
            arrayList.add(cardViewData);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view1);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(arrayList,getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

}
