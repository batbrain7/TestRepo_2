package com.example.mohitkumar.listasynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] movies = {"DEADPOOL","STAR WARS: THE FORCE AWAKENS","BATMAN V SUPERMAN: DAWN OF JUSTICE","THE JUNGLE BOOK",
            "CAPTAIN AMERICA: CIVIL WAR","SUICIDE SQUAD","MS DHONI: THE UNTOLD STORY","DOCTOR STRANGE","ASSASSIN'S CREED",
            "ROGUE ONE: A STAR WARS STORY","X MEN: APOCALYPSE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new ArrayList<String>()));
        new Mytask().execute();
    }

    class Mytask extends AsyncTask<Void,String,String>
    {
        ProgressBar progressBar;
        ArrayAdapter<String> adapter;
        int count;
        @Override
        protected String doInBackground(Void... params) {
            for(String name : movies)
            {
                publishProgress(name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "All items were added Successfully.....";
        }

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) listView.getAdapter();
            progressBar = (ProgressBar)findViewById(R.id.prog_bar);
            progressBar.setProgress(0);
            progressBar.setMax(13);
            progressBar.setVisibility(View.VISIBLE);
            count = 0;
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            count++;
            progressBar.setProgress(count);
        }
    }
}
