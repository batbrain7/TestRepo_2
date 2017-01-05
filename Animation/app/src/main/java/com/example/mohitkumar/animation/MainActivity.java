package com.example.mohitkumar.animation;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean b = false;
    ImageView imageView;
    int[] array = {R.drawable.shooter1,R.drawable.shooter2,R.drawable.shooter4,R.drawable.shooter5,R.drawable.shooter6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image1);

        for(int i = 0;i<20;i++) {
            new LoadAnim().execute();
        }
    }


    class LoadAnim extends AsyncTask<Void,Integer,Integer>{

        @Override
        protected void onProgressUpdate(Integer... values) {
            imageView.setImageResource(values[0]);
        }


        @Override
        protected Integer doInBackground(Void... params) {

            for(int i = 0;i<5;i++) {
                publishProgress(array[i]);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //return array[i];
            }
            return array[0];
        }

        @Override
        protected void onPostExecute(Integer aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
